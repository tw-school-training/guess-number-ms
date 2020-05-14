package com.thoughtworks.guessnumber;

public class Game {
    private static final int MAX_GUESS_TIMES = 6;
    private static final String ANSWER_PATTERN = "^[0-9]{4}$";
    private Generator generator;
    private String answer;
    private int guessTimes;
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
            throw new GameAnswerException();
        }

        guessTimes++;
        Result result = rule.compare(answer);
        boolean winning = result.getNumOfA() == 4;
        isGameOver = winning || guessTimes == MAX_GUESS_TIMES;

        return Outcome.builder()
                .compareResult(result)
                .isWinning(winning)
                .leftTimes(MAX_GUESS_TIMES - guessTimes)
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
