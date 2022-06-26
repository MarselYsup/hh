package com.technokratos.controllers;

import com.technokratos.api.FilesApi;
import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.FileInfoResponse;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class FilesController implements FilesApi {

    private final FileService fileService;

    @Override
    public FileInfoResponse saveFile(MultipartFile multipart) {
        return fileService.saveFile(multipart);
    }

    @Override
    public List<FileInfoResponse> saveFiles(MultipartFile[] multiparts) {
        return fileService.saveFiles(multiparts);
    }

    @Override
    public void getNormalFile(HttpServletResponse response, FileInfoRequest fileInfo) {
        fileService.writeFileToResponse(response, fileInfo);
    }
}