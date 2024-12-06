package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class Task1 {
	int[] start;
	
	public int solve(char[][] board) {
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
		char next = board[startX + directions[currDirection][0]][startY + directions[currDirection][1]];
		Set<String> visited = new HashSet<>();
		while (true) {
			if (next == '#') {
				currDirection = getCurrDirection(currDirection, directions);
			}
			visited.add(startX + "," + startY);
			startX += directions[currDirection][0];
			startY += directions[currDirection][1];
			if (board[startX][startY] == '.' && (startX == 0 || startY == 0 || startX == board.length - 1 || startY == board[0].length - 1)) {
				visited.add(startX + "," + startY);
				visited.stream().forEach(pair -> System.out.println("PAIR: " + pair));
				return visited.size();
			}
			next = board[startX + directions[currDirection][0]][startY + directions[currDirection][1]];
		}
	}

	private static int getCurrDirection(int currDirection, int[][] directions) {
		currDirection++;
		if (currDirection == directions.length) {
			currDirection = 0;
		}
		return currDirection;
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
			System.out.println("RESULT IS " + task1.solve(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}