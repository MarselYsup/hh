package com.technokratos.mappers;

public interface BasicMapper<Entity, Request, Response> {
    Entity toEntity(Request request);
    Response toResponse(Entity entity);
}
