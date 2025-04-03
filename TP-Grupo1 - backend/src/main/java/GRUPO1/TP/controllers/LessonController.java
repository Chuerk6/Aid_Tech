package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Lesson;
import GRUPO1.TP.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LessonController {
    @Autowired
    LessonService lessonservice;

    //http://localhost:8080/api/exercises_Lessons
    @GetMapping("/lessons")
    public ResponseEntity<List<Lesson>> getAll() {
        List<Lesson> lessons = lessonservice.listAll();
        return new ResponseEntity<>(lessons, HttpStatus.OK);
    }

    @GetMapping("/lessons/{id}")
    public ResponseEntity<Lesson> getById(@PathVariable("id") Long id) {
        Lesson oLesson = lessonservice.findById(id);
        return new ResponseEntity<>(oLesson, HttpStatus.OK);
    }

    @PostMapping("/lessons")
    public ResponseEntity<Lesson> create(@RequestBody Lesson oLesson) {
        Lesson newLesson = lessonservice.save(oLesson);
        return new ResponseEntity<>(newLesson, HttpStatus.CREATED);
    }


    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        lessonservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/lessons/{id}")
    public ResponseEntity<Lesson> update(@RequestBody Lesson oLesson, @PathVariable("id") Long id) {
        Lesson objectfound = lessonservice.findById(id);
        if (oLesson.getTheme()!=null) {
            objectfound.setTheme(oLesson.getTheme());
        }
        if (oLesson.getDescription()!=null) {
            objectfound.setDescription(oLesson.getDescription());
        }
        Lesson newObject = lessonservice.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
