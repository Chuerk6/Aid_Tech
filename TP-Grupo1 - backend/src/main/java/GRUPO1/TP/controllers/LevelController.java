package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Level;
import GRUPO1.TP.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LevelController {
    @Autowired
    LevelService levelservice;

    //http://localhost:8080/api/exercises_levels
    @GetMapping("/levels")
    public ResponseEntity<List<Level>> getAll() {
        List<Level> levels = levelservice.listAll();
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/levels/{id}")
    public ResponseEntity<Level> getById(@PathVariable("id") Long id) {
        Level oLevel = levelservice.findById(id);
        return new ResponseEntity<>(oLevel, HttpStatus.OK);
    }

    @PostMapping("/levels")
    public ResponseEntity<Level> create(@RequestBody Level oLevel) {
        Level newLevel = levelservice.save(oLevel);
        return new ResponseEntity<>(newLevel, HttpStatus.CREATED);
    }


    @DeleteMapping("/levels/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        levelservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/levels/{id}")
    public ResponseEntity<Level> update(@RequestBody Level oLevel, @PathVariable("id") Long id) {
        Level objectfound = levelservice.findById(id);
        if (oLevel.getName()!=null) {
            objectfound.setName(oLevel.getName());
        }
        if (oLevel.getDescription()!=null) {
            objectfound.setDescription(oLevel.getDescription());
        }
        Level newObject = levelservice.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
