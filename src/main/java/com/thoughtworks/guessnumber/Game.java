package com.thoughtworks.guessnumber;

import lombok.Getter;

public class Game {
    private static final int MAX_GUESS_TIMES = 6;
    private static final String ANSWER_PATTERN = "^[0-9]{4}$";
    private int guessTimes;
    @Getter
    private boolean isGameOver;
    private Rule rule;


    public Game(Generator generator) {
        String answer = generator.generate();
        rule = new Rule(answer);
    }

    public Outcome guess(String answer) {
        if (isGameOver) {
            throw new GameOverException();
        }

        if (answerPatternNotMatch(answer) || AnswerFormatValidation.existsRepeatingNumbers(answer)) {
            throw new GameAnswerException("Wrong Input, Input again.");
        }

        guessTimes++;
        Result result = rule.compare(answer);
        int leftTimes = MAX_GUESS_TIMES - guessTimes;
        isGameOver = result.isWinning() || leftTimes == 0;

        return Outcome.builder()
                .compareResult(result)
                .leftTimes(isGameOver ? 0 : leftTimes)
                .userGuess(answer).build();
    }

    private boolean answerPatternNotMatch(String answer) {
        return !answer.matches(ANSWER_PATTERN);
    }

}
