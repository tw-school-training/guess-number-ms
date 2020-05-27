package com.thoughtworks.guessnumber;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter(AccessLevel.NONE)
public class Result {
    private int numOfA;
    private int numOfB;

    public String render() {
        return String.format("%dA%dB", getNumOfA(), getNumOfB());
    }
}
