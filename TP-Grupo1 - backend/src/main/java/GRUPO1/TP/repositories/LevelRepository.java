package GRUPO1.TP.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import GRUPO1.TP.entities.Level;

import java.util.List;

@Repository
public interface LevelRepository extends JpaRepository<Level, Long> {
//    @Query("SELECT e FROM Level e WHERE e.name = :name")
//    List<Level> findByName(String name);

}
