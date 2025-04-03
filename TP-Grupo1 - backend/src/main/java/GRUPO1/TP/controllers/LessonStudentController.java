package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.LessonStudent;
import GRUPO1.TP.services.LessonStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LessonStudentController {
    @Autowired
    LessonStudentService lessonstudentsservice;

    //http://localhost:8080/api/exercises_Lessons
    @GetMapping("/lesson_students")
    public ResponseEntity<List<LessonStudent>> getAll() {
        List<LessonStudent> lessonStudents = lessonstudentsservice.listAll();
        return new ResponseEntity<>(lessonStudents, HttpStatus.OK);
    }

    @GetMapping("/lesson_students/{id}")
    public ResponseEntity<LessonStudent> getById(@PathVariable("id") Long id) {
        LessonStudent oLessonStudent = lessonstudentsservice.findById(id);
        return new ResponseEntity<>(oLessonStudent, HttpStatus.OK);
    }

    @PostMapping("/lesson_students")
    public ResponseEntity<LessonStudent> create(@RequestBody LessonStudent oLessonStudent) {
        LessonStudent newLessonStudent = lessonstudentsservice.save(oLessonStudent);
        return new ResponseEntity<>(newLessonStudent, HttpStatus.CREATED);
    }


    @DeleteMapping("/lesson_students/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        lessonstudentsservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/lesson_students/{id}")
    public ResponseEntity<LessonStudent> update(@RequestBody LessonStudent oLessonStudent, @PathVariable("id") Long id) {
        LessonStudent objectfound = lessonstudentsservice.findById(id);
        if (oLessonStudent.getStatus()!=null) {
            objectfound.setStatus(oLessonStudent.getStatus());
        }
        LessonStudent newObject = lessonstudentsservice.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
