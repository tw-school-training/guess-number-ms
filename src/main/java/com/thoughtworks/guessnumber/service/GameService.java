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

    private String gameRound;

    private Game game;

    public GameService(GameRecordMapper gameRecordMapper) {
        this.gameRecordMapper = gameRecordMapper;
    }

    @Transactional
    public GameRecord guess(String userAnswer) {
        startGameIf();

        Outcome outcome = game.guess(userAnswer);
        GameRecord gameRecord = buildGameRecord(userAnswer, outcome);
        gameRecordMapper.save(gameRecord);

        exitGameIf();
        return gameRecord;
    }

    private void exitGameIf() {
        if (game.isGameOver()) {
            game = null;
        }
    }

    private void startGameIf() {
        if (game == null) {
            game = new Game(new Generator());
            gameRound = UUID.randomUUID().toString();
        }
    }

    public List<GameRecord> findGameRecords() {
        return gameRecordMapper.findGameRecordsByGameRound(gameRound);
    }

    private GameRecord buildGameRecord(String userAnswer, Outcome outcome) {
        return GameRecord.builder()
                .id(UUID.randomUUID().toString())
                .gameRound(gameRound)
                .userGuess(userAnswer)
                .isWinning(outcome.isWinning())
                .leftTimes(outcome.getLeftTimes())
                .compareResult(outcome.getCompareResult().toString())
                .build();
    }

}
