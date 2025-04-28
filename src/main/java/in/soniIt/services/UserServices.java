package in.soniIt.services;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;

public interface UserServices {
    public   String login(LogInForm logInForm);

    public boolean recoverPwd(String email);

    public DashboardCards fetchDashboardInfo();


}
