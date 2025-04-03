package GRUPO1.TP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "lesson")
    private List<Exercise> exercise;


    @JsonIgnore
    @OneToMany(mappedBy = "lesson")
    private List<LessonStudent> lessonStudents;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

//    public Lesson(Long aLong, String vocabulario, String nivelConocedor, Object o, Object o1)
//    {
//        this.id = id;
//        this.theme = theme;
//        this.description = description;
//    }
}
