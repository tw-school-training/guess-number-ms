package com.thoughtworks.guessnumber;

import org.junit.Assert;
import org.junit.Test;

public class GeneratorTest {

    @Test
    public void should_return_4digit_when_generate() {

        Generator generator = new Generator();

        String result = generator.generate();

        Assert.assertEquals(4, result.length());
    }

    @Test
    public void should_not_exist_repeating_numbers_when_generate() {
        Generator generator = new Generator();

        String result = generator.generate();

        Assert.assertTrue(result, nonRepeatingNumbers(result));
    }

    private boolean nonRepeatingNumbers(String result) {
        for (int charIndex = 0; charIndex < result.length(); charIndex++) {
            char ch = result.charAt(charIndex);
            if (result.lastIndexOf(ch) != charIndex) {
                return false;
            }
        }
        return true;
    }
}
