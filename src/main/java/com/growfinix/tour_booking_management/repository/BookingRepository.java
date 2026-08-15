package com.growfinix.tour_booking_management.repository;


import com.growfinix.tour_booking_management.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByDestination(String destination);

    List<Booking> findByUserId(Long userId);

    List<Booking> findByTravelDate(LocalDate travelDate);

}
