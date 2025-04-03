package GRUPO1.TP.services;
import GRUPO1.TP.entities.Image;
import java.util.List;
public interface ImageService {
    List<Image> listAll();
    Image save(Image image);
    Image findById(Long id);
    void delete(Long id);
}
