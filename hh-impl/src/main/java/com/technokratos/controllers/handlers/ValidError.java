package com.technokratos.controllers.handlers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/** валидационная ошибка конкретного поля */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidError {

    /** невалидное поле */
    private String fieldOfError;

    /** сообщение об ошибке */
    private String message;
}
