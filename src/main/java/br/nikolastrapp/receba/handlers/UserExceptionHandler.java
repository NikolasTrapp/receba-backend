package br.nikolastrapp.receba.handlers;

import br.nikolastrapp.receba.exceptions.RecebaGenericException;
import br.nikolastrapp.receba.exceptions.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;


@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<RecebaGenericException> genericUserExceptionHandler(UserException err) {

        var body = RecebaGenericException.builder()
                .status(500)
                .title(err.getCause().getLocalizedMessage())
                .detail(err.getMessage())
                .instant(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }

}
