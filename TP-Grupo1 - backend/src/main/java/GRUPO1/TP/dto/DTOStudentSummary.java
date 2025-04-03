package GRUPO1.TP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOStudentSummary {
    private String name;
    private Long completedLessons;
    private String currentPlan;
    private String level;




}

