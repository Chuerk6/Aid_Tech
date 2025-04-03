package GRUPO1.TP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "exercises")
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String level;
    private String type_question;
    private String question;
    private String comment;


    @JsonIgnore
    @OneToMany(mappedBy = "exercise")
    private List<ExerciseImage> exercisesImages;

    @JsonIgnore
    @OneToMany(mappedBy = "exercise")
    private List<StudentExercise> studentExercises;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

}
