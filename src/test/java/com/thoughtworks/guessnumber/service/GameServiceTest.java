package com.thoughtworks.guessnumber.service;

import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.mapper.GameRecordMapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class GameServiceTest {

    @Test
    public void should_return_game_record_when_guess_given_non_ticket() {
        String ticket = null;
        String answer = "1234";
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);

        GameService gameService = new GameService(gameRecordMapper);
        GameRecord gameRecord = gameService.guess(ticket, answer);

        Mockito.verify(gameRecordMapper).save(Mockito.any());

        Assert.assertNotNull(gameRecord);
        Assert.assertNotNull(gameRecord.getId());
        Assert.assertNotNull(gameRecord.getTicket());
        Assert.assertNotNull(gameRecord.getLeftTimes());
        Assert.assertNotNull(gameRecord.getIsWinning());
        Assert.assertNotNull(gameRecord.getCompareResult());
        Assert.assertEquals(answer, gameRecord.getUserGuess());

    }

    @Test
    public void should_return_game_record_when_guess_given_ticket_with_game() {
        String ticket = null;
        String answer = "1234";
        GameRecordMapper gameRecordMapper = Mockito.mock(GameRecordMapper.class);

        GameService gameService = new GameService(gameRecordMapper);
        GameRecord gameRecord = gameService.guess(ticket, answer);

        gameRecord = gameService.guess(gameRecord.getTicket(), answer);

        Mockito.verify(gameRecordMapper, Mockito.times(2)).save(Mockito.any());

        Assert.assertNotNull(gameRecord);
        Assert.assertNotNull(gameRecord.getId());
        Assert.assertNotNull(gameRecord.getTicket());
        Assert.assertNotNull(gameRecord.getLeftTimes());
        Assert.assertNotNull(gameRecord.getIsWinning());
        Assert.assertNotNull(gameRecord.getCompareResult());
        Assert.assertEquals(answer, gameRecord.getUserGuess());
    }
}
