package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Task1 {
    Set<String> allTowels = new HashSet<>();
    List<String> combinations = new ArrayList<>();
    Map<String, Boolean> combinationSuccess = new HashMap<>();

    public void convertInput(String input) {
        String[] lines = input.split("\n");
        allTowels = new HashSet<>(Arrays.stream(lines[0].split(", ")).collect(Collectors.toList()));
        for (int i = 2; i < lines.length; i++) {
            combinations.add(lines[i]);
        }
    }

    public int solve() {
        int result = 0;
        for (String combination : combinations) {
            if (checkCombo(combination)) {
                result++;
            }
        }
        return result;
    }

    private boolean checkCombo(String combination) {
        if (allTowels.contains(combination)) {
            return true;
        }
        for (int i = 1; i < combination.length(); i++) {
            boolean left;
            boolean right;
            if (combinationSuccess.containsKey(combination.substring(0, i))) {
                left = combinationSuccess.get(combination.substring(0, i));
            } else {
                left = checkCombo(combination.substring(0, i));
                combinationSuccess.put(combination.substring(0, i), left);
            }
            if (combinationSuccess.containsKey(combination.substring(i))) {
                right = combinationSuccess.get(combination.substring(i));
            } else {
                right = checkCombo(combination.substring(i));
                combinationSuccess.put(combination.substring(i), right);
            }
            if (left && right) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day19.txt"));
            Task1 task1 = new Task1();
            task1.convertInput(input);
            System.out.println("Result is: " + task1.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
