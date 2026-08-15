package com.growfinix.tour_booking_management.controller;

import com.growfinix.tour_booking_management.dto.UserRequest;
import com.growfinix.tour_booking_management.dto.UserResponse;
import com.growfinix.tour_booking_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest request) {

        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
    }

    @GetMapping("/email/{email}")
    public UserResponse getUserByEmail(@PathVariable String email) {

        return userService.getUserByEmail(email);

    }

    @GetMapping("/search/{name}")
    public List<UserResponse> searchUsers(
            @PathVariable String name) {

        return userService.searchUsers(name);

    }

}
