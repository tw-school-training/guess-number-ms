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
            int index = this.base.indexOf(value.charAt(i));
            if (index == -1) {
                continue;
            }
            if (index == i) {
                numOfA++;
            } else {
                numOfB++;
            }
        }
        return Result.builder().numOfA(numOfA).numOfB(numOfB).build();
    }
}
