package com.mevy.taskcontrolapi.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(

        @NotBlank(
            message = "The User fullName must have at least one character. "
        )
        @Size(
            min = 1,
            max = 60,
            message = "The User fullName must have between 1-60 characters. "
        )
        String fullName,

        String password

) {
    
}
