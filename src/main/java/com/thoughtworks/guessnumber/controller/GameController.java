package com.thoughtworks.guessnumber.controller;

import com.thoughtworks.guessnumber.dto.GameRecordHistoryResponse;
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
    public GameRecordHistoryResponse guess(@RequestParam(value = "ticket", required = false) String ticket,
                                           @RequestParam("user-answer") String answer) {
        GameRecord gameRecord = gameService.guess(ticket, answer);
        List<GameRecord> historyGameRecords = gameService.findGameRecordsByTicket(gameRecord.getTicket());

        return GameRecordHistoryResponse.builder()
                .leftTimes(gameRecord.getLeftTimes())
                .ticket(gameRecord.getTicket())
                .compareResult(gameRecord.getCompareResult())
                .userGuess(gameRecord.getUserGuess())
                .isWinning(gameRecord.getIsWinning())
                .guessHistory(buildHistory(historyGameRecords))
                .build();
    }

    private List<GameRecordHistoryResponse.GuessRecordResponse> buildHistory(List<GameRecord> historyGameRecords) {
        return historyGameRecords.stream()
                .map(gameRecord -> GameRecordHistoryResponse.GuessRecordResponse.builder()
                        .userGuess(gameRecord.getUserGuess())
                        .compareResult(gameRecord.getCompareResult())
                        .build())
                .collect(Collectors.toList());
    }
}
