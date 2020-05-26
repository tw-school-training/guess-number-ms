package com.thoughtworks.guessnumber.service;

import com.thoughtworks.guessnumber.Game;
import com.thoughtworks.guessnumber.Generator;
import com.thoughtworks.guessnumber.Outcome;
import com.thoughtworks.guessnumber.Result;
import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.mapper.GameRecordMapper;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameService {

    private GameRecordMapper gameRecordMapper;

    private Map<String, Game> gameMap = new ConcurrentHashMap<>();

    public GameService(GameRecordMapper gameRecordMapper) {
        this.gameRecordMapper = gameRecordMapper;
    }

    public GameRecord guess(String ticket, String answer) {
        Game game;
        if (StringUtils.isEmpty(ticket)) {
            game = new Game(new Generator());
            ticket = UUID.randomUUID().toString();
            gameMap.put(ticket, game);
        } else {
            game = gameMap.get(ticket);
        }
        Outcome outcome = game.guess(answer);
        if(game.isGameOver()){
            gameMap.remove(ticket);
        }

        GameRecord gameRecord = GameRecord.builder()
                .id(UUID.randomUUID().toString())
                .ticket(ticket)
                .userGuess(answer)
                .isWinning(outcome.isWinning())
                .leftTimes(outcome.getLeftTimes())
                .compareResult(buildResult(outcome.getCompareResult()))
                .build();

        gameRecordMapper.save(gameRecord);

        return gameRecord;
    }

    private String buildResult(Result compareResult) {
        return String.format("%dA%dB", compareResult.getNumOfA(), compareResult.getNumOfB());
    }
}
