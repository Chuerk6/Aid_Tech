package GRUPO1.TP.services;
import GRUPO1.TP.entities.Lesson;
import java.util.List;
public interface LessonService {
    List<Lesson> listAll();
    Lesson save(Lesson lesson);
    Lesson findById(Long id);
    void delete(Long id);

    List<Lesson> findCompletedLessonsByStudentId(Long studentId);

}
