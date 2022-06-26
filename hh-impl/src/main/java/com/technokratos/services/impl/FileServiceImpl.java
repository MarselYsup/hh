package com.technokratos.services.impl;

import com.technokratos.dto.request.FileInfoRequest;
import com.technokratos.dto.response.FileInfoResponse;
import com.technokratos.exceptions.file.FileUnableProcessException;
import com.technokratos.exceptions.not_found.FileNotFoundException;
import com.technokratos.mappers.FileInfoMapper;
import com.technokratos.models.FileInfoEntity;
import com.technokratos.models.MongoFileEntity;
import com.technokratos.repositories.FileInfoRepository;
import com.technokratos.repositories.MongoFileEntityRepository;
import com.technokratos.services.FileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MongoFileEntityRepository mongoRepository;

    private final FileInfoRepository fileInfoRepository;

    private final FileInfoMapper fileInfoMapper;

    public FileInfoResponse saveFile(MultipartFile file) {
        try {
            MongoFileEntity mongoFile = MongoFileEntity.builder()
                    .id(UUID.randomUUID())
                    .content(new Binary(file.getBytes())).build();

            mongoFile = mongoRepository.save(mongoFile);

            FileInfoEntity fileInfo = FileInfoEntity.builder()
                    .originalFileName(file.getOriginalFilename())
                    .mimeType(file.getContentType())
                    .size(file.getSize())
                    .imageId(mongoFile.getId())
                    .build();

            fileInfo = fileInfoRepository.save(fileInfo);
            return fileInfoMapper.toResponse(fileInfo);
        } catch (IOException e){
            throw new FileUnableProcessException(file.getOriginalFilename());
        }
    }

    @Override
    public List<FileInfoResponse> saveFiles(MultipartFile[] files) {
        return Arrays.stream(files).map(this::saveFile).collect(Collectors.toList());
    }

    @Override
    public void writeFileToResponse(HttpServletResponse response, FileInfoRequest requestDto) {
        try {
            FileInfoEntity fileInfo = fileInfoRepository.getById(requestDto.getId());
            MongoFileEntity fileEntity = getFileData(fileInfo).orElseThrow(FileNotFoundException::new);
            response.getOutputStream().write(fileEntity.getContent().getData());
            response.setContentLengthLong(fileInfo.getSize());
            response.setContentType(fileInfo.getMimeType());
            response.setHeader("Content-Disposition",
                    String.format("filename=\"%s\"", fileInfo.getOriginalFileName()));
        } catch (IOException e){
            throw new FileNotFoundException();
        }
    }

    @Override
    public FileInfoEntity getFileInfoById(FileInfoRequest fileInfoRequest) {
        return getFileInfoById(fileInfoRequest.getId());
    }

    @Override
    public FileInfoEntity getFileInfoById(UUID id) {
        return fileInfoRepository.findById(id).orElseThrow(FileNotFoundException::new);
    }

    @Override
    public Optional<MongoFileEntity> getFileData(FileInfoEntity fileInfo) {
        if(Objects.isNull(fileInfo)) {
            return Optional.empty();
        }

        return mongoRepository.findById(fileInfo.getImageId());
    }
}
