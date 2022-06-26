package com.technokratos.mappers.custom_converters.impl;

import com.technokratos.enums.ResumeSort;
import org.springframework.core.convert.converter.Converter;

public class ResumeSortConverter implements Converter<String, ResumeSort> {
    @Override
    public ResumeSort convert(String source) {
        try {
            return ResumeSort.valueOf(source.toUpperCase());
        } catch (Exception ex) {
            return ResumeSort.TITLE_ASC;
        }
    }
}
