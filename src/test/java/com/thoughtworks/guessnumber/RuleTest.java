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

    @Test
    public void should_return_xA0B_when_x_numbers_position_is_right_given_x_numbers_is_right() {

        String base = "1234";
        Rule rule = new Rule(base);

        Result result = rule.compare("1235");

        Assert.assertEquals(3, result.getNumOfA());
        Assert.assertEquals(0, result.getNumOfB());
    }

    @Test
    public void should_return_xAyB_when_x_numbers_position_is_right_and_y_numbers_position_is_wrong_given_x_and_y_numbers_is_right() {
        String base = "1234";
        Rule rule = new Rule(base);

        Result result = rule.compare("3245");

        Assert.assertEquals(1, result.getNumOfA());
        Assert.assertEquals(2, result.getNumOfB());
    }

    @Test
    public void should_return_0AyB_when_y_numbers_position_is_right_given_y_numbers_is_right() {
        String base = "1234";
        Rule rule = new Rule(base);

        Result result = rule.compare("4320");

        Assert.assertEquals(0, result.getNumOfA());
        Assert.assertEquals(3, result.getNumOfB());
    }

}
