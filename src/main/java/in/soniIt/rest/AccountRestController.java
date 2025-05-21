package in.soniIt.rest;

import in.soniIt.binding.UserAccountForm;
import in.soniIt.services.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {


    @Autowired
    private AccountServices accountServices;
  @PostMapping("/user")
    public ResponseEntity<String> createAccount(@RequestBody UserAccountForm userAccountForm){
       boolean status= accountServices.createUserAccount(userAccountForm);

       if(status){
               return  new ResponseEntity<>("Account Created", HttpStatus.CREATED);//201
       }else{
           return  new ResponseEntity<>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);//500
       }
    }

    @GetMapping("/user")
    public ResponseEntity<List<UserAccountForm>> getUsers(){
      List<UserAccountForm> userAccountFormList= accountServices.fetchUserAccounts();
     return  new ResponseEntity<>(userAccountFormList ,HttpStatus.OK);


    }


    @GetMapping("/user/{userId}")
    public  ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Integer userId){
      UserAccountForm userAccById =accountServices.getUserAccById(userId);
       return  new ResponseEntity<>(userAccById,HttpStatus.OK);
    }

   @PutMapping("/user/{userId}/{status}")
    public  ResponseEntity<List<UserAccountForm>> updateUserAcc( @PathVariable("userId") Integer userId,@PathVariable ("status") String status){
     accountServices.changeAccStatus(userId,status);
     List<UserAccountForm> userAccountForms =accountServices.fetchUserAccounts();
     return new ResponseEntity<>(userAccountForms ,HttpStatus.OK);

    }
}
