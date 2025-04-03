package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Lesson;
import GRUPO1.TP.entities.LessonStudent;
import GRUPO1.TP.entities.Student;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.KeyRepeatedDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.LessonRepository;
import GRUPO1.TP.repositories.LessonStudentRepository;
import GRUPO1.TP.repositories.StudentRepository;
import GRUPO1.TP.services.LessonStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonStudentServiceImpl implements LessonStudentService {
    @Autowired
    LessonStudentRepository lessonStudentRepository;
    @Autowired
    LessonRepository lessonRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public List<LessonStudent> listAll() {
        List<LessonStudent> lessonStudents = lessonStudentRepository.findAll();
        for (LessonStudent l: lessonStudents) {
            l.getLesson().setLessonStudents(null);
            l. getStudent().setLessonStudents(null);
        }
        return lessonStudents;
    }

    @Override
    public LessonStudent save(LessonStudent lessonStudent) {

        if(lessonStudent.getStatus()== null || lessonStudent.getStatus().isEmpty()  ){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a exerciseImage");
        }
        List<LessonStudent> listLessonStudentDuplicated = lessonStudentRepository.findByLessonAndStudent(lessonStudent.getLesson().getId(),lessonStudent.getStudent().getId());
        if(listLessonStudentDuplicated.size()>1 || (listLessonStudentDuplicated.size()==1 && !listLessonStudentDuplicated.get(0).getId().equals(lessonStudent.getId()))){
            throw new KeyRepeatedDataException("LessonStudent no puede estar duplicado");
        }


        Lesson lesson= lessonRepository.findById(lessonStudent.getLesson().getId()).get();
        Student student=studentRepository.findById(lessonStudent.getStudent().getId()).get();
        lessonStudent.setStudent(student);
        lessonStudent.setLesson(lesson);
        return lessonStudentRepository.save(lessonStudent);
    }

    @Override
    public LessonStudent findById(Long id) {
        LessonStudent lessonStudent = lessonStudentRepository.findById(id).
                orElse(null);
        if (lessonStudent == null) {
            throw new ResourceNotFoundException("There are no object with the id: "
                    +String.valueOf(id));
        }
        return lessonStudent;

    }

    @Override
    public void delete(Long id) {
        lessonStudentRepository.deleteById(id);
    }
}
