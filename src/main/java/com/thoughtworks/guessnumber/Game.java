package com.thoughtworks.guessnumber;

import lombok.Getter;

public class Game {
    private static final int MAX_GUESS_TIMES = 6;
    private static final String ANSWER_PATTERN = "^[0-9]{4}$";
    private Generator generator;
    private String answer;
    private int guessTimes;
    @Getter
    private boolean isGameOver;
    private Rule rule;


    public Game(Generator generator) {
        this.generator = generator;
        answer = this.generator.generate();
        rule = new Rule(answer);
    }

    public Outcome guess(String answer) {
        if (isGameOver) {
            throw new GameOverException();
        }

        if (!answer.matches(ANSWER_PATTERN) || existsRepeatingNumbers(answer)) {
            throw new GameAnswerException("Wrong Input, Input again.");
        }

        guessTimes++;
        Result result = rule.compare(answer);
        boolean winning = result.getNumOfA() == 4;
        int leftTimes = MAX_GUESS_TIMES - guessTimes;
        isGameOver = winning || leftTimes == 0;

        return Outcome.builder()
                .compareResult(result)
                .isWinning(winning)
                .leftTimes(isGameOver ? 0 : leftTimes)
                .userGuess(answer).build();
    }

    private boolean existsRepeatingNumbers(String number) {
        for (int charIndex = 0; charIndex < number.length(); charIndex++) {
            char ch = number.charAt(charIndex);
            if (number.lastIndexOf(ch) != charIndex) {
                return true;
            }
        }
        return false;
    }
}
