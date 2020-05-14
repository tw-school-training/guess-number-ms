package com.thoughtworks.guessnumber;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private List<Integer> baseNumbers = List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    public String generate() {
        List<Integer> originalNumbers = new ArrayList<>(baseNumbers);
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int times = 0; times < 4; times++) {
            int index = random.nextInt(originalNumbers.size());
            sb.append(originalNumbers.remove(index));
        }
        return sb.toString();
    }
}
