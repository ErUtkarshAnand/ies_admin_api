package in.soniIt.services;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;
import in.soniIt.binding.UserAccountForm;

public interface UserServices {
    public   String login(LogInForm logInForm);

    public boolean recoverPwd(String email);

    public DashboardCards fetchDashboardInfo();

    public UserAccountForm getUserByEmail(String email);


}
