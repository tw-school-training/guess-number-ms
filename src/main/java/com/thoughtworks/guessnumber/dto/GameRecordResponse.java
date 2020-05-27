package com.thoughtworks.guessnumber.dto;

import com.thoughtworks.guessnumber.entity.GameRecord;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GameRecordResponse {

    private String userGuess;
    private String compareResult;
    private Integer leftTimes;
    private Boolean isWinning;
   
    public static GameRecordResponse getResponse(GameRecord gameRecord) {
        return GameRecordResponse.builder()
                .leftTimes(gameRecord.getLeftTimes())
                .compareResult(gameRecord.getCompareResult())
                .userGuess(gameRecord.getUserGuess())
                .isWinning(gameRecord.isWinning())
                .build();
    }
}
