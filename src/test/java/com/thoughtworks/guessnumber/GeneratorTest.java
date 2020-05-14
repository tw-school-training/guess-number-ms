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

}
