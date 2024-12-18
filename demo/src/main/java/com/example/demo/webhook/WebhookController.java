package com.example.demo.webhook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.example.demo.Repository.*;
import com.example.demo.Entity.*;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/createUserWithUppercase")
    public ResponseEntity<?> handleCreateUser(@RequestBody CreateUserRequest request) {
        // Validate request fields
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("First name cannot be null or empty");
        }

        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Last name cannot be null or empty");
        }

        // Convert firstName and lastName to uppercase
        String firstNameUpper = request.getFirstName().toUpperCase();
        String lastNameUpper = request.getLastName().toUpperCase();

        // Log transformed names
        System.out.println("Transformed Names: " + firstNameUpper + " " + lastNameUpper);

        // Create a new User entity and set its properties
        User user = new User();
        user.setFirstName(firstNameUpper);
        user.setLastName(lastNameUpper);
        user.setAge(request.getAge());

        // Save the user to the database
        userRepository.save(user);

        // Log the user details after saving
        System.out.println("User saved: " + user);

        // Return the transformed data to Hasura
        return ResponseEntity.ok(new CreateUserResponse("SUCCESS", "User created successfully", user));
    }

    @GetMapping("/home")
    public String getMethodName() {
        return "Welcome to the Webhook Home";
    }
}

// Request class to match Hasura action's input
class CreateUserRequest {
    private String firstName;
    private String lastName;
    private int age;

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

// Response class to send back to Hasura
class CreateUserResponse {
    private String status;
    private String message;
    private User user;

    // Constructor
    public CreateUserResponse(String status, String message, User user) {
        this.status = status;
        this.message = message;
        this.user = user;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
