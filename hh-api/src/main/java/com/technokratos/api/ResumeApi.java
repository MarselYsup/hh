package com.technokratos.api;

import com.technokratos.dto.request.ResumeRequest;
import com.technokratos.dto.response.ResumeListResponse;
import com.technokratos.dto.response.ResumeResponse;
import com.technokratos.enums.ResumeSort;
import org.springframework.data.jpa.domain.Specification;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(
        value = "Resume logic micro-service",
        description = "Create, update and get resume, also in file"
)
@RequestMapping("/hh/v1/resume")
public interface ResumeApi<PRINCIPAL, ENTITY> {

    @ApiOperation(
            value = "Create new resume",
            response = ResumeResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResumeResponse createResume(@RequestBody ResumeRequest resumeRequest, @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Update resume",
            response = ResumeResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested resume not found")
    })
    @PutMapping(path = "/{resume-id}",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    ResumeResponse updateResume(@RequestBody ResumeRequest resumeRequest, @PathVariable("resume-id") UUID id,
                                @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get resume by id",
            response = ResumeResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested resume not found")
    })
    @GetMapping(path = "/{resume-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResumeResponse getResume(@PathVariable("resume-id") UUID id, @AuthenticationPrincipal PRINCIPAL user);

    @ApiOperation(
            value = "Get list of resume by search properties",
            response = ResumeListResponse.class
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @GetMapping(produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    ResumeListResponse getResumeList(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
                                     @RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
                                     @RequestParam(name = "sort", required = false, defaultValue = "TITLE_ASC")
                                             ResumeSort resumeSort,
                                     Specification<ENTITY> resumeSpec);

    @ApiOperation(
            value = "Get resume by id in docx file"
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested resume not found")
    })
    @GetMapping("/download/{resume-id}")
    @ResponseStatus(HttpStatus.OK)
    void getResumeFile(HttpServletResponse response, @PathVariable("resume-id") UUID id,
                       @AuthenticationPrincipal PRINCIPAL user);
}