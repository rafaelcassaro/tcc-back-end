package trabalho.conclusao.curso.tcc.controllers.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import trabalho.conclusao.curso.tcc.services.exceptions.DatabaseException;
import trabalho.conclusao.curso.tcc.services.exceptions.ResourceNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) //toda excessao ResourceNotFoundException lançada ele vai pegar ela e tratar no q ta nessa funcao
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }


    @ExceptionHandler(DatabaseException.class) //toda excessao ResourceNotFoundException lançada ele vai pegar ela e tratar no q ta nessa funcao
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
