package com.thoughtworks.guessnumber;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
@Setter(AccessLevel.NONE)
public class Outcome {

    private boolean isWinning;

    private Result compareResult;

    private String userGuess;

    private int leftTimes;

}
