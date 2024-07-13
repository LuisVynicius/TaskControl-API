package com.mevy.taskcontrolapi.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse implements Serializable{

    @JsonFormat(
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        timezone = "UTC",
        shape = JsonFormat.Shape.STRING
    )
    private final Instant moment;
    private final int status;
    private final String title;
    private final String message;
    private List<ValidationError> errors;
    private String stackTrace;

    public void addValidationError(String field, String message) {
        if (Objects.isNull(errors)) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

    @AllArgsConstructor
    @Getter
    @Setter
    private class ValidationError {
        String field;
        String message;
    }

    public String toJson() {
        return String.format(
            "{ \"moment\" : \"%s\", \"status\" : %d, \"title\" : \"%s\", \"message\" : \"%s\" }",
            moment,
            status,
            title,
            message
        );
    }
    
}
