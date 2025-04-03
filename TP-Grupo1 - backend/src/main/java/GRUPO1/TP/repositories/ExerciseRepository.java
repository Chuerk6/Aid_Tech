package GRUPO1.TP.repositories;

import GRUPO1.TP.entities.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    @Query("SELECT e FROM Exercise e WHERE e.lesson.id = :lessonId")
    List<Exercise> findAllByLessonId(@Param("lessonId") Long lessonId);

//    @Query(value = "select e.id\n" +
//            "from exercises e \n" +
//            "\tjoin students_exercises se on e.id=se.exercise_id \n" +
//            "\tjoin lessons l on l.id=e.lesson_id \n" +
//            "\tjoin students stu on stu.id=se.student_id\n" +
//            "where e.lesson_id=?1 and se.student_id=?2 and se.correct is null\n" +
//            "group by e.id\n" +
//            "order by e.id desc\n" +
//            "limit 1", nativeQuery = true)
//    Object findActualExercise(Long userId, Long lessonId);

    @Query(value = "SELECT e.id\n" +
            "FROM exercises e\n" +
            "JOIN students_exercises se ON e.id = se.exercise_id\n" +
            "JOIN lessons l ON l.id = e.lesson_id\n" +
            "JOIN students stu ON stu.id = se.student_id\n" +
            "WHERE e.lesson_id = ?1 AND se.student_id = ?2 AND se.correct IS NULL\n" +
            "ORDER BY e.id ASC\n" +
            "LIMIT 1", nativeQuery = true)
    Object findActualExercise(Long lessonId, Long userId);
}
