package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.StudentPlan;

@Repository
public interface StudentPlanRepository extends JpaRepository<StudentPlan, Long>{
}
