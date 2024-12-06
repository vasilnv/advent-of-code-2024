package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Task1 {
	public int solve(char[][] board) {
		int[] start = new int[2];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '^') {
					start = new int[] {i,j};
				}
			}
		}

		int startX = start[0];
		int startY = start[1];

		int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

		int currDirection = 0;
		int nextX;
		int nextY;
		char next;
		Set<String> visited = new HashSet<>();
		Set<String> visitedUnique = new HashSet<>();
		while (true) {
			if (visited.contains(startX + "," + startY + "," + currDirection)) {
				throw new CycleDetectedException("Cycle detected");
			}
			visited.add(startX + "," + startY + "," + currDirection);
			visitedUnique.add(startX + "," + startY);

			nextX = startX + directions[currDirection][0];
			nextY = startY + directions[currDirection][1];
			if (nextX < 0 || nextY < 0 || nextX >= board.length || nextY >= board[0].length) {
				return visitedUnique.size();
			}
			next = board[nextX][nextY];
			if (next == '#') {
				currDirection = (currDirection + 1) % directions.length;
			} else {
				startX = nextX;
				startY = nextY;
			}
		}
	}

	public char[][] generateBoard(String input) {
		String[] lines = input.split("\n");
		char[][] board = new char[lines.length][lines[0].length()];
		
		for (int i = 0; i < lines.length; i++) {
			board[i] = lines[i].toCharArray();
		}
		return board;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day6.txt"));
			Task1 task1 = new Task1();
			char [][]board = task1.generateBoard(input);
			System.out.println("Result is: " + task1.solve(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}