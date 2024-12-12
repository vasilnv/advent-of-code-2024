package day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Task2 extends Task1 {
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
	int[][] corners = new int[][] {{1,-1}, {1,1}, {-1,1}, {-1,-1}};
	
	private int bfs(int i, int j, boolean[][] visited, char[][] board) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{i, j});
		int area = 0;
		int perimeter = 0;
		Set<String> revertedPos = new HashSet<>();
		while (!queue.isEmpty()) {
			int[] currPos = queue.poll();

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
				} 
			}
			
			try {
				for (var corner : corners) {
//					System.out.println("CHECKING CORNER " + corner[0] + " " + corner[1]);
					int currRow = currPos[0]; // 0
					int currCol = currPos[1]; // 0
					int newRow = currPos[0] + corner[0]; // -1
					int newCol = currPos[1] + corner[1]; // 1

					if ((newRow < 0 || newRow >= board.length) && (newCol < 0 || newCol >= board[0].length)) {
						perimeter++;
					} else if ((newRow < 0 || newRow >= board.length) && board[currRow][newCol] != board[currRow][currCol]) {
						perimeter++;
					} else if ((newCol < 0 || newCol >= board[0].length) && board[newRow][currCol] != board[currRow][currCol]) {
						perimeter++;
					} else if (newRow >= 0 && newRow < board.length &&
							newCol >= 0 && newCol < board[0].length &&
							board[newRow][currCol] != board[currRow][currCol] && 
							board[currRow][newCol] != board[currRow][currCol]) {
						perimeter++;
					} else if (newRow >= 0 && newRow < board.length &&
							newCol >= 0 && newCol < board[0].length &&
							board[newRow][newCol] != board[currPos[0]][currPos[1]] &&
							board[newRow][currCol] == board[currPos[0]][currPos[1]] &&
							board[currRow][newCol] == board[currPos[0]][currPos[1]]) {
						perimeter++;
					}
				}
			} catch (Exception e) {
				System.err.println("ERROR FOR " + currPos[0] + " " + currPos[1]);
				e.printStackTrace();

			}

		}
		return area * perimeter;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day12.txt"));
			Task2 task2 = new Task2();
			char[][] board = task2.initBoard(input);
			System.out.println("Result is: " + task2.getTotalPrice(board));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
