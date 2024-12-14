package day14;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Task1 {
	int nx;
	int ny;
	
	public List<RobotConfig> solve(List<RobotConfig> robotsConfigs, int totalMovements) {
		Queue<RobotConfig> queue = new LinkedList<>();
		for (int i = 0; i < robotsConfigs.size(); i++) {
			queue.add(robotsConfigs.get(i));
		}	
		
		for (int i = 0; i < totalMovements; i++) {
			List<RobotConfig> nextRound = new LinkedList<>();
			while (!queue.isEmpty()) {
				RobotConfig nextRobot = queue.poll();
				moveRobot(nextRobot);
				nextRound.add(nextRobot);
			}
			queue.addAll(nextRound);
		}

		List<RobotConfig> finalPositions = new LinkedList<>();
		while (!queue.isEmpty()) {
			RobotConfig nextRobot = queue.poll();
			finalPositions.add(nextRobot);
		}
		
		return finalPositions;
		
	}

	private RobotConfig moveRobot(RobotConfig nextRobot) {
		int x = nextRobot.pX;
		int y = nextRobot.pY;
		
		int vx = nextRobot.vX;
		int vy = nextRobot.vY;
		
		if (x + vx >= nx) {
			nextRobot.pX = vx - nx + x;
		} else if (x + vx < 0) {
			nextRobot.pX = nx + vx + x;
		} else {
			nextRobot.pX = x + vx;
		}

		if (y + vy >= ny) {
			nextRobot.pY = vy - ny + y;
		} else if (y + vy < 0) {
			nextRobot.pY = ny + vy + y;
		} else {
			nextRobot.pY = y + vy;
		}
		
		return nextRobot;
	}

	public List<RobotConfig> init(String input, int tilesWide, int tilesTall) {
		this.nx = tilesWide;
		this.ny = tilesTall;
		
		String[] lines = input.split("\n");
		List<RobotConfig> robotsConfigs = new LinkedList<>();
		for (String line : lines) {
			String[] tokens = line.split(" ");
			String[] position = tokens[0].split("=")[1].split(",");
			Integer x = Integer.parseInt(position[0]);
			Integer y = Integer.parseInt(position[1]);
			
			String[] velocity = tokens[1].split("=")[1].split(",");
			Integer vx = Integer.parseInt(velocity[0]);
			Integer vy = Integer.parseInt(velocity[1]);
			RobotConfig config = new RobotConfig(x, y, vx, vy);
			robotsConfigs.add(config);
		}
		return robotsConfigs;
	}

	public int calculateSafetyFactor(List<RobotConfig> positions) {
		int sumQ1 = 0;
		int sumQ2 = 0;
		int sumQ3 = 0;
		int sumQ4 = 0;
		
		int hY = ny / 2;
		int hX = nx / 2;
		
		for (var robot : positions) {
			if (robot.pX < hX && robot.pY < hY) {
				sumQ1++;
			}
			if (robot.pX > hX && robot.pY < hY) {
				sumQ2++;
			}
			if (robot.pX < hX && robot.pY > hY) {
				sumQ3++;
			}
			if (robot.pX > hX && robot.pY > hY) {
				sumQ4++;
			}
		}
		return sumQ1 * sumQ2 * sumQ3 * sumQ4;
	}

	static class RobotConfig {
		int pX;
		int pY;
		
		int vX;
		int vY;

		public RobotConfig(int pX, int pY, int vX, int vY) {
			this.pX = pX;
			this.pY = pY;
			this.vX = vX;
			this.vY = vY;
		}
	}
	public static void main(String[] args) {
		try {
			String input = Files.readString(Paths.get("src/main/resources/day14.txt"));
			Task1 task1 = new Task1();
			var configs = task1.init(input, 101, 103);
			task1.solve(configs, 100);
			System.out.println("Result is: " + task1.calculateSafetyFactor(configs));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
