package GRUPO1.TP.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageGeneric {
    private int Status;
    private String Message;
    private String Description;
    private Date Timestamp;
}
