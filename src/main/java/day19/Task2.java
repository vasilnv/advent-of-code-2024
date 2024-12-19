package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task2 extends Task1 {
    Map<String, Long> combinationSuccess = new HashMap<>();

    public long solve2() {
        long result = 0;
        for (String combination : combinations) {
            result += checkCombo(combination);
        }
        return result;
    }

    private long checkCombo(String combination) {
        if (combination.isEmpty()) {
            return 1;
        }
        if (combinationSuccess.containsKey(combination)) {
            return combinationSuccess.get(combination);
        }
        long result = 0;
        for (var combo : allTowels) {
            if (combination.startsWith(combo)) {
                result += checkCombo(combination.substring(combo.length()));
            }
        }
        combinationSuccess.put(combination, result);
        return result;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day19.txt"));
            Task2 task2 = new Task2();
            task2.convertInput(input);
            System.out.println("Result is: " + task2.solve2());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

