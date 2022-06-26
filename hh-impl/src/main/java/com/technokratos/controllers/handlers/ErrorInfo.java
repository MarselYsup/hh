package com.technokratos.controllers.handlers;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * Информация о ошибке, описывающую исключительную ситуацию
 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorInfo {

    /** Сообщение об ошибке */
    private String message;

    /** Статусный код ошибки */
    private HttpStatus httpStatus;

    /** Наименование исключения */
    private String exceptionName;

    /** Путь, по которому возникла ошибка */
    private String path;
}
