package com.bridgelabz.address_book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z ]{1,49}$", message = "Name must start with a capital letter, contain only letters and spaces, and be 2-50 characters long")
    private String name;

    @NotBlank(message = "Phone number cannot be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be a 10-digit number")
    private String phoneNumber;

    @NotBlank(message = "Address cannot be empty")
    private String address;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "State cannot be empty")
    private String state;

    @NotBlank(message = "Zip Code cannot be empty")
    @Pattern(regexp = "^\\d{6}$", message = "Zip Code must be a 6-digit number")
    private String zipCode;

    private String email;
}