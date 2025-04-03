package GRUPO1.TP.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DTOToken {
    String jwtToken;
    Long id;
    String authorities;
}
