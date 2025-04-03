package GRUPO1.TP.services;
import GRUPO1.TP.entities.StudentPlan;
import java.util.List;
public interface StudentPlanService {
    List<StudentPlan> listAll();
    StudentPlan save(StudentPlan studentPlan);
    StudentPlan findById(Long id);
    void delete(Long id);
}
