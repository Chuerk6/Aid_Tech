package GRUPO1.TP.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessageGeneric resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return message;
    }

    @ExceptionHandler(IncompleteDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessageGeneric incompleteDataExceptionHandler(IncompleteDataException ex, WebRequest request){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return message;
    }

    @ExceptionHandler(KeyRepeatedDataException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ErrorMessageGeneric keyRepeatedDataExceptionHandler(KeyRepeatedDataException ex, WebRequest request){
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.NOT_ACCEPTABLE.value(),
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return message;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessageGeneric globalExceptionHandler(RuntimeException ex, WebRequest request) {
        ErrorMessageGeneric message = new ErrorMessageGeneric(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getDescription(false),
                new Date()
        );
        return message;
    }

}