package com.thoughtworks.guessnumber.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRecord {
    private String id;
    private String gameRound;
    private Integer leftTimes;
    private boolean isWinning;
    private String compareResult;
    private String userGuess;

}
