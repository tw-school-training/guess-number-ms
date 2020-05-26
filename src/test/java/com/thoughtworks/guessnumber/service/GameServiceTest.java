package com.thoughtworks.guessnumber.service;

import com.thoughtworks.guessnumber.Game;
import com.thoughtworks.guessnumber.Outcome;
import com.thoughtworks.guessnumber.Result;
import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.mapper.GameRecordMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.UUID;

public class GameServiceTest {

    @Test
    public void should_return_game_record_when_guess_given_first_guess() {
        String userAnswer = "1234";
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);

        GameService gameService = new GameService(gameRecordMapper);
        GameRecord gameRecord = gameService.guess(userAnswer);

        Mockito.verify(gameRecordMapper).save(Mockito.any());

        Assert.assertNotNull(gameRecord);
        Assert.assertNotNull(gameRecord.getId());
        Assert.assertNotNull(gameRecord.getTicket());
        Assert.assertNotNull(gameRecord.getLeftTimes());
        Assert.assertNotNull(gameRecord.getIsWinning());
        Assert.assertNotNull(gameRecord.getCompareResult());
        Assert.assertEquals(userAnswer, gameRecord.getUserGuess());

    }

    @Test
    public void should_return_game_record_when_guess_given_second_guess() {
        String userAnswer = "1234";
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);
        Game game = Mockito.mock(Game.class);
        String ticket = UUID.randomUUID().toString();
        Outcome outcome = Outcome.builder().leftTimes(4).userGuess(userAnswer).isWinning(false)
                .compareResult(Result.builder().numOfA(1).numOfB(1).build()).build();
        Mockito.when(game.guess(userAnswer)).thenReturn(outcome);

        GameService gameService = new GameService(gameRecordMapper);
        ReflectionTestUtils.setField(gameService, "game", game);
        ReflectionTestUtils.setField(gameService, "ticket", ticket);

        GameRecord gameRecord = gameService.guess(userAnswer);

        Mockito.verify(gameRecordMapper).save(Mockito.any());

        Assert.assertNotNull(gameRecord);
        Assert.assertNotNull(gameRecord.getId());
        Assert.assertEquals(ticket, gameRecord.getTicket());
        Assert.assertEquals(outcome.getLeftTimes(), gameRecord.getLeftTimes().intValue());
        Assert.assertEquals(outcome.isWinning(), gameRecord.getIsWinning());
        Assert.assertEquals("1A1B", gameRecord.getCompareResult());
        Assert.assertEquals(outcome.getUserGuess(), gameRecord.getUserGuess());
    }
}
