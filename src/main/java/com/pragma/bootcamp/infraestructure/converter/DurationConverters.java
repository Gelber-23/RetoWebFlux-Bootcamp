package com.pragma.bootcamp.infraestructure.converter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.time.Duration;

public class DurationConverters {

    @WritingConverter
    public static class DurationToSecondsConverter implements Converter<Duration, Long> {
        @Override
        public Long convert(Duration source) {
            return source == null ? null : source.getSeconds();
        }
    }

    @ReadingConverter
    public static class SecondsToDurationConverter implements Converter<Long, Duration> {
        @Override
        public Duration convert(Long source) {
            return source == null ? null : Duration.ofSeconds(source);
        }
    }
}
