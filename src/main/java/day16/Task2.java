package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task2 {
    int BEST_SCORE;
    char[][] board;

    public Task2(String input, int bestScore) {
        loadBoard(input);
        BEST_SCORE = bestScore;
    }

    public char[][] loadBoard(String input) {
        String[] lines = input.split("\n");
        board = new char[lines.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = lines[i].toCharArray();
        }
        return board;
    }

    public Set<Pair<Integer, Integer>> solve() {
        Pair<Integer, Integer> start = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'S') {
                    start = new Pair<>(i, j);
                }
            }
        }
        return solve(start);
    }

    public Set<Pair<Integer, Integer>> solve(Pair<Integer, Integer> start) {
        Set<Pair<Integer, Integer>> allInPath = new HashSet<>();
        Map<PairState, Integer> seen = new HashMap<>();
        Queue<State> queue = new LinkedList<>();

        Set<Pair<Integer, Integer>> path = new HashSet<>();
        path.add(start);
        queue.add(new State(start, new Pair<>(0, 1), 0, path));

        while (!queue.isEmpty()) {
            State current = queue.poll();
            Pair<Integer, Integer> pos = current.pos;
            Pair<Integer, Integer> dir = current.dir;
            int currScore = current.score;
            Set<Pair<Integer, Integer>> currPath = current.path;

            PairState key = new PairState(pos, dir);
            if (currScore > seen.getOrDefault(key, Integer.MAX_VALUE) || currScore > BEST_SCORE) {
                continue;
            }
            seen.put(key, currScore);

            if (board[pos.key][pos.value] == 'E') {
                if (currScore == BEST_SCORE) {
                    allInPath.addAll(currPath);
                }
                continue;
            }

            int dr = dir.getKey();
            int dc = dir.getValue();
            int r = pos.getKey();
            int c = pos.getValue();

            Pair<Integer, Integer> nextPos = new Pair<>(r + dr, c + dc);
            if (board[nextPos.key][nextPos.value] != '#') {
                Set<Pair<Integer, Integer>> newPath = new HashSet<>(currPath);
                newPath.add(nextPos);
                queue.add(new State(nextPos, dir, currScore + 1, newPath));
            }

            Pair<Integer, Integer> nextPosRotatePos = new Pair<>(dc, dr); // reverse
            queue.add(new State(pos, nextPosRotatePos, currScore + 1000, new HashSet<>(currPath)));

            Pair<Integer, Integer> nextPosRotateNeg = new Pair<>(-dc, -dr); // reverse negatively
            queue.add(new State(pos, nextPosRotateNeg, currScore + 1000, new HashSet<>(currPath)));
        }

        return allInPath;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day16.txt"));
            Task1 task1 = new Task1(input);
            Task2 task2 = new Task2(input, task1.solve());
            char[][] board = task2.loadBoard(input);

            Pair<Integer, Integer> startPos = new Pair<>(-1, -1);
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    if (board[row][col] == 'S') {
                        startPos = new Pair<>(row, col);
                    }
                }
            }

            Set<Pair<Integer, Integer>> result = task2.solve(startPos);
            System.out.println("Result is: " + result.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<?, ?> pair = (Pair<?, ?>) o;
            return Objects.equals(key, pair.key) && Objects.equals(value, pair.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    static class State {
        Pair<Integer, Integer> pos;
        Pair<Integer, Integer> dir;
        int score;
        Set<Pair<Integer, Integer>> path;

        State(Pair<Integer, Integer> pos, Pair<Integer, Integer> dir, int score, Set<Pair<Integer, Integer>> path) {
            this.pos = pos;
            this.dir = dir;
            this.score = score;
            this.path = path;
        }
    }

    static class PairState {
        Pair<Integer, Integer> pos;
        Pair<Integer, Integer> dir;

        PairState(Pair<Integer, Integer> pos, Pair<Integer, Integer> dir) {
            this.pos = pos;
            this.dir = dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PairState that = (PairState) o;
            return Objects.equals(pos, that.pos) && Objects.equals(dir, that.dir);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pos, dir);
        }
    }
}