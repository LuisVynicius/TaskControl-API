package com.mevy.taskcontrolapi.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskDTO(

        @NotBlank(
            message = "The Task name must have at least one character. "
        )
        @Size(
            min = 1,
            max = 50,
            message = "The Task name must have between 1-50 characters. "
        )
        String name,

        @NotBlank(
            message = "The Task description must have at least one character. "
        )
        @Size(
            min = 1,
            max = 255,
            message = "The Task description must have between 1-255 characters. "
        )
        String description,

        @NotNull(
            message = "The task must have a department"
        )
        Long id

) {
    
}
