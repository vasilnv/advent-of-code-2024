package day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task2 extends Task1 {

    public Task2(String input) {
        super(input);
    }

    public String solve2() {
        Set<String> bestResult = new HashSet<>();
        int max = 0;
        for (var entry : connections.entrySet()) {
            for (var entryConnection : entry.getValue()) {
                Set<String> otherEntry = new HashSet<>(connections.get(entryConnection));
                otherEntry.retainAll(entry.getValue());
                otherEntry.add(entryConnection);
                otherEntry.add(entry.getKey());
                Set<String> allEntries = new HashSet<>(otherEntry);
                for (var n : allEntries) {
                    var newEntryConnections = new HashSet<>(connections.get(n));
                    otherEntry.retainAll(newEntryConnections);
                    otherEntry.add(n);
                }
                if (otherEntry.size() > max) {
                    bestResult.clear();
                    bestResult.add(entryConnection);
                    bestResult.add(entry.getKey());
                    bestResult.addAll(otherEntry);
                    max = otherEntry.size();
                }
            }
        }
        List<String> allResults = new ArrayList<>(bestResult);
        allResults.sort(String::compareTo);
        return allResults.toString();
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day23.txt"));
            Task2 solver = new Task2(input);
            System.out.println("Result is:" + solver.solve2());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}