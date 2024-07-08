package com.mevy.taskcontrolapi.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserCreateDTO(

        @NotBlank(
            message = "The User fullName must have at least one character. "
        )
        @Size(
            min = 1,
            max = 60,
            message = "The User fullName must have between 1-60 characters. "
        )
        String fullName,

        @NotBlank(
            message = "The User email must have at least one character. "
        )
        @Size(
            min = 1,
            max = 255,
            message = "The User email must have between 1-255 characters. "
        )
        String email,

        @NotBlank(
            message = "The User password must have at least one character. "
        )
        @Size(
            min = 1,
            max = 255,
            message = "The User password must have between 1-255 characters. "
        )
        String password

) {
    
}
