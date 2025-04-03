package GRUPO1.TP.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students_plans")
public class StudentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date star_date;
    private Date end_date;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

}
