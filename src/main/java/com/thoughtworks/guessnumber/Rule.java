package com.thoughtworks.guessnumber;

public class Rule {

    private String base;

    public Rule(String base) {
        this.base = base;
    }

    public Result compare(String value) {
        int numOfA = 0;
        int numOfB = 0;
        for (int i = 0; i < value.length(); i++) {
            if (base.contains(String.valueOf(value.charAt(i)))) {
                numOfA++;
            }
        }
        return Result.builder().numOfA(numOfA).numOfB(numOfB).build();
    }
}
