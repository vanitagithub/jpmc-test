package com.jpmc.theater;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import com.jpmc.theater.service.MovieService;
import com.jpmc.theater.service.MovieServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTests {


    private static MovieService movieService;
    @BeforeAll
    static void setUp(){

        movieService = new MovieServiceImpl();

    }
    @Test
    void movieWithSpecialDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), BigDecimal.valueOf(12.5), 1);
        Showing showing = new Showing(spiderMan, 0, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        BigDecimal expected = new BigDecimal(10);
        assertTrue(expected.compareTo(movieService.calculateTicketPrice(showing)) == 0);
        System.out.println(Duration.ofMinutes(90));
    }
    @Test
    void movieWith7thShowDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), BigDecimal.valueOf(12),0);
        Showing showing = new Showing(spiderMan, 7, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        BigDecimal expected = new BigDecimal(11);
        assertTrue(expected.compareTo(movieService.calculateTicketPrice(showing)) == 0);

        System.out.println(Duration.ofMinutes(90));
    }
    @Test
    void movieWithTimingShowDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), BigDecimal.valueOf(12),0);
        Showing showing = new Showing(spiderMan, 7, LocalDate.now().atTime(12, 0));
        BigDecimal expected = new BigDecimal(11);
        assertTrue(expected.compareTo(movieService.calculateTicketPrice(showing)) == 0);

        System.out.println(Duration.ofMinutes(90));
    }
}
