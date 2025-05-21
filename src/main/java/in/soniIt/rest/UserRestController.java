package in.soniIt.rest;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;
import in.soniIt.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/login")
    public String login(LogInForm logInForm){

         String status = userServices.login(logInForm);

         if(status.equals("success")){
             //login Success
            return  "redirect:/dashboard";
         }else{
             return status;

         }

    }
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardCards> buildDashboard(){
     DashboardCards dashboardCards = userServices.fetchDashboardInfo();

     return  new ResponseEntity<>(dashboardCards, HttpStatus.OK);
    }
}
