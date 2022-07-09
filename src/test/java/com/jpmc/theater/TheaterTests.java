package com.jpmc.theater;

import com.jpmc.theater.model.Customer;
import com.jpmc.theater.model.LocalDateProvider;
import com.jpmc.theater.model.Reservation;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
//        System.out.println("You have to pay " + reservation.getTotalFee());
        BigDecimal expected = new BigDecimal(50);

        assertTrue(expected.compareTo(reservation.totalFee()) == 0);

    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printScheduleInJson();

    }
}
