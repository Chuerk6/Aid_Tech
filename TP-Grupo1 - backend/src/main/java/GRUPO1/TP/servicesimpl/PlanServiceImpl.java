package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Plan;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.PlanRepository;
import GRUPO1.TP.services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlanServiceImpl implements PlanService {
    @Autowired
    PlanRepository  planRepository;
    @Override
    public List<Plan> listAll() {
        List<Plan> plans = planRepository.findAll();
        for (Plan e: plans) {
            e.setStudentPlans(null);
        }
        return plans;
    }

    @Override
    public Plan save(Plan plan) {
        if(plan.getName()== null || plan.getName().isEmpty() ||
                plan.getDescription() == null || plan.getDescription().isEmpty() ||
                plan.getPrice() == null || plan.getPrice().isNaN() ||
                plan.getTime() == null){

            throw new IncompleteDataException("Not all the necessary data has been entered to register a object");
        }
        return planRepository.save(plan);
    }

    @Override
    public Plan findById(Long id) {
        Plan planfound = planRepository.findById(id).orElse(null);
        if (planfound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return planfound;
        //return planRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Plan plan = planRepository.findById(id).orElse(null);
        if (plan == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
        planRepository.deleteById(id);
    }
}
