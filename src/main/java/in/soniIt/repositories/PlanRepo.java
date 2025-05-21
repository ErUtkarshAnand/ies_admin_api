package in.soniIt.repositories;

import in.soniIt.entites.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo  extends JpaRepository<PlanEntity,Integer> {

}
