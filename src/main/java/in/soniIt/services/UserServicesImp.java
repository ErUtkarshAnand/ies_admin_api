package in.soniIt.services;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;

public class UserServicesImp implements UserServices{
    @Override
    public String login(LogInForm logInForm) {
        return "";
    }

    @Override
    public boolean recoverPwd(String email) {
        return false;
    }

    @Override
    public DashboardCards fetchDashboardInfo() {
        return null;
    }
}
