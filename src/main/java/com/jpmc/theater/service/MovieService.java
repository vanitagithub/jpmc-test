package com.jpmc.theater.service;

import com.jpmc.theater.model.Showing;

import java.math.BigDecimal;

public interface MovieService {
    BigDecimal calculateTicketPrice(Showing showing);
}
