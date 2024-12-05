package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task2 extends Task1 {

    public static int getTotalScore(List<List<Integer>> sequences, List<List<Integer>> pairs) {
        int totalScore = 0;
        for (var seq : sequences) {
            Task2 task2 = new Task2();
            List<List<Integer>> filtered = pairs.stream().filter(pair -> seq.contains(pair.get(0))).collect(Collectors.toList());
            task2.createGraph(filtered);
            List<Integer> sorted = task2.topologicalSort();
            totalScore += task2.calculateScore(seq, sorted);
        }
        return totalScore;
    }

    @Override
    public int calculateScore(List<Integer> sequence, List<Integer> sorted) {
        int total = 0;
        List<Integer> orderedSequence = new ArrayList<>();
        for (var i : sorted) {
            if (sequence.contains(i)) {
                orderedSequence.add(i);
            }
        }

        boolean shouldInclude = false;
        for (int i = 0; i < orderedSequence.size(); i++) {
            if (!orderedSequence.get(i).equals(sequence.get(i))) {
                shouldInclude = true;
                break;
            }
        }
        if (shouldInclude) {
            total += orderedSequence.get(orderedSequence.size() / 2);
        }
        return total;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day5.txt"));
            List<List<Integer>> pairs = getPairs(input);
            List<List<Integer>> sequences = extractSequences(input);

            int totalScore = getTotalScore(sequences, pairs);
            System.out.println("TOTAL SCORE IS " + totalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
