package in.soniIt.services;

import in.soniIt.binding.DashboardCards;
import in.soniIt.binding.LogInForm;
import in.soniIt.entites.EligEntity;
import in.soniIt.entites.UserEntity;
import in.soniIt.repositories.EligRepo;
import in.soniIt.repositories.PlanRepo;
import in.soniIt.repositories.UserRepo;
import in.soniIt.utils.EmailUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.DoubleStream;

public class UserServicesImp implements UserServices{


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PlanRepo planRepo;

    @Autowired
    private EligRepo eligRepo;

    @Autowired
    private EmailUtils emailUtils;

    @Override
    public String login(LogInForm logInForm) {
         UserEntity entity =  userRepo.findByEmailAndPwd(logInForm.getEmail(), logInForm.getPwd());
         if(entity ==null)
         {
             return "Invalid Credentials";
         }

         if( "Y" .equals(entity.getActiveSw()) && "UNLOCKED".equals(entity.getAccStatus())){
             return "success@role ="+entity.getRoleId();
         }else{
             return "Account Locked/In-Active";
         }

    }

    @Override
    public boolean recoverPwd(String email) {

         UserEntity userEntity = userRepo.findByEmail(email);
         if(null==userEntity){
             return false;
         }else{
             String subject = "";
             String body = "";
             return  emailUtils.sendEmail(subject,body,email);
         }

    }

    @Override
    public DashboardCards fetchDashboardInfo() {
  long plansCount = planRepo.count();

  List<EligEntity> eligList =eligRepo.findAll();
  Long approvedCnt = eligList.stream().filter(ed->ed.getPlanStatus().equals("AP")).count();

  Long deniedCnt = eligList.stream().filter(ed ->ed.getPlanStatus().equals("DN")).count();

   Double total  = eligList.stream().mapToDouble(ed->ed.getBenifitAmt()).sum();



   DashboardCards cards =new DashboardCards();
    cards.setPlansCnt(plansCount);
    cards.setApprovedCnt(approvedCnt);
    cards.setDeniedCnt(deniedCnt);
    cards.setBeniftAmtGiven(total);

        return cards;
    }
}
