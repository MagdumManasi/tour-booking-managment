package com.growfinix.tour_booking_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;
}
