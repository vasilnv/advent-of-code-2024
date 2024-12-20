package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Task2 extends Task1 {
    int result = 0;
    Set<Point> allPoints = new HashSet<>();

    public Task2(String input) {
        super(input);
    }

    public int solve(int threshold) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'S') {
                    allPoints.add(new Point(i, j));
                }
                if (distances[i][j] > 0) {
                    allPoints.add(new Point(i, j));
                }
            }
        }

        for (var point : allPoints) {
            allPoints.stream().filter((p) -> Math.abs(point.x - p.x) + Math.abs(point.y - p.y) <= 20)
                    .filter((p) -> distances[p.x][p.y] < distances[point.x][point.y])
                    .forEach((p) -> {
                long dist = Math.abs(point.x - p.x) + Math.abs(point.y - p.y);
                long diff = distances[point.x][point.y] - distances[p.x][p.y] - dist;

                if (diff >= threshold) {
                    result += 1;
                }
            });
        }
        return result;
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day20.txt"));
            Task2 solver = new Task2(input);
            System.out.println("Result is: " + solver.solve(100));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
