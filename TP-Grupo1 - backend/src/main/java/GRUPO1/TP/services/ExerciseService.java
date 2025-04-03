package GRUPO1.TP.services;
import GRUPO1.TP.entities.Exercise;
import java.util.List;

public interface ExerciseService {
    List<Exercise> listAll();
    Exercise findById(Long id);
    Exercise save(Exercise exercise);
    void delete(Long id);

    List<Exercise> findAllByLessonId(Long lessonId);
    Exercise findActualExercise(Long id1, Long id2);
}
