package in.soniIt.services;

import in.soniIt.binding.PlanForm;
import in.soniIt.repositories.PlanRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServicesImp implements PlanServices{

    @Autowired
    private PlanRepo planRepo;
    @Override
    public boolean createPlan(PlanForm planForm) {
        return false;
    }

    @Override
    public List<PlanForm> fetchPlans() {
        return List.of();
    }

    @Override
    public PlanForm getPlanById(Integer planId) {
        return null;
    }

    @Override
    public String changePlanStatus(Integer planId, String status) {
        return "";
    }
}
