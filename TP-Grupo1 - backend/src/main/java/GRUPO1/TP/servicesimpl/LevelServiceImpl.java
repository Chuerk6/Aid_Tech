package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Level;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.LevelRepository;
import GRUPO1.TP.services.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LevelServiceImpl implements LevelService {
    @Autowired
    LevelRepository levelRepository;
    @Override
    public List<Level> listAll() {
        List<Level> levels = levelRepository.findAll();
        for (Level l: levels) {
            l.setStudents(null);
        }
        return levels;
    }

    @Override
    public Level save(Level level) {
        if(level.getName()== null || level.getName().isEmpty() ||
                level.getDescription() == null || level.getDescription().isEmpty() ){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a object");
        }
        return levelRepository.save(level);
    }

    @Override
    public Level findById(Long id) {
        Level levelfound = levelRepository.findById(id).orElse(null);
        if (levelfound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return levelfound;
        //return levelRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        Level level = levelRepository.findById(id).orElse(null);
        if (level == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
        levelRepository.deleteById(id);
    }
}
