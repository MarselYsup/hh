package com.technokratos.services;

import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.FileInfoResponse;
import com.technokratos.models.FileInfoEntity;
import com.technokratos.models.MongoFileEntity;
import org.springframework.web.multipart.MultipartFile;
import com.technokratos.models.FileInfoEntity;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


public interface FileService {
    FileInfoResponse saveFile(MultipartFile file);

    List<FileInfoResponse> saveFiles(MultipartFile[] files);

    void writeFileToResponse(HttpServletResponse response, FileInfoRequest requestDto);

    FileInfoEntity getFileInfoById(FileInfoRequest fileInfoRequest);

    FileInfoEntity getFileInfoById(UUID id);

    Optional<MongoFileEntity> getFileData(FileInfoEntity fileInfo);
}
