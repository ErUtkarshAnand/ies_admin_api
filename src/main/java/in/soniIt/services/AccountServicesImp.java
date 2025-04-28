package in.soniIt.services;

import in.soniIt.binding.UnLockAccForm;
import in.soniIt.binding.UserAccountForm;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServicesImp  implements AccountServices{
    @Override
    public boolean createUserAccount(UserAccountForm accForm) {
        return false;
    }

    @Override
    public List<UserAccountForm> fetchUserAccounts() {
        return List.of();
    }

    @Override
    public UserAccountForm getUserAccById(Integer accId) {
        return null;
    }

    @Override
    public String changeAccStatus(Integer accId, String status) {
        return "";
    }

    @Override
    public String unlockAccount(UnLockAccForm unLockAccForm) {
        return "";
    }
}
