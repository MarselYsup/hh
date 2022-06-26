package com.technokratos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Entity
@Table(name = "file_info")
public class FileInfoEntity extends BaseEntity {
    @Column(name = "image_id", nullable = false, unique = true)
    private UUID imageId;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "original_file_name", nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private Long size;
}
