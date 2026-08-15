package com.growfinix.tour_booking_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private LocalDate travelDate;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private Integer numberOfPeople;

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
