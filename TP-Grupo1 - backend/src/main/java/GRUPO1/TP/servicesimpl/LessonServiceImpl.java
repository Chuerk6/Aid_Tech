package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Lesson;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.LessonRepository;
import GRUPO1.TP.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonRepository lessonRepository;
    @Override
    public List<Lesson> listAll() {
        List<Lesson> lessons = lessonRepository.findAll();
        for (Lesson l: lessons) {
            l.setExercise(null);
            l.setLessonStudents(null);
        }
        return lessons;
    }

    @Override
    public Lesson save(Lesson lesson) {
        if(lesson.getTheme()== null || lesson.getTheme().isEmpty() ||
                lesson.getDescription() == null || lesson.getDescription().isEmpty() ){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a object");
        }
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson findById(Long id) {
        Lesson lessonfound = lessonRepository.findById(id).orElse(null);
        if (lessonfound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return lessonfound;
        //return lessonRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if (lesson == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
        lessonRepository.deleteById(id);

    }

    @Override
    public List<Lesson> findCompletedLessonsByStudentId(Long studentId) {
        return lessonRepository.findCompletedLessonsByStudentId(studentId);
    }

}
