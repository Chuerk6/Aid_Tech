package GRUPO1.TP.entities;

import javax.persistence.*;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students_exercises")
public class StudentExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date answer_date;
    private Boolean correct;

    @ManyToOne
    @JoinColumn(name= "exercise_id")
    private Exercise exercise;

    @ManyToOne
    @JoinColumn(name= "student_id")
    private Student student;




}
