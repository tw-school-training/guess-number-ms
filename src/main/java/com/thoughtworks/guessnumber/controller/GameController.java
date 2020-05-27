package com.thoughtworks.guessnumber.controller;

import com.thoughtworks.guessnumber.dto.GameRecordResponse;
import com.thoughtworks.guessnumber.dto.GuessRecordResponse;
import com.thoughtworks.guessnumber.entity.GameRecord;
import com.thoughtworks.guessnumber.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/guess")
    public GameRecordResponse guess(@RequestParam("user-answer") String answer) {
        GameRecord gameRecord = gameService.guess(answer);

        return GameRecordResponse.getResponse(gameRecord);
    }

    @GetMapping("/find-guess-history")
    public List<GuessRecordResponse> findGuessHistory() {
        return buildHistory(gameService.findGameRecords());
    }

    private List<GuessRecordResponse> buildHistory(List<GameRecord> historyGameRecords) {
        return historyGameRecords.stream()
                .map(gameRecord -> GuessRecordResponse.builder()
                        .userGuess(gameRecord.getUserGuess())
                        .compareResult(gameRecord.getCompareResult())
                        .build())
                .collect(Collectors.toList());
    }
}
