package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.Plan;
import GRUPO1.TP.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PlanController {
    @Autowired
    PlanService Plan;

    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> getAll() {
        List<Plan> plans = Plan.listAll();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @GetMapping("/plans/{id}")
    public ResponseEntity<Plan> getById(@PathVariable("id") Long id) {
        Plan oPlan = Plan.findById(id);
        return new ResponseEntity<>(oPlan, HttpStatus.OK);
    }

    @PostMapping("/plans")
    public ResponseEntity<Plan> create(@RequestBody Plan oPlan) {
        Plan newPlan = Plan.save(oPlan);
        return new ResponseEntity<>(newPlan, HttpStatus.CREATED);
    }


    @DeleteMapping("/plans/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        Plan.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/plans/{id}")
    public ResponseEntity<Plan> update(@RequestBody Plan oPlan, @PathVariable("id") Long id) {
        Plan objectfound = Plan.findById(id);
        if (oPlan.getName()!=null) {
            objectfound.setName(oPlan.getName());
        }
        if (oPlan.getDescription()!=null) {
            objectfound.setDescription(oPlan.getDescription());
        }
        if (oPlan.getPrice()!=null) {
            objectfound.setPrice(oPlan.getPrice());
        }
        if (oPlan.getTime()!=null) {
            objectfound.setTime(oPlan.getTime());
        }
        Plan newObject = Plan.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
