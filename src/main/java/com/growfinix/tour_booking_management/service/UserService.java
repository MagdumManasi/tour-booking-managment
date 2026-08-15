package com.growfinix.tour_booking_management.service;

import com.growfinix.tour_booking_management.dto.UserRequest;
import com.growfinix.tour_booking_management.dto.UserResponse;
import com.growfinix.tour_booking_management.model.User;
import com.growfinix.tour_booking_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;


    public UserResponse createUser(UserRequest request) {

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        User savedUser = userRepository.save(user);

        return convertToResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private UserResponse convertToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone()
        );
    }

    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToResponse(user);
    }

    public UserResponse updateUser(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        User updatedUser = userRepository.save(user);

        return convertToResponse(updatedUser);
    }

    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return convertToResponse(user);
    }

    public List<UserResponse> searchUsers(String name) {

        return userRepository.findByFullNameContaining(name)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }
}
