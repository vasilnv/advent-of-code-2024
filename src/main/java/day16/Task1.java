package day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task1 {
    int[][] directions = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};
    char[][] board;
    private int dist[][];
    private Set<Node> settled;
    private PriorityQueue<Node> pq;
    private int W;
    private int H;

    public Task1(String input) {
        loadBoard(input);
        this.H = board.length;
        this.W = board[0].length;
        dist = new int[H][W];
        settled = new HashSet<>();
        pq = new PriorityQueue<>(H * W, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });
    }

    public void loadBoard(String input) {
        String[] lines = input.split("\n");
        board = new char[lines.length][];
        for (int i = 0; i < board.length; i++) {
            board[i] = lines[i].toCharArray();
        }
    }

    public int solve() {
        int[] startPos = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'S') {
                    startPos[0] = i;
                    startPos[1] = j;
                }
            }
        }
        int[] startDir = directions[2];
        dijkstra(startDir, startPos);
        int[] endPos = new int[2];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'E') {
                    endPos[0] = i;
                    endPos[1] = j;
                }
            }
        }
        return dist[endPos[0]][endPos[1]];
    }

    public void dijkstra(int[] direction, int[] src)
    {
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                dist[i][j] = Integer.MAX_VALUE;
                if (board[i][j] == '#') {
                    settled.add(new Node(i, j, dist[i][j], 0, 0));
                }
            }
        }

        pq.add(new Node(src[0], src[1], 0, direction[0], direction[1]));
        dist[src[0]][src[1]] = 0;

        while (settled.size() != H * W) {
            if (pq.isEmpty()) {
                return;
            }
            Node u = pq.remove();

            if (settled.contains(u)) {
                continue;
            }
            settled.add(u);

            evalNeighbours(u, new int[] {u.dirX, u.dirY});
        }
    }

    private void evalNeighbours(Node u, int[] direction) {
        int edgeDistance = -1;
        int newDistance = -1;

        for (var dir : directions) {
            Node v = new Node(u.nodeX + dir[0], u.nodeY + dir[1], dist[u.nodeX][u.nodeY], dir[0], dir[1]);
            if (!settled.contains(v)) {
                if (dir[0] == u.dirX && dir[1] == u.dirY) {
                    edgeDistance = 1;
                } else if ((dir[0] == -direction[1] && dir[1] == direction[0]) || // Counterclockwise rotation
                        (dir[0] == direction[1] && dir[1] == -direction[0])) {
                    edgeDistance = 1001;
                }
                newDistance = dist[u.nodeX][u.nodeY] + edgeDistance;

                if (newDistance < dist[v.nodeX][v.nodeY]) {
                    dist[v.nodeX][v.nodeY] = newDistance;
                }
                pq.add(new Node(v.nodeX, v.nodeY, dist[v.nodeX][v.nodeY], dir[0], dir[1]));
            }
        }
    }
    class Node {
        public int nodeX;
        public int nodeY;
        public int cost;
        public int dirX;
        public int dirY;

        public Node() {
        }

        public Node(int nodeX, int nodeY, int cost, int dirX, int dirY) {
            this.nodeX = nodeX;
            this.nodeY = nodeY;
            this.cost = cost;
            this.dirX = dirX;
            this.dirY = dirY;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return nodeX == node.nodeX && nodeY == node.nodeY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeX, nodeY);
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day16.txt"));
            Task1 solver = new Task1(input);
            System.out.println("Result is: " + solver.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
