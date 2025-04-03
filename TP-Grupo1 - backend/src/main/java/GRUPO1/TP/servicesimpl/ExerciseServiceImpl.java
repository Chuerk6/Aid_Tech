package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Exercise;
import GRUPO1.TP.entities.Lesson;
import GRUPO1.TP.entities.LessonStudent;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.ExerciseRepository;
import GRUPO1.TP.repositories.LessonRepository;
import GRUPO1.TP.repositories.LessonStudentRepository;
import GRUPO1.TP.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
    @Service
public class ExerciseServiceImpl implements ExerciseService{
    @Autowired
    ExerciseRepository exerciseRepository;
        @Autowired
        private LessonRepository lessonRepository;
        @Autowired
        private LessonStudentRepository lessonStudentRepository;

        @Override
    public List<Exercise> listAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        for (Exercise e: exercises) {
            e.setExercisesImages(null);
            e.setStudentExercises(null);

        }
        return exercises;
    }

    @Override
    public Exercise findById(Long id) {
        Exercise exercisefound = exerciseRepository.findById(id).orElse(null);
        if (exercisefound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return exercisefound;
    }


    @Override
    public Exercise save(Exercise exercise) {
        if(exercise.getLevel()== null || exercise.getLevel().isEmpty() ||
                exercise.getType_question() == null || exercise.getType_question().isEmpty() ||
                exercise.getQuestion() == null || exercise.getQuestion().isEmpty() ||
                exercise.getComment() == null || exercise.getComment().isEmpty()){

            throw new IncompleteDataException("Not all the necessary data has been entered to register a object");
        }
        return exerciseRepository.save(exercise);
    }

    @Override
    public void delete(Long id) {
        Exercise exercise = exerciseRepository.findById(id).orElse(null);
        if (exercise == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
//        List<Exercise> listDuplicated= exerciseRepository.
//                findByNameContaining(exercise.getName());
//        if (listDuplicated.size()>1 || (listDuplicated.size()==1
//                && !listDuplicated.get(0).getId().equals(exercise.getId())) ) {
//            throw new KeyRepeatedDataException("Employee name can not be duplicated");
//        }
        exerciseRepository.deleteById(id);
    }

        @Override
        public List<Exercise> findAllByLessonId(Long lessonId) {
            return exerciseRepository.findAllByLessonId(lessonId);
        }


    @Override
    public Exercise findActualExercise(Long id1, Long id2){
        System.out.println("hasta aqui normal");
        System.out.println("hasta aqui normal");
        Object result = exerciseRepository.findActualExercise(id1, id2);
        System.out.println("exercise ID: " + result);

        // Verificar si el resultado es nulo
        if (result == null) {
            System.out.println("No exercise found for lessonId: " + id1 + " and userId: " + id2);
            LessonStudent lessonStudent = lessonStudentRepository.findByLessonId(id1);
            if (lessonStudent != null) {
                lessonStudent.setStatus("Completado");
                System.out.println("se completo la leccion");
            }
            lessonStudentRepository.save(lessonStudent);
            return null;
        }

        // Convertir el resultado a Long de manera segura
        Long exerciseId = 0L;
        try {
            exerciseId = Long.parseLong(result.toString());
        } catch (NumberFormatException e) {
            System.out.println("The exercise ID is not a valid number: " + result);
        }
        System.out.println("exercise ID: " + exerciseId);
        Exercise exercisefound = exerciseRepository.findById(exerciseId).orElse(null);
        if (exercisefound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(exerciseId));
        }
        return exercisefound;
    }
}
