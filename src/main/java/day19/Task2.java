package day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task2 extends Task1 {
    Map<String, Long> combinationSuccess = new HashMap<>();

    public long solve(boolean use_dp) {
        long result = 0;
        for (String combination : combinations) {
            if (use_dp) {
                result += checkCombo_dp(combination);
            } else {
                result += checkCombo(combination);
            }
        }
        return result;
    }

    protected long checkCombo(String combination) {
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

    protected long checkCombo_dp(String combination) {
        long[] dp = new long[combination.length() + 1];
        dp[0] = 1;
        long currMax = 0;
        for (int i = 1; i <=  combination.length(); i++) {
            if (!allTowels.contains("" + combination.charAt(i - 1))) {
                dp[i] = 0;
            }
            for (int j = 0; j < i; j++) {
                if (allTowels.contains(combination.substring(j, i)) && dp[j] != 0) {
                    dp[i] += dp[j];
                }
            }
            currMax = Math.max(currMax, dp[i]);
        }
        return dp[combination.length()];
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day19.txt"));
            Task2 task2 = new Task2();
            task2.convertInput(input);
            long result = task2.solve(false);
            System.out.println("Result is: " + result); //666491493769758
            if (result != 666491493769758L) {
                System.err.println("Incorrect result:" + result);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

