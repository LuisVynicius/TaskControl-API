package com.mevy.taskcontrolapi.resources.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mevy.taskcontrolapi.entities.User;
import com.mevy.taskcontrolapi.entities.dtos.UserCreateDTO;
import com.mevy.taskcontrolapi.entities.dtos.UserUpdateDTO;
import com.mevy.taskcontrolapi.resources.exceptions.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Controller", description = "Controller for management users. ")
public interface UserDocsInterface {
    
    @Operation(
        summary = "Return all users"
    )
    ResponseEntity<List<User>> findAll();

    @Operation(
        summary = "Return a User by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "User found successful",
                content = @Content(schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "User not found. ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<User> findByfullName(
        @Parameter(
            description = "Provide a fullName (String value)",
            required = true
        )
        String fullName
    );

    @Operation(
        summary = "Create a User"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "User created successful"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Database integrity violation ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<Void> create(
        @RequestBody(
            description = "A UserCreateDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = UserCreateDTO.class)),
            required = true
        )
        UserCreateDTO userCreateDTO
    );

    @Operation(
        summary = "Delete a User by fullName"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "User deleted successful"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Database integrity violation ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<Void> deleteByFullName(
        @Parameter(
            description = "Provide a fullName (String value)",
            required = true
        )
        String fullName
    );

    ResponseEntity<Void> updateByCurrentUser(
        @RequestBody(
            description = "A UserUpdateDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = UserUpdateDTO.class)),
            required = true
        )
        UserUpdateDTO userUpdateDTO
    );

}
