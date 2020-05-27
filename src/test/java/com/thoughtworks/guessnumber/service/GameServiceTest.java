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

import java.util.List;
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
        Assert.assertNotNull(gameRecord.getGameRound());
        Assert.assertNotNull(gameRecord.getLeftTimes());
        Assert.assertNotNull(gameRecord.isWinning());
        Assert.assertNotNull(gameRecord.getCompareResult());
        Assert.assertEquals(userAnswer, gameRecord.getUserGuess());

    }

    @Test
    public void should_return_game_record_when_guess_given_second_guess() {
        String userAnswer = "1234";
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);
        Game game = Mockito.mock(Game.class);
        String gameRound = UUID.randomUUID().toString();
        Outcome outcome = Outcome.builder().leftTimes(4).userGuess(userAnswer).isWinning(false)
                .compareResult(Result.builder().numOfA(1).numOfB(1).build()).build();
        Mockito.when(game.guess(userAnswer)).thenReturn(outcome);

        GameService gameService = new GameService(gameRecordMapper);
        ReflectionTestUtils.setField(gameService, "game", game);
        ReflectionTestUtils.setField(gameService, "gameRound", gameRound);

        GameRecord gameRecord = gameService.guess(userAnswer);

        Mockito.verify(gameRecordMapper).save(Mockito.any());

        Assert.assertNotNull(gameRecord);
        Assert.assertNotNull(gameRecord.getId());
        Assert.assertEquals(gameRound, gameRecord.getGameRound());
        Assert.assertEquals(outcome.getLeftTimes(), gameRecord.getLeftTimes().intValue());
        Assert.assertEquals(outcome.isWinning(), gameRecord.isWinning());
        Assert.assertEquals("1A1B", gameRecord.getCompareResult());
        Assert.assertEquals(outcome.getUserGuess(), gameRecord.getUserGuess());
    }

    @Test
    public void should_return_game_records_when_find_guess_history() {
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);
        List<GameRecord> expectedGameRecords = List.of(
                GameRecord.builder().build()
        );
        Mockito.when(gameRecordMapper.findGameRecordsByGameRound(Mockito.any())).thenReturn(expectedGameRecords);

        GameService gameService = new GameService(gameRecordMapper);
        List<GameRecord> gameRecords = gameService.findGameRecords();

        Assert.assertEquals(expectedGameRecords, gameRecords);

    }
}
