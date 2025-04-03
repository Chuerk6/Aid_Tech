package GRUPO1.TP.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String avatar;

    @ManyToOne //tabla levels
    @JoinColumn(name = "level_id")
    private Level level;

    //@ManyToOne //tabla users
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentPlan> studentPlans;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<LessonStudent> lessonStudents;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Lesson> completedLessons;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<StudentExercise> studentExercises;

}
