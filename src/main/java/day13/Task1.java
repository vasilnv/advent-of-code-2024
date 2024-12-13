package day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

	public static final int MAX_BUTTON_HITS = 100;

	public List<MachineConfig> parseConfig(String input) {
		List<MachineConfig> configs = new ArrayList<>();
		String[] lines = input.split("\n");
		long ax = 0, ay = 0, bx = 0, by = 0, targetX = 0, targetY=0;
		for (String line : lines) {
			if (line.startsWith("Button A:")) {
				ax = Integer.parseInt(line.split("\\+")[1].split(",")[0]);
				ay = Integer.parseInt(line.split("\\+")[2]);
			}
			if (line.startsWith("Button B:")) {
				bx = Integer.parseInt(line.split("\\+")[1].split(",")[0]);
				by = Integer.parseInt(line.split("\\+")[2]);
			}
			if (line.startsWith("Prize:")) {
				targetX = Integer.parseInt(line.split("=")[1].split(",")[0]);
				targetY = Integer.parseInt(line.split("=")[2]);
				MachineConfig config = new MachineConfig(ax, bx, ay, by, targetX, targetY);
				configs.add(config);
			}
		}
		return configs;
	}
	
	public int findResult(List<MachineConfig> configs) {
		int total = 0;
		for (MachineConfig config : configs) {
			total += solve(config.aCostX, config.aCostY, config.bCostX, config.bCostY, config.targetX, config.targetY);
		}
		return total;
	}
	
	public int solve(long aX, long aY, long bX, long bY, long targetX, long targetY) {
		long[][] dpX = new long[MAX_BUTTON_HITS][MAX_BUTTON_HITS];
		long[][] dpY = new long[MAX_BUTTON_HITS][MAX_BUTTON_HITS];

		createDPArray(aX, bX, dpX);
		createDPArray(aY, bY, dpY);
	
		int currentMin = Integer.MAX_VALUE;
		boolean isChanged = false;
		for (int i = 0; i < MAX_BUTTON_HITS; i++) {
			for (int j = 0; j < MAX_BUTTON_HITS; j++) {
				if (dpX[i][j] == targetX && dpY[i][j] == targetY) {
					if (i * 4 + j < currentMin) {
						currentMin = i * 3 + j;
						isChanged = true;
					}
				}
			}
		}
		return isChanged ? currentMin : 0;
	}

	private void createDPArray(long aCost, long bCost, long[][] dp) {
		for (int i = 0; i < MAX_BUTTON_HITS; i++) {
			for (int j = 0; j < MAX_BUTTON_HITS; j++) {
				dp[i][j] = i * aCost + j * bCost;
			}
		}
	}
	
	static class MachineConfig {
		long aCostX = 0;
		long bCostX = 0;
		long aCostY = 0;
		long bCostY = 0;
		long targetX = 0;
		long targetY = 0;

		public MachineConfig(long aCostX, long bCostX, long aCostY, long bCostY, long targetX, long targetY) {
			this.aCostX = aCostX;
			this.bCostX = bCostX;
			this.aCostY = aCostY;
			this.bCostY = bCostY;
			this.targetX = targetX;
			this.targetY = targetY;
		}
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day13.txt"));
			Task1 task1 = new Task1();
			var configs = task1.parseConfig(input);
			System.out.println("Result is: " + task1.findResult(configs));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
