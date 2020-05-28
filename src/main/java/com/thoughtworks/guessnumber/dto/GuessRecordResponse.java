package com.thoughtworks.guessnumber.dto;

import com.thoughtworks.guessnumber.entity.GameRecord;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuessRecordResponse {
    private String userGuess;
    private String compareResult;

    public static GuessRecordResponse of(GameRecord gameRecord) {
        return GuessRecordResponse.builder()
                .userGuess(gameRecord.getUserGuess())
                .compareResult(gameRecord.getCompareResult())
                .build();
    }

}