package day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Task1 {
    int nextStep = 0;
    Map<Pair, Long> cache = new HashMap<>();

    public long calcNewNumber(long secretNumber, int stepsLeft) {
        if (stepsLeft == 0) {
            return secretNumber;
        }
        if (cache.containsKey(new Pair(secretNumber, stepsLeft))) {
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
        return calcNewNumber(resSecret, stepsLeft - 1);
    }

    public long solve(String smallInput) {
        long total = 0;
        for (var line : smallInput.split("\n")) {
            total += calcNewNumber(Long.parseLong(line), 2000);
        }
        return total;
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
            Task1 task1 = new Task1();
            System.out.println("Result is: " + task1.solve(input));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
