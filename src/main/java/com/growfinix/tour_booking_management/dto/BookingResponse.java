package com.growfinix.tour_booking_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookingResponse {

    private Long id;

    private String destination;

    private LocalDate travelDate;

    private LocalDate bookingDate;

    private Integer numberOfPeople;

    private Double totalPrice;

    private String userName;
}
