package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Task1 {

	public char[][] initBoard(String board) {
		String[] lines = board.split("\n");
		char[][] resBoard = new char[lines.length][lines[0].length()];
		for (int i = 0; i < resBoard.length; i++) {
			resBoard[i] = lines[i].toCharArray();
		}
		return resBoard;
	}

	public long getTotalPrice(char[][] board) {
		boolean[][] visited = new boolean[board.length][board[0].length];
		var totalPrice = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (!visited[i][j]) {
					totalPrice += bfs(i, j, visited, board);
				}
			}
		}
		return totalPrice;
	}

	int[][] movements = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
	
	private int bfs(int i, int j, boolean[][] visited, char[][] board) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{i, j});
		int area = 0;
		int perimeter = 0;
		while (!queue.isEmpty()) {
			int[] currPos = queue.poll();
//			System.out.println("CHECKING POSITION " + currPos[0] + " " + currPos[1] + " FOR ENTRY " + board[currPos[0]][currPos[1]]);
			visited[currPos[0]][currPos[1]] = true;
			area++;
			for (var movement : movements) {
				if (currPos[0] + movement[0] >= 0 && currPos[0] + movement[0] < board.length &&
						currPos[1] + movement[1] >= 0 && currPos[1] + movement[1] < board[0].length &&
						board[currPos[0] + movement[0]][currPos[1] + movement[1]] == board[currPos[0]][currPos[1]]) {
					if (!visited[currPos[0] + movement[0]][currPos[1] + movement[1]]) {
						queue.add(new int[]{currPos[0] + movement[0], currPos[1] + movement[1]});
						visited[currPos[0] + movement[0]][currPos[1] + movement[1]] = true;
					}
				} else {
					perimeter++;
				}
			}
		}
		return area * perimeter;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day12.txt"));
			Task1 task1 = new Task1();
			char[][] board = task1.initBoard(input);
			System.out.println("Result is: " + task1.getTotalPrice(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
