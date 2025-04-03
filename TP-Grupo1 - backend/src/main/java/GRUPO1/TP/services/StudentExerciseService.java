package GRUPO1.TP.services;
import GRUPO1.TP.entities.StudentExercise;
import java.util.List;
public interface StudentExerciseService {
    List<StudentExercise> listAll();
    StudentExercise save(StudentExercise studentExercise);
    StudentExercise findById(Long id);
    void delete(Long id);
}
