package in.soniIt.services;

import in.soniIt.binding.PlanForm;

import java.util.List;

public interface PlanServices {
    public boolean createPlan(PlanForm planForm);

    public List<PlanForm> fetchPlans();

    public PlanForm getPlanById(Integer planId);

    public String changePlanStatus(Integer planId,String status);
}
