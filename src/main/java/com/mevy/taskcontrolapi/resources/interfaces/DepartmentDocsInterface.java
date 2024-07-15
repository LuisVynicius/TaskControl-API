package com.mevy.taskcontrolapi.resources.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mevy.taskcontrolapi.entities.Department;
import com.mevy.taskcontrolapi.entities.dtos.DepartmentDTO;
import com.mevy.taskcontrolapi.resources.exceptions.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Department Controller", description = "Controller for management departments. ")
public interface DepartmentDocsInterface {
    
    @Operation(
        summary = "Return all departments"
    )
    ResponseEntity<List<Department>> findAll();

    @Operation(
        summary = "Return enableds department",
        description = "Return all departments where DisabledAt is null"
    )
    ResponseEntity<List<Department>> findEnabledDepartments();

    @Operation(
        summary = "Return a department by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "200",
                description = "Department finded successful",
                content = @Content(schema = @Schema(implementation = Department.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Department not found. ",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
        }
    )
    ResponseEntity<Department> findByName(
        @Parameter(
            description = "Provide a name (String value)",
            required = true
        )
        String name
    );

    @Operation(
        summary = "Create a department"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "201",
                description = "Department created successful"
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
            description = "A DepartmentDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = DepartmentDTO.class)),
            required = true
        )
        DepartmentDTO departmentDTO
    );

    @Operation(
        summary = "Delete a department by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "Department deleted successful"
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
        summary = "Update a department by name"
    )
    @ApiResponses(
        value = {
            @ApiResponse(
                responseCode = "204",
                description = "Department updated successful"
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
            description = "A DepartmentDTO object must be used in the request body",
            content = @Content(schema = @Schema(implementation = DepartmentDTO.class)),
            required = true
        )
        DepartmentDTO departmentDTO
    );



}
