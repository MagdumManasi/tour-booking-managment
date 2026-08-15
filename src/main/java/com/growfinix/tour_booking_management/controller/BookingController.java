package com.growfinix.tour_booking_management.controller;

import com.growfinix.tour_booking_management.dto.BookingRequest;
import com.growfinix.tour_booking_management.dto.BookingResponse;
import com.growfinix.tour_booking_management.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse createBooking(
            @Valid @RequestBody BookingRequest request) {

        return bookingService.createBooking(request);
    }

    @GetMapping
    public List<BookingResponse> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingResponse getBookingById(@PathVariable Long id) {

        return bookingService.getBookingById(id);

    }

    @PutMapping("/{id}")
    public BookingResponse updateBooking(
            @PathVariable Long id,
            @Valid @RequestBody BookingRequest request){

        return bookingService.updateBooking(id, request);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable Long id) {

        bookingService.deleteBooking(id);

    }

    @GetMapping("/destination/{destination}")
    public List<BookingResponse> getBookingsByDestination(
            @PathVariable String destination) {

        return bookingService.getBookingsByDestination(destination);

    }

    @GetMapping("/user/{userId}")
    public List<BookingResponse> getBookingsByUser(
            @PathVariable Long userId) {

        return bookingService.getBookingsByUser(userId);

    }

    @GetMapping("/date/{travelDate}")
    public List<BookingResponse> getBookingsByTravelDate(
            @PathVariable LocalDate travelDate) {

        return bookingService.getBookingsByTravelDate(travelDate);

    }
}
