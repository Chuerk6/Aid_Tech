package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.LessonStudent;

import java.util.List;

@Repository
public interface LessonStudentRepository extends JpaRepository<LessonStudent, Long> {

    @Query(value="SELECT * FROM lessons_student WHERE lesson_id=?1 AND student_id=?2", nativeQuery = true)
    List<LessonStudent> findByLessonAndStudent(Long lesson_id, Long student_id);

    @Query(value="select * from lessons_student where lessons_student.lesson_id=?1", nativeQuery = true)
    LessonStudent findByLessonId(Long lesson_id);
}
