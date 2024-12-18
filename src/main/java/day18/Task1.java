package day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task1 {
    int H;
    int W;
    char[][] board;
    Set<Node> settled;
    PriorityQueue<Node> pq;
    int[][] movements = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int[][] dist;

    Task1(String input, int threshold, int H, int W) {
        this.board = new char[H][W];
        loadInput(input, threshold);
        settled = new HashSet<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        dist = new int[H][W];
        this.H = H;
        this.W = W;
    }

    private void loadInput(String input, int threshold) {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                board[i][j] = '.';
            }
        }
        String[] points = input.split("\n");
        for (int i = 0; i < threshold; i++) {
            int pointI = Integer.parseInt(points[i].split(",")[0]);
            int pointJ = Integer.parseInt(points[i].split(",")[1]);
            board[pointI][pointJ] = '#';
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public int solve() {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                if (board[i][j] == '#') {
                    settled.add(new Node(i, j, 0));
                }
            }
        }
        int[] start = new int[] {0,0};

        dist[0][0] = 0;
        pq.add(new Node(start[0], start[1], 0));
        while (settled.size() != H * W) {
            if (pq.isEmpty()) {
                return dist[H-1][W-1];
            }
            Node node = pq.poll();
            if (settled.contains(node)) {
                continue;
            }
            settled.add(node);
            for (var movement : movements) {
                int nextI = node.i + movement[0];
                int nextJ = node.j + movement[1];
                if (isInBoundary(nextI, nextJ)) {
                    if (board[nextI][nextJ] != '#') {
                        int newCost = node.cost + 1;
                        if (!settled.contains(new Node(nextI, nextJ, newCost))) {
                            pq.add(new Node(nextI, nextJ, newCost));
                            if (newCost < dist[nextI][nextJ]) {
                                dist[nextI][nextJ] = newCost;
                            }
                        }
                    }
                }
            }
        }
        return dist[H-1][W-1];
    }

    private boolean isInBoundary(int i, int j) {
        return i >= 0 && i < H && j >= 0 && j < W;
    }

    class Node {
        public int i;
        public int j;
        public int cost;
        public Node(int i, int j, int cost) {
            this.i = i;
            this.j = j;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return i == node.i && j == node.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day18.txt"));
            Task1 task1 = new Task1(input, 1024, 71, 71);
            System.out.println("Result is: " + task1.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

