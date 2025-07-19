package com.pragma.bootcamp.infraestructure.input.res;

import com.pragma.bootcamp.application.dto.request.BootcampRequest;
import com.pragma.bootcamp.application.dto.request.PageRequest;
import com.pragma.bootcamp.application.dto.response.BootcampResponse;
import com.pragma.bootcamp.application.dto.response.PageResponse;
import com.pragma.bootcamp.application.handler.IBootcampHandler;
import com.pragma.bootcamp.domain.enumdata.SortBy;
import com.pragma.bootcamp.domain.enumdata.SortOrder;
import com.pragma.bootcamp.domain.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
@Tag(name = OpenApiConstants.TITLE_BOOTCAMP_REST, description = OpenApiConstants.TITLE_DESCRIPTION_BOOTCAMP_REST)
public class BootcampRestController {


    private final IBootcampHandler bootcampHandler;

    @Operation(
            summary = OpenApiConstants.NEW_BOOTCAMP_TITLE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_201, description = OpenApiConstants.NEW_BOOTCAMP_CREATED_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400, description = OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @PostMapping("/")
    public Mono<BootcampResponse> createBootcamp (@RequestBody BootcampRequest bootcampRequest) {
        return  bootcampHandler.createBootcamp(bootcampRequest);
    }


    @Operation(summary = OpenApiConstants.GET_BOOTCAMP_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.GET_BOOTCAMP_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_404, description = OpenApiConstants.NO_DATA_MESSAGE, content = @Content)
    })
    @GetMapping("/{id}")
    public Mono<BootcampResponse> getBootcampById(@PathVariable Long id) {
        return bootcampHandler.getBootcampById(id);
    }

    @Operation(summary = OpenApiConstants.GET_ALL_BOOTCAMP_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.GET_ALL_BOOTCAMP_MESSAGE,
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = BootcampResponse.class)))),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_404, description =OpenApiConstants.NO_DATA_MESSAGE, content = @Content)
    })
    @GetMapping("/")
    public Mono<PageResponse<BootcampResponse>> getBootcamps(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @Parameter(
                    description = OpenApiConstants.GET_ALL_BOOTCAMP_ORDER_DESCRIPTION,
                    schema = @Schema(implementation = SortOrder.class)
            )
            @RequestParam(defaultValue = "DESC") SortOrder order,
            @Parameter(
                    description = OpenApiConstants.GET_ALL_BOOTCAMP_SORT_BY_DESCRIPTION,
                    schema = @Schema(implementation = SortBy.class)
            )
            @RequestParam(defaultValue = "NAME") SortBy sortBy
    ) {
        return bootcampHandler.getBootcamps(new PageRequest(page, size, order, sortBy));
    }


}
