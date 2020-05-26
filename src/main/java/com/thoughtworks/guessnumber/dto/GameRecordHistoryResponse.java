package com.thoughtworks.guessnumber.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class GameRecordHistoryResponse {

    private String ticket;
    private String userGuess;
    private String compareResult;
    private Integer leftTimes;
    private Boolean isWinning;

    private List<GuessRecordResponse> guessHistory;

    @Getter
    @Builder
    public static class GuessRecordResponse {
        private String userGuess;
        private String compareResult;
    }
}
