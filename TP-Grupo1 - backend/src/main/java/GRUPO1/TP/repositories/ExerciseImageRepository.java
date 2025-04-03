package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.ExerciseImage;

import java.util.List;
@Repository
public interface ExerciseImageRepository extends JpaRepository<ExerciseImage, Long> {
//    @Query("SELECT e FROM Exercise e WHERE e.level= :level")
//    List<ExerciseImage> ListLevelExeImage(String level);

//    @Query("SELECT e FROM Exercise e WHERE e.type_question = :type")
//    List<ExerciseImage> ListBytypeQuestion(String type);
}
