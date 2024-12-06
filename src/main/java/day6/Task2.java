package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task2 {
	int[] start;

	public boolean solve(char[][] board) {
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
		int nextX = startX + directions[currDirection][0];
		int nextY = startY + directions[currDirection][1];
		char next = board[nextX][nextY];
		Set<String> visited = new HashSet<>();
		while (true) {
			if (visited.contains(startX + "," + startY + "," + currDirection)) {
				return true;
			}
			visited.add(startX + "," + startY + "," + currDirection);

			nextX = startX + directions[currDirection][0];
			nextY = startY + directions[currDirection][1];
			if (nextX < 0 || nextY < 0 || nextX >= board.length || nextY >= board[0].length) {
				return false;
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


	public int findSolutions(char[][] board) {
		int result = 0;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					board[i][j] = '#';
					if (solve(board)) {
						result++;
					}
					board[i][j] = '.';
				}
			}
		}
		return result;
	}

	private void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day6.txt"));
			Task2 task2 = new Task2();
			char [][]board = task2.generateBoard(input);
			int result = task2.findSolutions(board);
			System.out.println("RESULT IS " + result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}