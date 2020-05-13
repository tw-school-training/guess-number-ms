package com.thoughtworks.guessnumber;

public class Rule {
    
    private String base;

    public Rule(String base) {
        this.base = base;
    }

    public Result compare(String value) {
        return Result.builder().build();
    }
}
