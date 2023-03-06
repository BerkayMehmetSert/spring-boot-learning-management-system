package com.bms.learningmanagementsystem.core.utilities.generator;

import java.util.Random;

public class NumberGenerator {
    private NumberGenerator() {
        throw new IllegalStateException("Utility class");
    }

    private static final String LOWER_CASE_ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE_ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";

    public static String generateRandomString(int length) {
        var random = new Random();
        var sb = new StringBuilder();

        String characters = LOWER_CASE_ALPHABETS + UPPER_CASE_ALPHABETS + NUMBERS;

        for (int i = 0; i < length; i++) {
            var index = random.nextInt(characters.length());
            var randomChar = characters.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

}
