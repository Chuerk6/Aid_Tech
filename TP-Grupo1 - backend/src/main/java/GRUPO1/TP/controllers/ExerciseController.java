package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Exercise;
import GRUPO1.TP.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ExerciseController {
    @Autowired
    ExerciseService exerciseService;

    //http://localhost:8080/api/students
    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getAllExercise() {
        List<Exercise> exercises = exerciseService.listAll();
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> getAExercisesById(@PathVariable("id") Long id) {
        Exercise exercise = exerciseService.findById(id);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PostMapping("/exercises")
    public ResponseEntity<Exercise> createStudent(@RequestBody Exercise exercise) {
        Exercise newexercise = exerciseService.save(exercise);
        return new ResponseEntity<>(newexercise, HttpStatus.CREATED);
    }


    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<HttpStatus> deleteExercise(@PathVariable("id") Long id) {
        exerciseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/exercises/{id}")
    public ResponseEntity<Exercise> update(@RequestBody Exercise exercise, @PathVariable("id") Long id) {
        Exercise exerciseFound = exerciseService.findById(id);
        if (exercise.getLevel()!=null) {
            exerciseFound.setLevel(exercise.getLevel());
        }
        if (exercise.getType_question()!=null) {
            exerciseFound.setType_question(exercise.getType_question());
        }
        if (exercise.getQuestion()!=null) {
            exerciseFound.setQuestion(exercise.getQuestion());
        }
        if (exercise.getComment()!=null) {
            exerciseFound.setComment(exercise.getComment());
        }
        Exercise nexExercise = exerciseService.save(exerciseFound);
        return new ResponseEntity<>(nexExercise, HttpStatus.OK);
    }

//    @GetMapping("/exercise/{user_id}/{lesson_id}")
//    public ResponseEntity<Exercise> getActualExercise(@PathVariable("user_id") Long user_id, @PathVariable("lesson_id") Long lesson_id) {
//        System.out.println("-----------------------------");
//        System.out.println(user_id);
//        System.out.println(lesson_id);
//        Exercise exercise = exerciseService.findActualExercise(user_id, lesson_id);
//        System.out.println(exercise);
//        System.out.println("-----------------------------");
//        return new ResponseEntity<>(exercise, HttpStatus.OK);
//    }

    @GetMapping("/exercises/{id1}/{id2}")
    public ResponseEntity<Exercise> getAExercisesById(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2) {
        Exercise exercise = exerciseService.findActualExercise(id2, id1);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

}
