package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task2 {
	int[][] movements = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public int search(int[][] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				if (nums[i][j] == 0) {
					res += dfs(nums, i, j, 0);
				}
			}
		}
		return res;
	}

	private int dfs(int[][] nums, int i, int j, int curr) {
		int res = 0;
		for (var movement : movements) {
			int nextI = i + movement[0];
			int nextJ = j + movement[1];
			if (nextI >= 0 && nextI < nums.length && nextJ >= 0 && nextJ < nums[0].length) {
				if (nums[nextI][nextJ] == curr + 1) {
					if (curr == 8) {
						res++;
					} else {
						res += dfs(nums, nextI, nextJ, curr + 1);
					}
				}
			}
		}
		return res;
	}

	public int[][] getBoard(String input) {
		String[] lines = input.split("\n");
		int[][] board = new int[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++) {
			char[] chars = lines[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				board[i][j] = chars[j] - '0';
			}
		}
		return board;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day10.txt"));
			Task2 task2 = new Task2();
			int[][] board = task2.getBoard(input);
			System.out.println("RESULT IS: " + task2.search(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
