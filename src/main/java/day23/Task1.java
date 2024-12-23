package day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task1 {
    Map<String, Set<String>> connections = new HashMap<>();

    public Task1(String input) {
        loadInput(input);
    }

    public void loadInput(String input) {
        String[] lines = input.split("\n");
        for (var line : lines) {
            String[] pcs = line.split("-");
            connections.putIfAbsent(pcs[0], new HashSet<>());
            connections.putIfAbsent(pcs[1], new HashSet<>());
            connections.get(pcs[0]).add(pcs[1]);
            connections.get(pcs[1]).add(pcs[0]);
        }
    }

    public int solve() {
        Set<Set<String>> result = new HashSet<>();
        for (var entry : connections.entrySet()) {
            for (var entryConnection : entry.getValue()) {
                Set<String> otherEntry = new HashSet<>(connections.get(entryConnection));
                otherEntry.retainAll(entry.getValue());
                for (String commonConnection : otherEntry) {
                    if (commonConnection.startsWith("t") || entryConnection.startsWith("t") || entry.getKey().startsWith("t")) {
                        Set<String> triples = new HashSet<>();
                        triples.add(commonConnection);
                        triples.add(entryConnection);
                        triples.add(entry.getKey());
                        result.add(triples);
                        System.out.println("Adding " + triples);
                    }
                }
            }
        }
        return result.size();
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day23.txt"));
            Task1 solver = new Task1(input);
            System.out.println("Result is:" + solver.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
