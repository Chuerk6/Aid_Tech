package GRUPO1.TP.controllers;

import GRUPO1.TP.entities.StudentPlan;
import GRUPO1.TP.services.StudentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentPlanController {
    @Autowired
    StudentPlanService studentplanservice;

    @GetMapping("/students_plans")
    public ResponseEntity<List<StudentPlan>> getAllStudentPlan() {
        List<StudentPlan> studentPlans = studentplanservice.listAll();
        return new ResponseEntity<>(studentPlans, HttpStatus.OK);
    }

    @GetMapping("/students_plans/{id}")
    public ResponseEntity<StudentPlan> getByIdStudentPlan(@PathVariable("id") Long id) {
        StudentPlan oStudentPlan = studentplanservice.findById(id);
        return new ResponseEntity<>(oStudentPlan, HttpStatus.OK);
    }

    @PostMapping("/students_plans")
    public ResponseEntity<StudentPlan> createStudentPlan(@RequestBody StudentPlan oStudentPlan) {
        StudentPlan newStudentPlan = studentplanservice.save(oStudentPlan);
        return new ResponseEntity<>(newStudentPlan, HttpStatus.CREATED);
    }


    @DeleteMapping("/students_plans/{id}")
    public ResponseEntity<HttpStatus> deleteStudentPlan(@PathVariable("id") Long id) {
        studentplanservice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/students_plans/{id}")
    public ResponseEntity<StudentPlan> updateStudentPlan(@RequestBody StudentPlan oStudentPlan, @PathVariable("id") Long id) {
        StudentPlan objectfound = studentplanservice.findById(id);
        if (oStudentPlan.getStar_date()!=null) {
            objectfound.setStar_date(oStudentPlan.getStar_date());
        }
        if (oStudentPlan.getEnd_date()!=null) {
            objectfound.setEnd_date(oStudentPlan.getEnd_date());
        }
        StudentPlan newObject = studentplanservice.save(objectfound);
        return new ResponseEntity<>(newObject, HttpStatus.OK);
    }
}
