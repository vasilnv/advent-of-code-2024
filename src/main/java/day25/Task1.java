package day25;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    List<List<Integer>> locks;
    List<List<Integer>> keys;

    public Task1(String input) {
        locks = new ArrayList<>();
        keys = new ArrayList<>();
        String[] lines = input.split("\n");
        for (int i = 0; i < lines.length; i++) {
            char[][] board = new char[7][5];
            int j = 0;
            while (i < lines.length && !lines[i].isEmpty()) {
                board[j] = lines[i].toCharArray();
                i++;
                j++;
            }
            convertBoard(board);
        }
    }

    private void convertBoard(char[][] board) {
        if ("#####".equals(new String(board[0]))) {
            var currLock = new ArrayList<Integer>();
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] == '.') {
                        currLock.add(j - 1);
                        break;
                    }
                }
            }
            locks.add(currLock);
        }

        if ("#####".equals(new String(board[board.length - 1]))) {
            var currKey = new ArrayList<Integer>();
            for (int i = 0; i < board[0].length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i] == '#') {
                        currKey.add(board.length - j - 1);
                        break;
                    }
                }
            }
            keys.add(currKey);
        }

    }

    public int solve() {
        int total = 0;
        for (var key : keys) {
            for (var lock : locks) {
                if (checkIfCompatible(key, lock)) {
                    total++;
                }
            }
        }
        return total;
    }

    private boolean checkIfCompatible(List<Integer> key, List<Integer> lock) {
        for (int i = 0; i < lock.size(); i++) {
            if (lock.get(i) + key.get(i) > 5) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day25.txt"));
            Task1 task1 = new Task1(input);
            System.out.println("Result is: " + task1.solve());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
