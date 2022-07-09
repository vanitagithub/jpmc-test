package com.jpmc.theater.config;

import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.MovieService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@TestConfiguration
public class MovieServiceTestConfig {

    @Bean
    public MovieService movieService() {
        return new MovieService() {
            @Override
            public BigDecimal calculateTicketPrice(Showing showing) {
                return null;
            }
            // implement methods
        };
    }
}