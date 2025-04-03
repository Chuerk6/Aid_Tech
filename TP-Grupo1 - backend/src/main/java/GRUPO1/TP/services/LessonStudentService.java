package GRUPO1.TP.services;
import GRUPO1.TP.entities.LessonStudent;
import java.util.List;
public interface LessonStudentService {
    List<LessonStudent> listAll();
    LessonStudent save(LessonStudent lessonStudent);
    LessonStudent findById(Long id);
    void delete(Long id);
}
