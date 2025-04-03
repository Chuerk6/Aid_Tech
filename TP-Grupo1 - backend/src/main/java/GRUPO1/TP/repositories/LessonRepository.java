package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.Lesson;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
//    @Query("SELECT l FROM Lesson l JOIN l.students s WHERE s.id = :studentId")
    List<Lesson> findCompletedLessonsByStudentId(@Param("studentId") Long studentId);

//    @Query("SELECT e FROM Lesson e WHERE e.theme = :theme")
//    List<Lesson> findByTheme(String theme);
//    @Query("SELECT e FROM Lesson e WHERE e.description = :description")
//    List<Lesson> findByDescription(String description);
}
