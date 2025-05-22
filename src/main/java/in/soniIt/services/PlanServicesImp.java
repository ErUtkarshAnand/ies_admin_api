package in.soniIt.services;

import in.soniIt.binding.PlanForm;
import in.soniIt.entites.PlanEntity;
import in.soniIt.entites.PlanEntity;
import in.soniIt.repositories.PlanRepo;
import in.soniIt.services.PlanServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public  class PlanServicesImp implements PlanServices
{

    private final PlanRepo planRepo;

    @Override
    public boolean createPlan(PlanForm planForm) {
        PlanEntity entity = new PlanEntity();
        BeanUtils.copyProperties(planForm, entity);
        PlanEntity saved = planRepo.save(entity);
        return saved.getPlanId() != null;
    }

    @Override
    public List<PlanForm> fetchPlans() {
        List<PlanEntity> entities = planRepo.findAll();
        List<PlanForm> planList = new ArrayList<>();

        for (PlanEntity entity : entities) {
            PlanForm form = new PlanForm();
            BeanUtils.copyProperties(entity, form);
            planList.add(form);
        }
        return planList;
    }

    @Override
    public PlanForm getPlanById(Integer planId) {
        Optional<PlanEntity> opt = planRepo.findById(planId);
        if (opt.isPresent()) {
            PlanForm form = new PlanForm();
            BeanUtils.copyProperties(opt.get(), form);
            return form;
        }
        return null;
    }

    @Override
    public String changePlanStatus(Integer planId, String status) {
        Optional<PlanEntity> opt = planRepo.findById(planId);
        if (opt.isPresent()) {
            PlanEntity entity = opt.get();
            entity.setActiveSw(status);
            planRepo.save(entity);
            return "Status updated successfully";
        }
        return "Plan not found";
    }
}
