package com.thoughtworks.guessnumber.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameRecordResponse {

    private String userGuess;
    private String compareResult;
    private Integer leftTimes;
    private Boolean isWinning;
}
