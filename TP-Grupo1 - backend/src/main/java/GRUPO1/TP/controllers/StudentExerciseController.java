package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.StudentExercise;
import GRUPO1.TP.services.StudentExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentExerciseController {
    @Autowired
    StudentExerciseService studentExerciseservice;

    @GetMapping("/students_exercises")
    public ResponseEntity<List<StudentExercise>> getAllStudentExercise() {
        List<StudentExercise> studentsExercises = studentExerciseservice.listAll();
        return new ResponseEntity<>(studentsExercises, HttpStatus.OK);
    }

    @GetMapping("/students_exercises/{id}")
    public ResponseEntity<StudentExercise> getByIdStudentExercise(@PathVariable("id") Long id) {
        StudentExercise oStudentExercise = studentExerciseservice.findById(id);
        return new ResponseEntity<>(oStudentExercise, HttpStatus.OK);
    }

    @PostMapping("/students_exercises")
    public ResponseEntity<StudentExercise> createStudentExercise(@RequestBody StudentExercise oStudentExercise) {
        StudentExercise newStudentExercise = studentExerciseservice.save(oStudentExercise);
        return new ResponseEntity<>(newStudentExercise, HttpStatus.CREATED);
    }


    @DeleteMapping("/students_exercises/{id}")
    public ResponseEntity<HttpStatus> deleteStudentExercise(@PathVariable("id") Long id) {
        studentExerciseservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/students_exercises/{id}")
    public ResponseEntity<StudentExercise> updateStudentExercise(@RequestBody StudentExercise oStudentExercise, @PathVariable("id") Long id) {
        StudentExercise objectfound = studentExerciseservice.findById(id);
        if (oStudentExercise.getAnswer_date()!=null) {
            objectfound.setAnswer_date(oStudentExercise.getAnswer_date());
        }
        if (oStudentExercise.getCorrect()!=null) {
            objectfound.setCorrect(oStudentExercise.getCorrect());
        }
        StudentExercise newObject = studentExerciseservice.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
