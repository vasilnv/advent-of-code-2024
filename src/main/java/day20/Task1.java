package day20;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task1 {
    char[][] board;
    long[][] distances;
    int[] start;
    int[] end;
    int[][] movements = {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public Task1(String input) {
        String[] lines = input.split("\n");
        board = new char[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            board[i] = lines[i].toCharArray();
        }

        distances = new long[board.length][board[0].length];
        start = new int[2];
        end = new int[2];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                }
                if (board[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        var currpos = new int[2];
        currpos[0] = start[0];
        currpos[1] = start[1];
        boolean[][] visited = new boolean[board.length][board[0].length];
        long currDistance = 0;
        while (currpos[0] != end[0] || currpos[1] != end[1]) {
            distances[currpos[0]][currpos[1]] = currDistance;
            visited[currpos[0]][currpos[1]] = true;
            currDistance++;
            for (var movement : movements) {
                if (!visited[currpos[0] + movement[0]][currpos[1] + movement[1]] &&
                        (board[currpos[0] + movement[0]][currpos[1] + movement[1]] == '.' ||
                        board[currpos[0] + movement[0]][currpos[1] + movement[1]] == 'E')) {
                    currpos[0] += movement[0];
                    currpos[1] += movement[1];
                    break;
                }
            }
        }
        distances[currpos[0]][currpos[1]] = currDistance;
    }

    public int solve(int threshold) {
        int[] currPos = new int[2];
        currPos[0] = start[0];
        currPos[1] = start[1];
        boolean[][] visited = new boolean[board.length][board[0].length];
        int result = 0;
        while (currPos[0] != end[0] || currPos[1] != end[1]) {
            long currDist = distances[currPos[0]][currPos[1]];
            visited[currPos[0]][currPos[1]] = true;
            for (var movement : movements) {
                var tempPos = currPos;
                int newPosX = tempPos[0] + 2 * movement[0];
                int newPosY = tempPos[1] + 2 * movement[1];
                if (areInBoundaries(newPosX, newPosY)
                        && board[tempPos[0] + movement[0]][tempPos[1] + movement[1]] == '.') {
                    long saved = distances[newPosX][newPosY] - currDist - 2;
//                    System.out.println("SAVING " + saved + " FROM " + currPos[0] + " " + currPos[1]+ " TO " + tempPos[0] + " " + tempPos[1]);
                    if (saved >= threshold) {
                        result++;
                    }
                }
            }
            for (var movement : movements) {
                if (!visited[currPos[0] + movement[0]][currPos[1] + movement[1]] &&
                        (board[currPos[0] + movement[0]][currPos[1] + movement[1]] == '.' ||
                                board[currPos[0] + movement[0]][currPos[1] + movement[1]] == 'E')) {
                    currPos[0] += movement[0];
                    currPos[1] += movement[1];
                    break;
                }
            }

        }
        return result;
    }

    protected boolean areInBoundaries(int tempPosX, int tempPosY) {
        return tempPosX >= 0 && tempPosX < board.length &&
                tempPosY >= 0 && tempPosY < board[0].length;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day20.txt"));
            Task1 task1 = new Task1(input);
            System.out.println("Result is: " + task1.solve(100));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
