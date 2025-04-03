package GRUPO1.TP.servicesimpl;

import GRUPO1.TP.entities.Image;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.ImageRepository;
import GRUPO1.TP.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public List<Image> listAll() {
        List<Image> images = imageRepository.findAll();
        for (Image i: images) {
            i.setExercisesImages(null);
        }
        return images;
    }

    @Override
    public Image save(Image image) {
        if(image.getLink()== null || image.getLink().isEmpty() ||
                image.getMeaning() == null || image.getMeaning().isEmpty() ){

            throw new IncompleteDataException("Not all the necessary data has been entered to register a object");
        }
        return imageRepository.save(image);
    }

    @Override
    public Image findById(Long id) {
        Image imagefound = imageRepository.findById(id).orElse(null);
        if (imagefound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return imagefound;
    }

    @Override
    public void delete(Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (image == null) {
            throw new ResourceNotFoundException("object with id: "+String.valueOf(id)
                    + " not found");
        }
        imageRepository.deleteById(id);

    }
}
