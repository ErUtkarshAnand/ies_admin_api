package in.soniIt.rest;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;
import in.soniIt.binding.UserAccountForm;
import in.soniIt.services.AccountServices;
import in.soniIt.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    private UserServices userService;

    @PostMapping("/login")
    public String login(@RequestBody LogInForm loginForm) {
        String status = userService.login(loginForm);
        if (status.equals("success")) {
            return "redirect:/dashboard?email=" + loginForm.getEmail();
        } else {
            return status;
        }
    }

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardCards> buildDashboard(@RequestParam("email") String email) {
        UserAccountForm user = userService.getUserByEmail(email);
        DashboardCards dashboardCard = userService.fetchDashboardInfo();
        dashboardCard.setUser(user);
        return new ResponseEntity<>(dashboardCard, HttpStatus.OK);
    }
}
