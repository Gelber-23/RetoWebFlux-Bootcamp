package com.pragma.bootcamp.infraestructure.configuration;

import com.pragma.bootcamp.infraestructure.converter.DurationConverters;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.convert.R2dbcCustomConversions;

import java.util.List;
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    private final ConnectionFactory connectionFactory;

    public R2dbcConfig(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }
    @Override
    public ConnectionFactory connectionFactory() {
        return this.connectionFactory;
    }
    @Override
    @Bean
    public R2dbcCustomConversions r2dbcCustomConversions() {
        return new R2dbcCustomConversions(
                CustomConversions.StoreConversions.NONE,
                List.of(
                        new DurationConverters.DurationToSecondsConverter(),
                        new DurationConverters.SecondsToDurationConverter()
                )
        );
    }
}