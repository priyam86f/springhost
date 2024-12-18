package com.example.demo.webhook;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    // This will be invoked by Hasura for the custom action
    @PostMapping("/createUserWithUppercase")
    public ResponseEntity<?> handleCreateUser(@RequestBody CreateUserRequest request) {
        // Validate input
        if (request.getFirstName() == null || request.getLastName() == null) {
            return ResponseEntity.badRequest().body("First name and Last name cannot be null");
        }

        // Convert firstName and lastName to uppercase
        String firstNameUpper = request.getFirstName().toUpperCase();
        String lastNameUpper = request.getLastName().toUpperCase();

        // Log for debugging
        System.out.println("Transformed Names: " + firstNameUpper + " " + lastNameUpper);

        // Return the transformed data to Hasura
        return ResponseEntity.ok(new CreateUserResponse(firstNameUpper, lastNameUpper, request.getAge()));
    }

    @GetMapping("/home")
    public String getMethodName() {
        return "new String();";
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
    private String firstName;
    private String lastName;
    private int age;

    // Constructor
    public CreateUserResponse(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    // Getters and Setters
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
