package com.thoughtworks.guessnumber.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRecord {
    private String id;
    private String ticket;
    private Integer leftTimes;
    private Boolean isWinning;
    private String compareResult;
    private String userGuess;

}
