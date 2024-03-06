package ru.cft.test_cft.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.cft.test_cft.api.exception.QParamNotSupportedException;
import ru.cft.test_cft.app.service.IntervalService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Log4j2
@RestController
@RequestMapping(value = "/api/v1/intervals", produces = "application/json")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Controller {
    private final IntervalService intervalService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/merge", consumes = APPLICATION_JSON_VALUE)
    public Object createInterval(@RequestParam String kind, @RequestBody List<List<?>> intervalList) {
        if (kind.equals("digits")) {
            return intervalService.saveDigitIntervals(intervalList);
        } else if (kind.equals("letters")) {
            return intervalService.saveLetterIntervals(intervalList);
        }
        String errMsg = "Unknown query parameter: " + kind;
        log.error(errMsg);

        throw new QParamNotSupportedException(errMsg, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/min", produces = APPLICATION_JSON_VALUE)
    public Object getMinInterval(@RequestParam String kind) {
        if (kind.equals("digits")) {
            return intervalService.getMinDigitInterval();
        } else if (kind.equals("letters")) {
            return intervalService.getMinLetterInterval();
        }
        String errMsg = "Unknown query parameter: " + kind;
        log.error(errMsg);

        throw new QParamNotSupportedException(errMsg, HttpStatus.BAD_REQUEST);
    }
}
