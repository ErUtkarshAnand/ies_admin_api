package in.soniIt.rest;

import in.soniIt.binding.UserAccountForm;
import in.soniIt.services.AccountServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {


    private Logger logger = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private AccountServices accService;

    @PostMapping("/user")
    public ResponseEntity<String> createAccount(@RequestBody UserAccountForm userAccForm){

        logger.debug("Account Creation Process Started...");

        boolean status = accService.createUserAccount(userAccForm);

        logger.debug("Account Creation Process Completed...");

        if(status){
            logger.info("Account Created Successfully..");
            return new ResponseEntity<>("Account Created", HttpStatus.CREATED); //201
        }else{
            logger.info("Account Creation Failed..");
            return new ResponseEntity<>("Account Creation Failed", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserAccountForm>> getUsers(){
        logger.debug("Fetching User Accounts process started..");
        List<UserAccountForm> userAccForms = accService.fetchUserAccounts();
        logger.debug("Fetching User Accounts process completed..");
        logger.info("User Accounts Fetched success....");
        return new ResponseEntity<>(userAccForms, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserAccountForm> getUser(@PathVariable("userId") Integer userId){
        UserAccountForm userAccById = accService.getUserAccById(userId);
        logger.info("User account fetched successfully..");
        return new ResponseEntity<>(userAccById, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/{status}")
    public ResponseEntity<List<UserAccountForm>> updateUserAcc(@PathVariable("userId") Integer userId,
                                                           @PathVariable("status")String status){
        logger.debug("User account update process started..");
        accService.changeAccStatus(userId, status);
        logger.debug("User account update process completed..");
        logger.info("User account status updated successfully..");
        List<UserAccountForm> userAccForms = accService.fetchUserAccounts();
        return new ResponseEntity<>(userAccForms, HttpStatus.OK);
    }
}
