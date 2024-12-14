package day14;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Task2 extends Task1 {
	
	public void printBoard(List<RobotConfig> robotsConfigs, int step) {
		System.out.println("Next configuration is: " + step);
		char[][] board = new char[ny][nx];
		for (int i = 0; i < this.ny; i++) {
			for (int j = 0; j < this.nx; j++) {
				board[i][j] = ' ';
			}
		}
		for (var robotConfig : robotsConfigs) {
			board[robotConfig.pY][robotConfig.pX] = 'X';
		}
		for (int i = 0; i < this.ny; i++) {
			for (int j = 0; j < this.nx; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}

	public boolean checkUniqueRobots(Queue<RobotConfig> robotsConfigs) {
		Set<RobotConfig> allConfigs = new HashSet<>();
		allConfigs.addAll(robotsConfigs);
		
		return allConfigs.size() == robotsConfigs.size();
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Paths.get("src/main/resources/day14.txt"));
			Task2 task2 = new Task2();
			var configs = task2.init(input, 101, 103);
			task2.solve(configs, 10000);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
