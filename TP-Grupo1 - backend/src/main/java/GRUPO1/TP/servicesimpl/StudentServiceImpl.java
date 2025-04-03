package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.dto.DTOStudent;
import GRUPO1.TP.dto.DTOStudentSummary;
import GRUPO1.TP.entities.Exercise;
import GRUPO1.TP.entities.LessonStudent;
import GRUPO1.TP.entities.Student;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.KeyRepeatedDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.StudentRepository;
import GRUPO1.TP.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> listAll() {
        List<Student> students = studentRepository.findAll();
        for (Student s: students) {
            s.setLessonStudents(null);
            s.setStudentExercises(null);
            s.setStudentPlans(null);
        }
        return students;
    }



    @Override
    public List<Student> listByName(String name) {
        List<Student> students = studentRepository.findByNameContaining(name);
        return students;
    }

    @Override
    public Student findById(Long id) {
        Student studentFound = studentRepository.findById(id).orElse(null);
        if (studentFound == null) {
            throw new ResourceNotFoundException("There are no Student with the id: "+String.valueOf(id));
        }
        return studentFound;
    }

    @Override
    public Student save(Student student) {
        if (student.getName()==null || student.getName().isEmpty()) {
            throw new IncompleteDataException("Student name can not be null or empty");
        }
        List<Student> listStudentNameDuplicated= studentRepository.findByNameContaining(student.getName());
        if (listStudentNameDuplicated.size()>1 || (listStudentNameDuplicated.size()==1 && !listStudentNameDuplicated.get(0).getId().equals(student.getId())) ) {
            throw new KeyRepeatedDataException("Student name can not be duplicated");
        }

        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        Student student = findById(id);
        studentRepository.delete(student);
    }

    @Override
    public DTOStudentSummary findStudentProgress(Long studentId) {
        DTOStudentSummary dtoStudent = studentRepository.findStudentSummary(studentId);
        // Utiliza el método findById existente para obtener el estudiante
System.out.println(dtoStudent);
        return dtoStudent;

//
//        long completedLessons = student.getLessonStudents().stream()
//                .filter(lessonStudent -> "COMPLETED".equals(lessonStudent.getStatus()))
//                // Suponiendo que "COMPLETED" es el estado para lecciones completadas
//                .count();
//
//        // Obtener el plan actual del estudiante
//        String currentPlan = student.getStudentPlans().isEmpty() ? "No Plan" : student.getStudentPlans().get(0).toString();
//        // Esto se ajusta dependiendo del plan
//
//        return new DTOStudentSummary(
//                student.getName(),
//                completedLessons,
//                currentPlan,
//                student.getLevel().toString()
//
//        );
    }

}
