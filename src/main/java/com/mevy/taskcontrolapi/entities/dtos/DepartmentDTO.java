package com.mevy.taskcontrolapi.entities.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentDTO(
        
        @NotBlank(
            message = "The Department name must have at least one character. "
        )
        @Size(
            min = 1,
            max = 50,
            message = "The Department name must have between 1-50 characters. "
        )
        String name,

        @NotBlank(
            message = "The Department description must have at least one character. "
        )
        @Size(
            min = 1,
            max = 255,
            message = "The Department description must have between 1-255 characters. "
        )
        String description

) {
    
}
