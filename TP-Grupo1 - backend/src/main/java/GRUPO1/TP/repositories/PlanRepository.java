package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.Plan;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Long>{
//    @Query(value="SELECT e FROM Plan e WHERE e.price>?2 ", nativeQuery = false)
//    List<Plan> findByNameAndPrice( Double price);
//
//    @Query(value="SELECT e FROM Plan e WHERE e.price=?1 AND e.price=?2 ", nativeQuery = false)
//    List<Plan> findByPriceBetween(Double priceMin, Double priceMax );

}
