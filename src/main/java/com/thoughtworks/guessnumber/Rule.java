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
            char tmp = value.charAt(i);
            for (int j = 0; j < this.base.length(); j++) {
                char baseChar = this.base.charAt(j);
                if (baseChar == tmp) {
                    if (i == j) {
                        numOfA++;
                    } else {
                        numOfB++;
                    }
                }
            }
        }
        return Result.builder().numOfA(numOfA).numOfB(numOfB).build();
    }
}
