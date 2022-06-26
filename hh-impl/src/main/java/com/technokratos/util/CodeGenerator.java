package com.technokratos.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

/** Утилитный класс для генерации кода */

@UtilityClass
public class CodeGenerator {

    private final String CAPITAL_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final String SMALL_CHARS = "abcdefghijklmnopqrstuvwxyz";

    private final String NUMBERS = "0123456789";

    private final char SEPARATOR = '-';

    private final int DEFAULT_COUNT_OF_GROUP = 3;

    private final int DEFAULT_SIZE_OF_GROUP = 4;
    /**
     * Метод для генерации кода
     *
     * @param countOfGroup количество групп в коде, которые будут разделены специальным знаком
     * @param sizeOfGroup размер одной группы
     *
     * @return сгенерируемый код состоящий из countOfGroup групп, каждая из которых состоит из sizeOfGroup
     * */
    public String generate(int countOfGroup, int sizeOfGroup) {
        String allSymbols = CAPITAL_CHARS + SMALL_CHARS + NUMBERS;

        StringBuilder generatedPassword = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < countOfGroup; i++) {
            for (int j = 0; j < sizeOfGroup; j++) {
                generatedPassword.append(allSymbols.charAt(random.nextInt(allSymbols.length())));
            }
            if(i != countOfGroup-1) {
                generatedPassword.append(SEPARATOR);
            }
        }

        return generatedPassword.toString();
    }

    public String generate() {
        return generate(DEFAULT_COUNT_OF_GROUP, DEFAULT_SIZE_OF_GROUP);
    }
}
