package com.thoughtworks.guessnumber;

public class Game {
    private static final int MAX_GUESS_TIMES = 6;
    private Generator generator;
    private String answer;
    private int guessTimes;
    private Rule rule;


    public Game(Generator generator) {
        this.generator = generator;
        answer = this.generator.generate();
        rule = new Rule(answer);
    }

    public Outcome guess(String number) {
        if (guessTimes < MAX_GUESS_TIMES) {
            guessTimes++;
            Result result = rule.compare(number);

            boolean winning = result.getNumOfA() == 4;
            return Outcome.builder()
                    .compareResult(result)
                    .isWinning(winning)
                    .leftTimes(MAX_GUESS_TIMES - guessTimes)
                    .userGuess(number).build();
        }
        return null;
    }
}
