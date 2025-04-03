package GRUPO1.TP.services;
import GRUPO1.TP.dto.DTOStudentSummary;
import GRUPO1.TP.entities.Student;

import java.util.List;
public interface StudentService {
    public List<Student> listAll();
    public List<Student> listByName(String name);
    public Student save(Student student);
    public Student findById(Long id);
    public void delete(Long id);


    DTOStudentSummary findStudentProgress(Long studentId);
}
