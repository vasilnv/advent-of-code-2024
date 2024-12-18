package day15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Task2 {
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
			String currLine = "";
			for (char c : line.toCharArray()) {
				if (c == '.') {
					currLine += "..";
				} else if (c == 'O') {
					currLine += "[]";
				} else if (c == '@') {
					currLine += "@.";
				} else if (c == '#') {
					currLine += "##";
				}
			}
			boardRows.add(currLine);
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
	
	public int[] pushRight(int[] currPos, int[] movement) {
		int currX = currPos[0];
		int currY = currPos[1];

		int movementX = movement[0];
		int movementY = movement[1];

		board[currX][currY] = '.';

		int newRobotPosX = currX + movementX;
		int newRobotPosY = currY + movementY;

		int tmpX = currX + movementX;
		int tmpY = currY + movementY;

		while (board[tmpX][tmpY] == '[' || board[tmpX][tmpY] == ']') {
			tmpX += movementX;
			tmpY += movementY;
		}

		board[tmpX][tmpY] = ']';
		
		while (tmpX != newRobotPosX || tmpY != newRobotPosY) {
			tmpX -= movementX;
			tmpY -= movementY;
			if (board[tmpX][tmpY] == ']') {board[tmpX][tmpY] = '[';}
			else if (board[tmpX][tmpY] == '[') {board[tmpX][tmpY] = ']';}
		}
		
		board[newRobotPosX][newRobotPosY] = '@';
		return new int[]{newRobotPosX, newRobotPosY};
	}

	public int[] pushLeft(int[] currPos, int[] movement) {
		int currX = currPos[0];
		int currY = currPos[1];

		int movementX = movement[0];
		int movementY = movement[1];

		board[currX][currY] = '.';

		int newRobotPosX = currX + movementX;
		int newRobotPosY = currY + movementY;

		int tmpX = currX + movementX;
		int tmpY = currY + movementY;

		while (board[tmpX][tmpY] == '[' || board[tmpX][tmpY] == ']') {
			tmpX += movementX;
			tmpY += movementY;
		}

		board[tmpX][tmpY] = '[';

		while (tmpX != newRobotPosX || tmpY != newRobotPosY) {
			tmpX -= movementX;
			tmpY -= movementY;
			if (board[tmpX][tmpY] == '[') {board[tmpX][tmpY] = ']';}
			else if (board[tmpX][tmpY] == ']') {board[tmpX][tmpY] = '[';}
		}

		board[newRobotPosX][newRobotPosY] = '@';
		return new int[]{newRobotPosX, newRobotPosY};
	}

	public void playAll() {
		int[] nextPos = getStartPosition();
		for (var movement : movements) {
//			System.out.println("PUSHING " + movement);
			int[] dir = getDirection(movement);
			if (movement == '>') {
				if (canPushLeftRight(nextPos, dir)) {
					nextPos = pushRight(nextPos, dir);
				} else {
//					System.out.println("CANNOT PUSH RIGHT");
				}
			} else if (movement == '<') {
				if (canPushLeftRight(nextPos, dir)) {
					nextPos = pushLeft(nextPos, dir);
				} else {
//					System.out.println("CANNOT PUSH LEFT");
				}
			} else if (movement == '^' || movement == 'v') {
				if (canPushUpDown(nextPos, dir)) {
					nextPos = pushUpDown(nextPos, dir);
				} else {
//					System.out.println("CANNOT PUSH UP DOWN");
				}
			}
//			printBoard();
		}
	}

	private void printBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	private boolean canPushUpDown(int[] currPos, int[] movement) {
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
			if (board[tmpX][tmpY] == ']') {
				tmpY --;
			}
			Queue<Box> queue = new LinkedList<>();
			Box nextPos = new Box(tmpX, tmpY);
			queue.add(nextPos);
			while (!queue.isEmpty()) {
				nextPos = queue.poll();
				if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1] == '#' || board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 1] == '#' ) {
					return false;
				}
				if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1] == '[') {
					queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1));
				}
				if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 1] == '[') {
					queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1 + 1));
				}
				if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 - 1] == '[') {
					queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1 - 1));
				}

			}

			return board[nextPos.x1 + movement[0]][nextPos.y1 + movement[1]] == '.' && board[nextPos.x1 + movement[0]][nextPos.y1 + movement[1] + 1] == '.';
	}

	private boolean canPushLeftRight(int[] currPos, int[] movement) {
		int nextX = currPos[0] + movement[0];
		int nextY = currPos[1] + movement[1];
		while (board[nextX][nextY] == '[' || board[nextX][nextY] == ']') {
			nextX += movement[0];
			nextY += movement[1];
		}
		return board[nextX][nextY] == '.';
	}

	public int[] pushUpDown(int[] currPos, int[] movement) {
		int currX = currPos[0];
		int currY = currPos[1];

		int movementX = movement[0];
		int movementY = movement[1];

		board[currX][currY] = '.';

		int newRobotPosX = currX + movementX;
		int newRobotPosY = currY + movementY;

		int tmpX = currX + movementX;
		int tmpY = currY + movementY;
		
		boolean isInitOpening = board[tmpX][tmpY] == '[';

		if (isInitOpening) {
			int tmpX1 = tmpX;
			int tmpY1 = tmpY;
			board[tmpX1][tmpY1] = '@';
			board[tmpX1][tmpY1 + 1] = '.';
			moveBoxesUpwardsDownwards(tmpX1, tmpY1, movement);
		} else if (board[tmpX][tmpY] == ']') {
			int tmpX1 = tmpX;
			int tmpY1 = tmpY - 1;
			board[tmpX1][tmpY1] = '.';
			board[tmpX1][tmpY1 + 1] = '@';
			moveBoxesUpwardsDownwards(tmpX1, tmpY1, movement);
		}

		board[newRobotPosX][newRobotPosY] = '@';
		return new int[]{newRobotPosX, newRobotPosY};
	}

	private void moveBoxesUpwardsDownwards(int tmpX1, int tmpY1, int[] movement) {
		Queue<Box> queue = new LinkedList<>();
		queue.add(new Box(tmpX1, tmpY1));
		while (!queue.isEmpty()) {
			Box nextPos = queue.poll();
			if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1] == '[') {
				/*
				....[]....
				....[]....
				 */
				queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1));
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1] = '.';
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 1] = '.';
			} 
			if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 1] == '[') {
				/*
				.......[]...
				......[]....
				 */
				queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1 + 1));
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 1] = '.';
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 + 2] = '.';
			}
			if (board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 - 1] == '[') {
				/*
				.....[].....
				......[]....
				 */
				queue.add(new Box(movement[0] + nextPos.x1, movement[1] + nextPos.y1 - 1));
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1 - 1] = '.';
				board[movement[0] + nextPos.x1][movement[1] + nextPos.y1] = '.';
			}
			board[nextPos.x1 + movement[0]][nextPos.y1 + movement[1]] = '[';
			board[nextPos.x1+ movement[0]][nextPos.y1 + 1 + movement[1]] = ']';

//			board[nextPos.x1][nextPos.y1] = '.';
//			board[nextPos.x2][nextPos.y2] = '.';
		}
		
		/*  
	......
	[]....
	.[]...
	..[]..
	...[].
	...@..
	
	......
	[]....
	.[]...
	..[]..
	...[].
	...@..
*/

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
				if (board[i][j] == '[') {
					total += 100 * i + j;
				}
			}
		}
		printBoard();
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
	
	class Box {
		int x1, y1;
		char c;
		public Box(int x1, int y1) {
			this.x1 = x1;
			this.y1 = y1;
		}
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day15.txt"));
			Task2 task2 = new Task2();
			char[][] board = task2.convertBoard(input);
			char[] movements = task2.getMovements(input, board.length);

			task2.playAll();
			System.out.println("Result is: " + task2.calculateRes());
			// 1543338

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
