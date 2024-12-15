package day15;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

	int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	char[] movements;
	char[][] board;
	
	public static void main(String[] args) {
		
	}

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
	
	public void push(int[] currPos, int[] movement) {
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
	}
}
