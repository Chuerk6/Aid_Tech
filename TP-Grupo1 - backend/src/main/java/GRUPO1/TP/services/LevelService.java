package GRUPO1.TP.services;
import GRUPO1.TP.entities.Level;
import java.util.List;
public interface LevelService {
    List<Level> listAll();
    Level save(Level level);
    Level findById(Long id);
    void delete(Long id);
}
