package in.soniIt.services;

import in.soniIt.binding.UnLockAccForm;
import in.soniIt.binding.UserAccountForm;

import java.util.List;

public interface AccountServices {
   public  boolean createUserAccount(UserAccountForm accForm);

   public List<UserAccountForm>fetchUserAccounts();

   public UserAccountForm getUserAccById(Integer accId);

   public String changeAccStatus(Integer accId,String status);

   public  String unlockAccount(UnLockAccForm unLockAccForm);
}
