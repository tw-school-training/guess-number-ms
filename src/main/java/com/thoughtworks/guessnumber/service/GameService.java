package com.thoughtworks.guessnumber.service;

import com.thoughtworks.guessnumber.Game;
import com.thoughtworks.guessnumber.Generator;
import com.thoughtworks.guessnumber.Outcome;
import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.mapper.GameRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GameService {

    private GameRecordMapper gameRecordMapper;

    private String ticket;

    private Game game;

    public GameService(GameRecordMapper gameRecordMapper) {
        this.gameRecordMapper = gameRecordMapper;
    }

    @Transactional
    public GameRecord guess(String userAnswer) {
        if (game == null) {
            game = new Game(new Generator());
            ticket = UUID.randomUUID().toString();
        }

        Outcome outcome = game.guess(userAnswer);

        if (game.isGameOver()) {
            game = null;
        }

        GameRecord gameRecord = GameRecord.builder()
                .id(UUID.randomUUID().toString())
                .ticket(ticket)
                .userGuess(userAnswer)
                .isWinning(outcome.isWinning())
                .leftTimes(outcome.getLeftTimes())
                .compareResult(outcome.getCompareResult().render())
                .build();

        gameRecordMapper.save(gameRecord);

        return gameRecord;
    }

    public List<GameRecord> findGameRecords() {
        return gameRecordMapper.findGameRecordsByTicket(ticket);
    }
}
