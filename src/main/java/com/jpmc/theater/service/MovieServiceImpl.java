package com.jpmc.theater.service;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MovieServiceImpl implements MovieService {
    private static int MOVIE_CODE_SPECIAL = 1;

    @Override
    public BigDecimal calculateTicketPrice(Showing showing) {
        return showing.getMovie().getTicketPrice().subtract(getDiscount(showing));
    }

    public BigDecimal getDiscount(Showing showing) {
        List<BigDecimal> discounts = new ArrayList<>();
        Movie movie = showing.getMovie();
        int showSequence = showing.getSequenceOfTheDay();
        if (MOVIE_CODE_SPECIAL == movie.getSpecialCode()) {
            discounts.add(movie.getTicketPrice().multiply(BigDecimal.valueOf(0.2)));  // 20% discount for special movie
        }
        switch(showSequence) {
            case 1: discounts.add(BigDecimal.valueOf(3)); // $3 discount for 1st show
                break;
            case 2: discounts.add(BigDecimal.valueOf(2)); // $3 discount for 1st show
                break;
            case 7: discounts.add(BigDecimal.valueOf(1)); // $3 discount for 1st show
                break;
        }
        // Any movies showing starting between 11AM ~ 4pm, you'll get 25% discount
        if(showing.getStartTime().toLocalTime().isAfter(LocalTime.of(10,59)) &&
                showing.getStartTime().toLocalTime().isAfter(LocalTime.of(16,01))) {
            discounts.add(movie.getTicketPrice().multiply(BigDecimal.valueOf(0.25) ));
        }
        // biggest discount wins
        System.out.println( Collections.max(discounts));
        return Collections.max(discounts);
    }

}
