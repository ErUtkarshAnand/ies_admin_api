package in.soniIt.repositories;

import in.soniIt.entites.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo  extends JpaRepository<UserEntity,Integer> {


    @Query("update UserEntity set accStatus =: status where userId=:userId")
    public  Integer updateAccStatus(Integer UserId, String status);

    public UserEntity findByEmail(String email);

    public UserEntity findByEmailAndPwd(String email, String pwd);
}
