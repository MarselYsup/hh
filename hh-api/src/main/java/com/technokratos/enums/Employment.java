package com.technokratos.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Employment {
    FULL_TIME("полная занятость"),
    PART_TIME("частичная занятость"),
    ONE_TIME("разовое задание"),
    VOLUNTEERING("волонтерство"),
    WORK_PLACEMENT("стажировка");

    private final String description;
}