package com.thoughtworks.guessnumber;

public class AnswerFormatValidation {

    private static final String ANSWER_PATTERN = "^[0-9]{4}$";

    public static boolean failedValidate(String answer) {
        return answerPatternNotMatch(answer) || existsRepeatingNumbers(answer);
    }


    private static boolean answerPatternNotMatch(String answer) {
        return !answer.matches(ANSWER_PATTERN);
    }

    private static boolean existsRepeatingNumbers(String number) {
        for (int charIndex = 0; charIndex < number.length(); charIndex++) {
            char ch = number.charAt(charIndex);
            if (number.lastIndexOf(ch) != charIndex) {
                return true;
            }
        }
        return false;
    }
}
