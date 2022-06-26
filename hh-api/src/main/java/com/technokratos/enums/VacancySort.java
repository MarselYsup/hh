package com.technokratos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@Getter
public enum VacancySort {
    TITLE_ASC("title", ASC),
    TITLE_DESC("title", DESC),
    MIN_SALARY_ASC("minSalary", ASC),
    MIN_SALARY_DESC("minSalary", DESC),
    MAX_SALARY_ASC("maxSalary", ASC),
    MAX_SALARY_DESC("maxSalary",DESC);


    private final String value;

    private final Direction sortType;

}
