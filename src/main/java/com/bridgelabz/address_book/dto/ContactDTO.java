package com.bridgelabz.address_book.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;



//Using lombok
@Data // Generates getters, setters, toString, equals, and hashCode
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    @NotBlank(message = "Name is required")
    @Pattern(
            regexp = "^[A-Z][a-zA-Z ]{1,49}$",
            message = "Name must start with a capital letter and be 2-50 characters, letters and spaces only"
    )
    private String name;

    private String email;
}