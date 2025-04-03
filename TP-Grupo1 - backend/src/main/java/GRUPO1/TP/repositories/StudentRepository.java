package GRUPO1.TP.repositories;

import GRUPO1.TP.dto.DTOStudentSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import GRUPO1.TP.entities.Student;
import GRUPO1.TP.dto.DTOStudentSummary;


import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{


    /* Obtener un resumen del progreso de un estudiante
       Devuelve un obj DTOStudentSummar que contiene el name del student, el # de lecciones completadas,
       el nombre del plan actual y el nombre del nivel actual del estudiante.
    */

    @Query("SELECT new GRUPO1.TP.dto.DTOStudentSummary(s.name, COUNT(sl), p.name, lv.name) " +
            "FROM Student s " +
            "JOIN s.lessonStudents sl " +
            "JOIN s.studentPlans sp " +
            "JOIN sp.plan p " +
            "JOIN s.level lv " +
            "WHERE s.id = :studentId " +
            "GROUP BY s.name, p.name, lv.name")
    DTOStudentSummary findStudentSummary(@Param("studentId") Long studentId);


    List<Student> findByAvatarOrderByNameDesc(String avatar);

    List<Student> findByAvatarAndName(String avatar, String name);

    List<Student> findByNameContaining(String name);

}