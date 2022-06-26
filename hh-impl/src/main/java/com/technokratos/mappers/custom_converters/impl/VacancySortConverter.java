package com.technokratos.mappers.custom_converters.impl;


import com.technokratos.enums.VacancySort;
import org.springframework.core.convert.converter.Converter;

public class VacancySortConverter implements Converter<String, VacancySort> {
    @Override
    public VacancySort convert(String source) {
        try {
            return VacancySort.valueOf(source.toUpperCase());
        } catch (Exception ex) {
            return VacancySort.TITLE_ASC;
        }
    }
}
