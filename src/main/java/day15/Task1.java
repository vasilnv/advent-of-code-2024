package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

	int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	char[] movements;
	char[][] board;
	
	public char[][] convertBoard(String smallInput) {
		String[] lines = smallInput.split("\n");
		List<String> boardRows = new ArrayList<>();
		for (String line : lines) {
			if (line.isEmpty()) {
				break;
			}
			boardRows.add(line);
		}
		char[][] board = new char[boardRows.size()][];
		for (int i = 0; i < boardRows.size(); i++) {
			board[i] = boardRows.get(i).toCharArray();
		}
		this.board = board;
		return board;
	}

	public char[] getMovements(String smallInput, int startFromLine) {
		String[] lines = smallInput.split("\n");
		String movements = "";
		for (int i = startFromLine; i < lines.length; i++) {
			if (!lines[i].equals("\n")) {
				movements += lines[i];
			}
		}
		this.movements = movements.toCharArray();
		return movements.toCharArray();
	}

	public boolean canPush(int[] currPos, int[] movement) {
		int currX = currPos[0];
		int currY = currPos[1];
		
		int movementX = movement[0];
		int movementY = movement[1];
		
		if (board[currX + movementX][currY + movementY] == '.') {
			return true;
		}

		if (board[currX + movementX][currY + movementY] == '#') {
			return false;
		}
		
		int tmpX = currX + movementX;
		int tmpY = currY + movementY;
		while (board[tmpX][tmpY] == 'O') {
			tmpX += movementX;
			tmpY += movementY;
		}
		if (board[tmpX][tmpY] == '.') {
			return true;
		}
		return false;
	}
	
	public int[] push(int[] currPos, int[] movement) {
		int currX = currPos[0];
		int currY = currPos[1];
		
		int movementX = movement[0];
		int movementY = movement[1];
		
		board[currX][currY] = '.';
		
		int newRobotPosX = currX + movementX;
		int newRobotPosY = currY + movementY;
		
		int tmpX = currX + movementX;
		int tmpY = currY + movementY;
		
		while (board[tmpX][tmpY] == 'O') {
			tmpX += movementX;
			tmpY += movementY;
		}
		
		board[tmpX][tmpY] = 'O';
		board[newRobotPosX][newRobotPosY] = '@';
		return new int[]{newRobotPosX, newRobotPosY};
	}

	public void playAll() {
		int[] nextPos = getStartPosition();
		for (var movement : movements) {
			int[] dir = getDirection(movement);
			if (canPush(nextPos, dir)) {
				nextPos = push(nextPos, dir);
			}
		}
	}

	private int[] getStartPosition() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '@') {
					return new int[]{i, j};
				}
			}
		}
		return null;
	}

	public int calculateRes() {
		int total = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O') {
					total += 100 * i + j;
				}
			}
		}
		return total;
	}

	private int[] getDirection(char movement) {
		if (movement == 'v') {
			return directions[2];
		}
		if (movement == '<') {
			return directions[1];
		}
		if (movement == '^') {
			return directions[3];
		}
		if (movement == '>') {
			return directions[0];
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day15.txt"));
			Task1 task1 = new Task1();
			char[][] board = task1.convertBoard(input);
			char[] movements = task1.getMovements(input, board.length);

			task1.playAll();
			System.out.println("Result is: " + task1.calculateRes());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
