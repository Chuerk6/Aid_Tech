package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Exercise;
import GRUPO1.TP.entities.Student;
import GRUPO1.TP.entities.StudentExercise;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.ExerciseRepository;
import GRUPO1.TP.repositories.StudentExerciseRepository;
import GRUPO1.TP.repositories.StudentRepository;
import GRUPO1.TP.services.StudentExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentExerciseServiceImpl implements StudentExerciseService {
    @Autowired
    StudentExerciseRepository studentExerciseRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Override

    public List<StudentExercise> listAll() {
        List<StudentExercise>  studentExercises = studentExerciseRepository.findAll();
        for (StudentExercise e: studentExercises) {
            e.getExercise().setExercisesImages(null);
            e.getStudent().setStudentExercises(null);
        }
        return studentExercises;
    }

    @Override
    public StudentExercise save(StudentExercise studentExercise) {
        if( studentExercise.getAnswer_date()== null){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a exerciseImage");
        }
        Exercise exercise= exerciseRepository.findById(studentExercise.getExercise().getId()).get();
        Student student=studentRepository.findById(studentExercise.getStudent().getId()).get();
       studentExercise.setExercise(exercise);
       studentExercise.setStudent(student);
        return studentExerciseRepository.save(studentExercise);
    }

    @Override
    public StudentExercise findById(Long id) {
        StudentExercise studentExercisefound = studentExerciseRepository.findById(id).
                orElse(null);
        if (studentExercisefound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "
                    +String.valueOf(id));
        }
       return studentExercisefound;
        // return studentExerciseRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        studentExerciseRepository.deleteById(id);
    }
}
