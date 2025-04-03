package GRUPO1.TP.services;
import GRUPO1.TP.entities.ExerciseImage;
import java.util.List;
public interface ExerciseImageService {
    List<ExerciseImage> listAll();
    ExerciseImage save(ExerciseImage exerciseImage);
    ExerciseImage findById(Long id);
    void delete(Long id);
}

