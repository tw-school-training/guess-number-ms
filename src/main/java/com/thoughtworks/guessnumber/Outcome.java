package com.thoughtworks.guessnumber;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class Outcome {

    private Result compareResult;

    private String userGuess;

    private int leftTimes;

    public boolean isWinning() {
        return compareResult.isWinning();
    }
}
