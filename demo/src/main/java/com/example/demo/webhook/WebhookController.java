package com.example.demo.webhook;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping("/customAction")
    public ResponseEntity<?> handleCustomAction(@RequestBody CustomActionRequest request) {
        // Example logic for processing incoming request
        System.out.println("Received request: " + request);

        // Example: return a response back to Hasura
        return ResponseEntity.ok(new CustomActionResponse("Action Completed"));
    }

    @GetMapping("/home")
    public String getMethodName() {
        return new String("hello");
    }
    
}

// Request class to match Hasura action's input
class CustomActionRequest {
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

    // Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "CustomActionRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

// Response class to send back to Hasura
class CustomActionResponse {
    private String message;

    // Constructor
    public CustomActionResponse(String message) {
        this.message = message;
    }

    // Getter and Setter
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Optional: Override toString() for debugging
    @Override
    public String toString() {
        return "CustomActionResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
