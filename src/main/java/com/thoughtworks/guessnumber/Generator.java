package com.thoughtworks.guessnumber;

import java.util.List;
import java.util.Random;

public class Generator {

    public String generate() {
        List<Integer> originalNumbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int times = 0; times < 4; times++) {
            int index = random.nextInt(originalNumbers.size());
            sb.append(originalNumbers.get(index));
        }
        return sb.toString();
    }
}
