package com.mevy.taskcontrolapi.resources.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mevy.taskcontrolapi.entities.UserInformations;
import com.mevy.taskcontrolapi.entities.dtos.UserInformationsUpdateDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface UserInformationsDocsInterface {
    
    @Operation(
        summary = "Return all UserInformations"
    )
    ResponseEntity<List<UserInformations>> findAll();

    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "UserInformations updated successful"
            )
        }
    )
    public ResponseEntity<Void> updateByCurrentUser(
        @RequestBody(
            description = "A UserInformationsUpdateDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = UserInformationsUpdateDTO.class)),
            required = true
        )
        UserInformationsUpdateDTO userInformationsUpdateDTO
    );
}
