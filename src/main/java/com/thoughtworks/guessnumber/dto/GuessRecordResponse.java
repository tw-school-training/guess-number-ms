package com.thoughtworks.guessnumber.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GuessRecordResponse {
    private String userGuess;
    private String compareResult;
}