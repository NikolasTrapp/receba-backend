package br.nikolastrapp.receba.handlers;

import br.nikolastrapp.receba.exceptions.RecebaGenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Objects.isNull;

@Slf4j
@ControllerAdvice
public class RecebaExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        var detail = "Malformed message body. Check syntax";
        var title = "Malformed JSON";
        var err = RecebaGenericException.builder()
                //.instant(LocalDateTime.now())
                .title(title)
                .detail(detail)
                .status(status.value())
                .build();

        return handleExceptionInternal(ex, err, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR CAPTURED: {}", ex.getLocalizedMessage(), ex);

        if (isNull(body)) {
            body = RecebaGenericException.builder()
//                    .instant(LocalDateTime.now())
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = RecebaGenericException.builder()
//                    .instant(LocalDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

}
