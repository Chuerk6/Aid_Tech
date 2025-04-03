package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Image;
import GRUPO1.TP.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ImageController {
    @Autowired
    ImageService imageService;

    //http://localhost:8080/api/exercises_images
    @GetMapping("/images")
    public ResponseEntity<List<Image>> getAll() {
        List<Image> images = imageService.listAll();
        return new ResponseEntity<>(images, HttpStatus.OK);

    }

    @GetMapping("/images/{id}")
    public ResponseEntity<Image> getById(@PathVariable("id") Long id) {
        Image image = imageService.findById(id);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @PostMapping("/images")
    public ResponseEntity<Image> create(@RequestBody Image image) {
        Image newimage = imageService.save(image);
        return new ResponseEntity<>(newimage, HttpStatus.CREATED);
    }


    @DeleteMapping("/images/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/images/{id}")
    public ResponseEntity<Image> update(@RequestBody Image image, @PathVariable("id") Long id) {
        Image objectfound = imageService.findById(id);
        if (image.getLink()!=null) {
            objectfound.setLink(image.getLink());
        }
        if (image.getMeaning()!=null) {
            objectfound.setMeaning(image.getMeaning());
        }
        Image newObject = imageService.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
