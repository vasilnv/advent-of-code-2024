package day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Task1 {
	int[][] movements = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	
	public int search(int[][] nums) {
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				Set<Pair> visited = new HashSet<>();
				if (nums[i][j] == 0) {
					dfs(nums, i, j, 0, visited);
					System.out.println("ADDING TRAILHEAD " + i + " " + j + " WITH SIZE " + visited.size());
					res += visited.size();
				}
			}
		}
		return res;
	}

	private int dfs(int[][] nums, int i, int j, int curr, Set<Pair> visited) {
		int res = 0;
		for (var movement : movements) {
			int nextI = i + movement[0];
			int nextJ = j + movement[1];
			if (nextI >= 0 && nextI < nums.length && nextJ >= 0 && nextJ < nums[0].length) {
				if (nums[nextI][nextJ] == curr + 1) {
					if (curr == 8) {
						System.out.println("VISITED " + nextI + " " + nextJ + " AND IT WAS A 9");
						visited.add(new Pair(nextI, nextJ));
						res++;
					} else {
						res += dfs(nums, nextI, nextJ, curr + 1, visited);
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

	class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return this.x == ((Pair)obj).x && this.y == ((Pair)obj).y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.x, this.y);
		}
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day10.txt"));
			Task1 task1 = new Task1();
			int[][] board = task1.getBoard(input);
			System.out.println("RESULT IS: " + task1.search(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
