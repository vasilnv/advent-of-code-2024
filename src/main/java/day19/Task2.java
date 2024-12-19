package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task2 extends Task1 {
    Map<String, Set<List<String>>> combinationSuccess = new HashMap<>();

    public int solve() {
        int result = 0;
        for (String combination : combinations) {
            System.out.println("CHECKING COMBINATION " + combination);
            result += checkCombo(combination).size();
        }
        return result;
    }

    private Set<List<String>> checkCombo(String combination) {
        if (combinationSuccess.containsKey(combination)) {
            return combinationSuccess.get(combination);
        }
        Set<List<String>> allCombos = new HashSet<>();
        if (allTowels.contains(combination)) {
            allCombos.add(List.of(combination));
            return allCombos;
        }
        for (int i = 1; i < combination.length(); i++) {
            Set<List<String>> left;
            Set<List<String>> right;
            left = checkCombo(combination.substring(0, i));
            right = checkCombo(combination.substring(i));
            for (List<String> combinationsLeft : left) {
                for (List<String> combinationsRight : right) {
                    List<String> resultList = new ArrayList<>();
                    resultList.addAll(combinationsLeft);
                    resultList.addAll(combinationsRight);
                    allCombos.add(resultList);
                }
            }
        }
        combinationSuccess.put(combination, allCombos);
        return allCombos;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day19.txt"));
            Task2 task2 = new Task2();
            task2.convertInput(input);
            System.out.println("Result is: " + task2.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

