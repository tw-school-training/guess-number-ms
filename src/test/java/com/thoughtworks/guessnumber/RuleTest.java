package com.thoughtworks.guessnumber;

import org.junit.Assert;
import org.junit.Test;

public class RuleTest {

    @Test
    public void should_return_0A0B_when_all_numbers_is_wrong() {
        String base = "1234";
        Rule rule = new Rule(base);
        Result result = rule.compare("5678");
        Assert.assertEquals(0, result.getNumOfA());
        Assert.assertEquals(0, result.getNumOfB());
    }
}
