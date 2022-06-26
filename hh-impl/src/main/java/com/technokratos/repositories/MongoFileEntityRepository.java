package com.technokratos.repositories;

import com.technokratos.models.MongoFileEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface MongoFileEntityRepository extends MongoRepository<MongoFileEntity, UUID> {
}
