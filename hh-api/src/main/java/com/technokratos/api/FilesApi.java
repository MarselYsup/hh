package com.technokratos.api;

import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.FileInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(
        value = "Files micro-service",
        description = "Endpoints to download and upload files"
)
@RequestMapping("/hh/v1")
public interface FilesApi {

    @ApiOperation(
            value = "Save single file",
            response = FileInfoResponse.class,
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping("/upload-file")
    @ResponseStatus(HttpStatus.CREATED)
    FileInfoResponse saveFile(@RequestParam("file") MultipartFile multipart);

    @ApiOperation(
            value = "Save multiple files",
            code = 201
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized")
    })
    @PostMapping("/upload-multiple-files")
    @ResponseStatus(HttpStatus.CREATED)
    List<FileInfoResponse> saveFiles(@RequestParam("file") MultipartFile[] multipart);

    @ApiOperation(
            value = "Download file by id"
    )
    @ApiResponses({
            @ApiResponse(code = 400, message = "Invalid request parameters supplied"),
            @ApiResponse(code = 403, message = "Requested user are not authorized"),
            @ApiResponse(code = 404, message = "Requested file not found")
    })
    @GetMapping("/download-file")
    @ResponseStatus(HttpStatus.OK)
    void getNormalFile(HttpServletResponse response, @RequestBody FileInfoRequest fileInfo);
}
