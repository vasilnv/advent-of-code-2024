package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task2 {
    int nextStep = 0;
    Map<Pair, Long> cache = new HashMap<>();

    public long calcNewNumber(long secretNumber, int stepsLeft, List<Long> finalPrices) {
        if (stepsLeft == 0) {
            return secretNumber;
        }
        if (cache.containsKey(new Pair(secretNumber, stepsLeft))) {
            finalPrices.add(cache.get(new Pair(secretNumber, stepsLeft)));
            return cache.get(new Pair(secretNumber, stepsLeft));
        }
        long resSecret = secretNumber;
        if (nextStep % 3 == 0) {
            long newNumber = resSecret * 64;
            long mix = newNumber ^ resSecret;
            long pruned = mix % 16777216;
            resSecret = pruned;

            nextStep++;
        }
        if (nextStep % 3 == 1) {
            long newNumber = resSecret / 32;
            long mix = newNumber ^ resSecret;
            long pruned = mix % 16777216;
            resSecret = pruned;
            nextStep++;
        }
        if (nextStep % 3 == 2) {
            long newNumber = resSecret * 2048;
            long mix = newNumber ^ resSecret;
            long pruned = mix % 16777216;
            resSecret = pruned;
            nextStep++;
        }
        cache.put(new Pair(secretNumber, stepsLeft), resSecret);
        finalPrices.add(resSecret % 10);
        return calcNewNumber(resSecret, stepsLeft - 1, finalPrices);
    }

    public long solve(String smallInput) {
        long total = 0;
        List<List<Long>> allPrices = new ArrayList<>();
        List<List<Long>> allDiffs = new ArrayList<>();
        for (var line : smallInput.split("\n")) {
            List<Long> sellerPrices = new ArrayList<>();
            sellerPrices.add(Long.parseLong(line) % 10);
            calcNewNumber(Long.parseLong(line), 2000, sellerPrices);
            List<Long> diffs = new ArrayList<>();
            diffs.add(0L);
            for (int i = 1; i < sellerPrices.size(); i++) {
                diffs.add(sellerPrices.get(i) - sellerPrices.get(i - 1));
            }
            allPrices.add(sellerPrices);
            System.out.println(diffs);
            allDiffs.add(diffs);
        }

        Map<List<Long>, Long> sequencesToPrizes = new HashMap<>();
        for (int i = 0; i < allPrices.size(); i++) {
            Set<List<Long>> addedToList = new HashSet<>();
            for (int j = 3; j < allPrices.get(i).size(); j++) {
                List<Long> currSeq = new ArrayList<>(List.of(allDiffs.get(i).get(j-3), allDiffs.get(i).get(j - 2), allDiffs.get(i).get(j - 1), allDiffs.get(i).get(j)));
                if (addedToList.contains(currSeq)) {
                    continue;
                }
                sequencesToPrizes.put(currSeq, sequencesToPrizes.getOrDefault(currSeq, 0L) + allPrices.get(i).get(j));
                addedToList.add(currSeq);
            }
        }
        long max = 0L;
        for (var entry : sequencesToPrizes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    class Pair {
        long num;
        long stepsLeft;

        public Pair(long num, long stepsLeft) {
            this.num = num;
            this.stepsLeft = stepsLeft;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return num == pair.num && stepsLeft == pair.stepsLeft;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, stepsLeft);
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day22.txt"));
            Task2 solver = new Task2();
            System.out.println("Result is: " + solver.solve(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
