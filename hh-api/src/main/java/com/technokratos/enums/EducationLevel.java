package com.technokratos.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EducationLevel {
    SECONDARY("среднее образование"),
    FULL_SECONDARY("полное среднее образование"),
    SPECIAL_SECONDARY("среднее специальное образование"),
    BACHELOR("высшее образование, бакалвриат"),
    SPECIALITY("высшее образование, специалитет"),
    MAGISTRACY("высшее образование, магистратура");

    private final String description;
}
