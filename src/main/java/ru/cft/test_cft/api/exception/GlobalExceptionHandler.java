package ru.cft.test_cft.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(QParamNotSupportedException.class)
    protected ResponseEntity<ExceptionMessage> handleQParamNotSupported(QParamNotSupportedException ex) {
        return new ResponseEntity<>(new ExceptionMessage(ex.getMessage()), ex.getStatus());
    }

    @Getter
    @AllArgsConstructor
    protected static class ExceptionMessage {
        private String message;
    }
}
