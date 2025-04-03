package GRUPO1.TP.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "exercises_images")
public class ExerciseImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correct_answer;
    private Boolean correct_option;


    @ManyToOne
    @JoinColumn(name = "images_id")
    private Image image;

    @ManyToOne
    @JoinColumn(name = "exercises_id")
    private Exercise exercise;
}
