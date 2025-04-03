package GRUPO1.TP.controllers;

import GRUPO1.TP.dto.DTOStudent;
import GRUPO1.TP.dto.DTOStudentSummary;
import GRUPO1.TP.entities.Student;
import GRUPO1.TP.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentService studentService;

    //http://localhost:8080/api/students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.listAll();
        // return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

    //-----------------------------
    @GetMapping("/students/summary/{id}")
    public ResponseEntity<DTOStudentSummary> getStudentSumary(@PathVariable("id") Long id) {
        DTOStudentSummary dtoStudentSummary = studentService.findStudentProgress(id);
        // return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
        return new ResponseEntity<>(dtoStudentSummary, HttpStatus.OK);

    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getAStudentById(@PathVariable("id") Long id) {
        Student student = studentService.findById(id);
        // return new ResponseEntity<Student>(student, HttpStatus.OK);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student newStudent = studentService.save(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }


    @DeleteMapping("/students/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Alternativa elegida -> Pasar el Id por la ruta
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") Long id) {
        Student foundStudent = studentService.findById(id);
        if (student.getName()!=null) {
            foundStudent.setName(student.getName());
        }
        if (student.getAvatar()!=null) {
            foundStudent.setAvatar(student.getAvatar());
        }
        Student newStudent = studentService.save(foundStudent);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

}