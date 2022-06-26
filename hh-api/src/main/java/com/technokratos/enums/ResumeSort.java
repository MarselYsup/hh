package com.technokratos.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Direction;
import static org.springframework.data.domain.Sort.Direction.*;

@RequiredArgsConstructor
@Getter
public enum ResumeSort {
    TITLE_ASC("title", ASC),
    TITLE_DESC("title", DESC),
    SALARY_ASC("salary", ASC),
    SALARY_DESC("salary", DESC);


    private final String value;

    private final Direction sortType;

}
