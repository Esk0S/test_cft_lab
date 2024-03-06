package ru.cft.test_cft;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.cft.test_cft.app.mapper.IntervalDigitMapper;
import ru.cft.test_cft.app.mapper.IntervalLetterMapper;

@Configuration
public class Config {
    @Bean
    public IntervalLetterMapper getLetterMapper() {
        return new IntervalLetterMapper();
    }

    @Bean
    public IntervalDigitMapper getDigitMapper() {
        return new IntervalDigitMapper();
    }
}
