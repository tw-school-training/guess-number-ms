package com.thoughtworks.guessnumber;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

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

}
