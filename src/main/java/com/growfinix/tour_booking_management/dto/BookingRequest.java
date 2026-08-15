package com.growfinix.tour_booking_management.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequest {

    @NotBlank(message = "Destination is required")
    private String destination;

    @NotNull(message = "Travel date is required")
    @Future(message = "Travel date must be in the future")
    private LocalDate travelDate;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotNull(message = "Number of people is required")
    @Min(value = 1, message = "Minimum one person is required")
    private Integer numberOfPeople;

    @NotNull(message = "Total price is required")
    @Positive(message = "Price must be greater than zero")
    private Double totalPrice;

    @NotNull(message = "User ID is required")
    private Long userId;
}