package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task2 {
	public int findSolutions(String input) {
		int result = 0;
		Task1 task1 = new Task1();
		char[][] board = task1.generateBoard(input);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == '.') {
					board[i][j] = '#';
					try{
						task1.solve(board);
					} catch (CycleDetectedException e){
						result++;
					}
					board[i][j] = '.';
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day6.txt"));
			Task2 task2 = new Task2();
			int result = task2.findSolutions(input);
			System.out.println("Result is: " + result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}