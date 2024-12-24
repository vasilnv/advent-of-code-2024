package day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Task1 {
    List<String> rules;
    Map<String, Integer> calculated;

    public void loadInput(String input) {
        rules = new ArrayList<>();
        calculated = new HashMap<>();

        String[] lines = input.split("\n");
        for (var line : lines) {
            if (line.contains(":")) {
                String[] nums = line.trim().split(":");
                calculated.put(nums[0].trim(), Integer.parseInt(nums[1].trim()));
            } else if (line.contains("->")) {
                var lineUse = line.replace("\\s+", "");
                rules.add(lineUse.trim());
            }
        }
    }

    public long solve() {
        Queue<String> rulesTodo = new LinkedList<>();
        rulesTodo.addAll(rules);

        while (!rulesTodo.isEmpty()) {
            String next = rulesTodo.poll();
            String[] line = next.split("->");
            String res = line[1].trim();
            if (next.contains("XOR")) {
                String[] nums = line[0].split("XOR");
                if (calculated.containsKey(nums[0].trim()) && calculated.containsKey(nums[1].trim())) {
                    calculated.put(res, calculated.get(nums[0].trim()) ^ calculated.get(nums[1].trim()));
                } else {
                    rulesTodo.offer(next);
                }
            } else if (next.contains("OR")) {
                String[] nums = line[0].split("OR");
                if (calculated.containsKey(nums[0].trim()) && calculated.containsKey(nums[1].trim())) {
                    calculated.put(res, calculated.get(nums[0].trim()) | calculated.get(nums[1].trim()));
                } else {
                    rulesTodo.offer(next);
                }
            } else if (next.contains("AND")) {
                String[] nums = line[0].split("AND");
                if (calculated.containsKey(nums[0].trim()) && calculated.containsKey(nums[1].trim())) {
                    calculated.put(res, calculated.get(nums[0].trim()) & calculated.get(nums[1].trim()));
                } else {
                    rulesTodo.offer(next);
                }
            }
        }

        List<String> numsSorted = new ArrayList<>(calculated.keySet());
        List<String> numsInRes = numsSorted.stream().filter(num -> num.startsWith("z")).collect(Collectors.toList());
        numsInRes.sort(String::compareTo);
        long total = 0;
        for (int i = numsInRes.size() - 1; i >= 0; i--) {
            total += (long) (calculated.get(numsInRes.get(i)) * Math.pow(2, i));
        }
        return total;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day24.txt"));
            Task1 solver = new Task1();
            solver.loadInput(input);
            System.out.println("Result is: " + solver.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
