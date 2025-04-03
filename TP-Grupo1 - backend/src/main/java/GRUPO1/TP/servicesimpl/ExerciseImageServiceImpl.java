package GRUPO1.TP.servicesimpl;
import GRUPO1.TP.entities.Exercise;
import GRUPO1.TP.entities.ExerciseImage;

import GRUPO1.TP.entities.Image;
import GRUPO1.TP.exceptions.IncompleteDataException;
import GRUPO1.TP.exceptions.ResourceNotFoundException;
import GRUPO1.TP.repositories.ExerciseImageRepository;
import GRUPO1.TP.repositories.ExerciseRepository;
import GRUPO1.TP.repositories.ImageRepository;
import GRUPO1.TP.services.ExerciseImageService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ExerciseImageServiceImpl implements ExerciseImageService {
    @Autowired
    ExerciseImageRepository exerciseImageRepository;
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    ImageRepository imageRepository;

    @Override
    public List<ExerciseImage> listAll() {
        List<ExerciseImage> exerciseImages = exerciseImageRepository.findAll();
        for (ExerciseImage e: exerciseImages) {
            e.getExercise().setExercisesImages(null);
            e.getImage().setExercisesImages(null);
        }
        return exerciseImages;
    }

    @Override
    public ExerciseImage save(ExerciseImage exerciseImage) {
        if( exerciseImage.getCorrect_answer()== null ||
                exerciseImage.getCorrect_answer().isEmpty() ||
                exerciseImage.getCorrect_option()== null
        ){
            throw new IncompleteDataException("Not all the necessary data has been entered to register a exerciseImage");
        }
        Exercise exercise= exerciseRepository.findById(exerciseImage.getExercise().getId()).get();
        Image image=imageRepository.findById(exerciseImage.getImage().getId()).get();
        exerciseImage.setExercise(exercise);
        exerciseImage.setImage(image);
        return exerciseImageRepository.save(exerciseImage);
    }

    @Override
    public ExerciseImage findById(Long id) {
        ExerciseImage exerciseImageFound = exerciseImageRepository.findById(id).
                orElse(null);
        if (exerciseImageFound == null) {
            throw new ResourceNotFoundException ("There are no object with the id: "
                    +String.valueOf(id));
        }
        return exerciseImageFound;
    }

    @Override
    public void delete(Long id) {
        ExerciseImage exerciseImage = findById(id);
        exerciseImageRepository.delete(exerciseImage);
    }
}
