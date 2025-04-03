package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.ExerciseImage;
import GRUPO1.TP.services.ExerciseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ExerciseImageController {
    @Autowired
    ExerciseImageService exerciseImageService;

    //http://localhost:8080/api/exercises_images
    @GetMapping("/exercises_images")
    public ResponseEntity<List<ExerciseImage>> getAll() {
        List<ExerciseImage> exerciseImages = exerciseImageService.listAll();
        return new ResponseEntity<>(exerciseImages, HttpStatus.OK);

    }

    @GetMapping("/exercises_images/{id}")
    public ResponseEntity<ExerciseImage> getById(@PathVariable("id") Long id) {
        ExerciseImage exerciseImage = exerciseImageService.findById(id);
        return new ResponseEntity<>(exerciseImage, HttpStatus.OK);
    }

    @PostMapping("/exercises_images")
    public ResponseEntity<ExerciseImage> create(@RequestBody ExerciseImage exerciseImage) {
        ExerciseImage newExerciseImage = exerciseImageService.save(exerciseImage);
        return new ResponseEntity<>(newExerciseImage, HttpStatus.CREATED);
    }


    @DeleteMapping("/exercises_images/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        exerciseImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/exercises_images/{id}")
    public ResponseEntity<ExerciseImage> update(@RequestBody ExerciseImage exerciseImage, @PathVariable("id") Long id) {
        ExerciseImage exerciseImagefound = exerciseImageService.findById(id);
        if (exerciseImage.getCorrect_answer()!=null) {
            exerciseImagefound.setCorrect_answer(exerciseImage.getCorrect_answer());
        }
        if (exerciseImage.getCorrect_option()!=null) {
            exerciseImagefound.setCorrect_option(exerciseImage.getCorrect_option());
        }
        ExerciseImage newObject = exerciseImageService.save(exerciseImagefound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
