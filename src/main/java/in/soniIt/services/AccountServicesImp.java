package in.soniIt.services;

import in.soniIt.binding.UnLockAccForm;
import in.soniIt.binding.UserAccountForm;
import in.soniIt.entites.UserEntity;
import in.soniIt.repositories.UserRepo;
import in.soniIt.utils.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountServicesImp  implements AccountServices{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public boolean createUserAccount(UserAccountForm accForm) {

        UserEntity entity=new UserEntity();
        BeanUtils.copyProperties(accForm,entity);

        //set random pwd
        entity.setPwd(generatePwd());

        //set acc status
        entity.setAccStatus("LOCKED");
        entity.setActiveSw("Y");

        userRepo.save(entity);

        //send email
        String subject="";
        String body="";
        return emailUtils.sendEmail(subject,body,accForm.getEmail());

    }

    @Override
    public List<UserAccountForm> fetchUserAccounts() {
        List<UserEntity> userEntities=userRepo.findAll();
        List<UserAccountForm> users= new ArrayList<UserAccountForm>();
        for(UserEntity userEntity:userEntities)
        {
            UserAccountForm user= new UserAccountForm();
            BeanUtils.copyProperties(userEntity,user);
            users.add(user);
        }
        return users;
    }

    @Override
    public UserAccountForm getUserAccById(Integer accId) {

        Optional<UserEntity> optional =userRepo.findById(accId);
        if(optional.isPresent()){
            UserEntity userEntity =optional.get();
            UserAccountForm user =new UserAccountForm();
            BeanUtils.copyProperties(userEntity,user);
             return user;
        }
        return null;
    }

    @Override
    public String changeAccStatus(Integer userId, String status) {
       int cnt = userRepo.updateAccStatus(userId,status);
        if(cnt>0){
            return "Status Changed";
        }
        return "Failed to change";
    }

    @Override
    public String unlockAccount(UnLockAccForm unLockAccForm) {
        UserEntity entity= userRepo.findByEmail(unLockAccForm.getEmail());
          entity.setPwd(unLockAccForm.getNewPwd());
          entity.setAccStatus("UNLOCKED");
          userRepo.save(entity);

        return "Account Unlocked";
    }

    private  String generatePwd(){
        // create a string of all characters
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 6;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        return sb.toString();

    }
}
