package com.thoughtworks.guessnumber;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

public class GameTest {

    @Test
    public void should_fail_when_guess_number_given_guess_times_less_than_6() {

        String number = "1245";
        Generator generator = Mockito.mock(Generator.class);
        Mockito.when(generator.generate()).thenReturn("1234");

        Game game = new Game(generator);
        Outcome outcome = game.guess(number);

        Assert.assertFalse(outcome.isWinning());
        Assert.assertEquals(2, outcome.getCompareResult().getNumOfA());
        Assert.assertEquals(1, outcome.getCompareResult().getNumOfB());
        Assert.assertEquals(number, outcome.getUserGuess());
        Assert.assertEquals(5, outcome.getLeftTimes());

    }

    @Test
    public void should_win_when_guess_number() {
        String number = "1234";
        Generator generator = Mockito.mock(Generator.class);
        Mockito.when(generator.generate()).thenReturn("1234");

        Game game = new Game(generator);
        Outcome outcome = game.guess(number);

        Assert.assertTrue(outcome.isWinning());
        Assert.assertEquals(4, outcome.getCompareResult().getNumOfA());
        Assert.assertEquals(0, outcome.getCompareResult().getNumOfB());
        Assert.assertEquals(number, outcome.getUserGuess());
    }

    @Test(expected = GameOverException.class)
    public void should_throw_game_over_exception_when_guess_number_given_game_over() {
        String number = "1235";
        Generator generator = Mockito.mock(Generator.class);
        Mockito.when(generator.generate()).thenReturn("1234");


        Game game = new Game(generator);
        ReflectionTestUtils.setField(game, "isGameOver", true);
        game.guess(number);
    }

    @Test(expected = GameAnswerException.class)
    public void should_throw_game_answer_exception_when_guess_number_given_answers_length_is_not_equals_4() {
        String number = "12";
        Generator generator = Mockito.mock(Generator.class);
        Mockito.when(generator.generate()).thenReturn("1234");

        Game game = new Game(generator);
        game.guess(number);
    }
}
