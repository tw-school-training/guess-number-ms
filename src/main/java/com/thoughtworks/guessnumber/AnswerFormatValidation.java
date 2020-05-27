package com.thoughtworks.guessnumber;

public class AnswerFormatValidation { 
    public static boolean existsRepeatingNumbers(String number) {
        for (int charIndex = 0; charIndex < number.length(); charIndex++) {
            char ch = number.charAt(charIndex);
            if (number.lastIndexOf(ch) != charIndex) {
                return true;
            }
        }
        return false;
    }
}
