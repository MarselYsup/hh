package com.technokratos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.UUID;

@Document(collection = "hhfiles")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MongoFileEntity {
    @Id
    private UUID id;

    private Binary content;
}
