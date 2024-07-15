package com.mevy.taskcontrolapi.resources.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mevy.taskcontrolapi.entities.Task;
import com.mevy.taskcontrolapi.entities.dtos.TaskDTO;
import com.mevy.taskcontrolapi.resources.exceptions.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Task controller", description = "Controller for management tasks. ")
public interface TaskDocsInterface {
    
    @Operation(
        summary = "Return all tasks"
    )
    ResponseEntity<List<Task>> findAll();

    @Operation(
        summary = "Return all tasks by a department name"
    )
    ResponseEntity<List<Task>> findByDepartmentName(
        @Parameter(
            description = "Provide a name (String value)",
            required = true
        )
        String name
    );

    @Operation(
        summary = "Create a Task"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Task created successful"
            )
        }
    )
    ResponseEntity<Void> create(
        @RequestBody
        TaskDTO taskDTO
    );

    @Operation(
        summary = "Delete a task by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "Task deleted successful"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Database integrity violation ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<Void> deleteByName(
        @Parameter(
            description = "Provide a name (String value)",
            required = true
        )
        String name
    );

    @Operation(
        summary = "Update a task by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "Task updated successful"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Database integrity violation ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<Void> updateByName(
        @Parameter(
            description = "Provide a name (String value)",
            required = true
        )
        String name,
        @RequestBody(
            description = "A TaskDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = TaskDTO.class)),
            required = true
        )
        TaskDTO taskDTO
    );

}
