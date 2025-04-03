package GRUPO1.TP.services;
import GRUPO1.TP.entities.Plan;
import java.util.List;
public interface PlanService {
    List<Plan> listAll();
    Plan save(Plan plan);
    Plan findById(Long id);
    void delete(Long id);
}
