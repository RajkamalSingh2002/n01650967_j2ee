package com.example.employeerestapi.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Employee {

    private int id;

    @NotBlank(message = "First name should not be empty or blank")
    @Size(min = 5, max = 20, message = "First name must be between 5 and 20 characters")
    private String firstName;

    @NotBlank(message = "Last name should not be empty")
    private String lastName;

    @NotBlank(message = "Email should not be empty")
    @Email(message = "Please provide a valid email structure")
    private String email;

    public Employee() {
    }
    public Employee(int _id, String firstName, String lastName, String email) {
        id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
