package com.growfinix.tour_booking_management.service;

import com.growfinix.tour_booking_management.dto.BookingRequest;
import com.growfinix.tour_booking_management.dto.BookingResponse;

import com.growfinix.tour_booking_management.model.Booking;
import com.growfinix.tour_booking_management.model.User;
import com.growfinix.tour_booking_management.repository.BookingRepository;
import com.growfinix.tour_booking_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;



    public BookingResponse createBooking(BookingRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Booking booking = new Booking();

        booking.setDestination(request.getDestination());
        booking.setTravelDate(request.getTravelDate());
        booking.setBookingDate(request.getBookingDate());
        booking.setNumberOfPeople(request.getNumberOfPeople());
        booking.setTotalPrice(request.getTotalPrice());

        booking.setUser(user);

        Booking savedBooking = bookingRepository.save(booking);

        return convertToResponse(savedBooking);
    }

    public List<BookingResponse> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }


    public BookingResponse getBookingById(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return convertToResponse(booking);
    }

    public BookingResponse updateBooking(Long id, BookingRequest request) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        booking.setDestination(request.getDestination());
        booking.setTravelDate(request.getTravelDate());
        booking.setBookingDate(request.getBookingDate());
        booking.setNumberOfPeople(request.getNumberOfPeople());
        booking.setTotalPrice(request.getTotalPrice());
        booking.setUser(user);

        Booking updatedBooking = bookingRepository.save(booking);

        return convertToResponse(updatedBooking);
    }

    public void deleteBooking(Long id) {

        bookingRepository.deleteById(id);

    }

    public List<BookingResponse> getBookingsByDestination(String destination) {

        return bookingRepository.findByDestination(destination)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<BookingResponse> getBookingsByUser(Long userId) {

        return bookingRepository.findByUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public List<BookingResponse> getBookingsByTravelDate(LocalDate travelDate) {

        return bookingRepository.findByTravelDate(travelDate)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    private BookingResponse convertToResponse(Booking booking) {

        return new BookingResponse(
                booking.getId(),
                booking.getDestination(),
                booking.getTravelDate(),
                booking.getBookingDate(),
                booking.getNumberOfPeople(),
                booking.getTotalPrice(),
                booking.getUser().getFullName()
        );
    }
}
