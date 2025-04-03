package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.*;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.PlanRepository;
import GRUPO1.TP.repositories.StudentPlanRepository;
import GRUPO1.TP.repositories.StudentRepository;
import GRUPO1.TP.services.StudentPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentPlanServiceImpl implements StudentPlanService {
    @Autowired
    StudentPlanRepository studentPlanRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PlanRepository planRepository;
    @Override
    public List<StudentPlan> listAll() {
        List<StudentPlan> studentPlans = studentPlanRepository.findAll();
        for (StudentPlan e: studentPlans) {
            e.getStudent().setStudentPlans(null);
            e.getPlan().setStudentPlans(null);
        }
        return studentPlans;
    }

    @Override
    public StudentPlan save(StudentPlan studentPlan) {
        if( studentPlan.getEnd_date()== null || studentPlan.getStar_date()==null ){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a exerciseImage");
        }
        Student student= studentRepository.findById(studentPlan.getStudent().getId()).get();
        Plan plan=planRepository.findById(studentPlan.getPlan().getId()).get();
        studentPlan.setStudent(student);
        studentPlan.setPlan(plan);
        return studentPlanRepository.save(studentPlan);
    }

    @Override
    public StudentPlan findById(Long id) {
        StudentPlan studentPlanfound = studentPlanRepository.findById(id).
                orElse(null);
        if (studentPlanfound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "
                    +String.valueOf(id));
        }
        return studentPlanfound;
    }

    @Override
    public void delete(Long id) {
        studentPlanRepository.deleteById(id);
    }
}
