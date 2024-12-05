package day5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Task1 {
    Map<Integer, List<Integer>> edges = new HashMap<>();

    public void createGraph(List<List<Integer>> input) {
        for (int i = 0; i < input.size(); i++) {
            edges.putIfAbsent(input.get(i).get(0), new ArrayList<>());
            edges.get(input.get(i).get(0)).add(input.get(i).get(1));
            edges.putIfAbsent(input.get(i).get(1), new ArrayList<>());
        }
    }

    public boolean checkSequence(List<Integer> sequence) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> orderStack = new Stack<>();

        for (int i = 0; i < sequence.size(); i++) {
            if (!visited.contains(sequence.get(i))) {
                topologicalSort(sequence.get(i), visited, orderStack);
            }
        }

        List<Integer> ordered = new ArrayList<>();
        while (!orderStack.isEmpty()) {
            ordered.add(orderStack.pop());
        }

        Queue<Integer> queue = new LinkedList<>(sequence);
        int first = queue.poll();
        int next = queue.peek();
        for (int i = 0; i < ordered.size(); i++) {
            if (ordered.get(i) == first) {
                first = queue.poll();
                if (queue.isEmpty()) {
                    return true;
                }
                next = queue.peek();
            }

            if (ordered.get(i) == next) {
                return false;
            }
        }
        return false;
    }

    public List<Integer> topologicalSort() {
        Map<Integer, Integer> indegree = new HashMap<>();
        for (var key : edges.keySet()) {
            indegree.put(key, 0);
        }

        for (var entryVal : edges.keySet()) {
            for (int it : edges.get(entryVal)) {
                indegree.put(it, indegree.get(it) + 1);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        for (var key : edges.keySet()) {
            if (indegree.get(key) == 0) {
                q.offer(key);
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);

            for (int it : edges.get(node)) {
                indegree.put(it, indegree.get(it) - 1);

                // If indegree becomes 0, push it to the
                // queue
                if (indegree.get(it) == 0) {
                    q.offer(it);
                }
            }
        }

        // Check for cycle
        if (result.size() != edges.size()) {
            System.out.println("Graph contains cycle! Result size is " + result.size() + " and edges size is " + edges.size());
            throw new RuntimeException("Graph contains cycle!");
        }

        return result;
    }

    private void topologicalSort(int index, Set<Integer> visited, Stack<Integer> orderStack) {
        visited.add(index);

        if (edges.get(index) != null) {
            for (int i : edges.get(index)) {
                if (!visited.contains(i)) {
                    topologicalSort(i, visited, orderStack);
                }
            }
        }
        orderStack.push(index);
    }

    public int calculateScoreOnCorrectSequences(List<List<Integer>> sequences) {
        int total = 0;
        for (List<Integer> sequence : sequences) {
            total += sequence.get(sequence.size() / 2);
        }
        return total;
    }

    public int calculateScore(List<List<Integer>> sequences) {
        List<List<Integer>> sequencesToCheck = new ArrayList<>();
        for (List<Integer> sequence : sequences) {
            if (checkSequence(sequence)) {
                System.out.println("ADDING SEQUENCE " + sequence + " AS CORRECT");
                sequencesToCheck.add(sequence);
            }
        }

        return calculateScoreOnCorrectSequences(sequencesToCheck);
    }

    public int calculateScore(List<Integer> sequence, List<Integer> topologicalSort) {
        int total = 0;
        List<Integer> orderedSequence = new ArrayList<>();
        for (var i : topologicalSort) {
            if (sequence.contains(i)) {
                orderedSequence.add(i);
            }
        }
        boolean shouldInclude = true;
        for (int i = 0; i < orderedSequence.size(); i++) {
            if (orderedSequence.get(i) != sequence.get(i)) {
                shouldInclude = false;
                break;
            }
        }
        if (shouldInclude) {
            total += sequence.get(sequence.size() / 2);
        }
        return total;
    }


    public static List<List<Integer>> getPairs(String input) {
        List<List<Integer>> pairs = new ArrayList<>();
        String lines[] = input.split("\n");
        for (String line : lines) {
            // Check if the line contains a "|"
            if (line.contains("|")) {
                // Split the line by "|" and parse the integers
                String[] parts = line.split("\\|");
                List<Integer> pair = new ArrayList<>();
                pair.add(Integer.parseInt(parts[0]));
                pair.add(Integer.parseInt(parts[1]));
                pairs.add(pair);
            }
        }
        return pairs;
    }

    public static List<List<Integer>> extractSequences(String input) {
        String[] lines = input.split("\n");
        List<List<Integer>> sequences = new ArrayList<>();

        boolean foundEmptyLine = false;

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                foundEmptyLine = true;
                continue;
            }
            if (foundEmptyLine) {
                String[] parts = line.split(",");
                List<Integer> sequence = new ArrayList<>();
                for (String part : parts) {
                    sequence.add(Integer.parseInt(part.trim()));
                }
                sequences.add(sequence);
            }
        }

        return sequences;
    }

    public static int getTotalScore(List<List<Integer>> sequences, List<List<Integer>> pairs) {
        int totalScore = 0;
        for (var seq : sequences) {
            Task1 task1 = new Task1();
            List<List<Integer>> filtered = pairs.stream().filter(pair -> seq.contains(pair.get(0))).collect(Collectors.toList());
            task1.createGraph(filtered);
            List<Integer> sorted = task1.topologicalSort();
            totalScore += task1.calculateScore(seq, sorted);
        }
        return totalScore;
    }

    public static int getTotalScoreErroneous(List<List<Integer>> sequences, List<List<Integer>> pairs) {
        int totalScore = 0;
        for (var seq : sequences) {
            Task1 task1 = new Task1();
            List<List<Integer>> filtered = pairs.stream().filter(pair -> seq.contains(pair.get(0))).collect(Collectors.toList());
            task1.createGraph(filtered);
            List<Integer> sorted = task1.topologicalSort();
            totalScore += task1.calculateScoreErroneous(seq, sorted);
        }
        return totalScore;
    }

    private int calculateScoreErroneous(List<Integer> sequence, List<Integer> sorted) {
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

            int totalScore = getTotalScoreErroneous(sequences, pairs);
            System.out.println("TOTAL SCORE IS " + totalScore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
