package com.technokratos.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InfoMail {
    /** Адрес получателя */
    private String to;

    /** Тема сообщения */
    private String subject;

    /** Имя шаблона */
    private String templateName;

    /** Данные */
    private Map<String, String> data;
}
