package ru.cft.test_cft.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class QParamNotSupportedException extends RuntimeException {
    private final HttpStatus status;

    public QParamNotSupportedException(String msg, HttpStatus status) {
        super(msg);
        this.status = status;
    }
}
