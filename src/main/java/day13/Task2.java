package day13;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends Task1 {
	
	public List<MachineConfig> parseConfig2(String input) {
		List<MachineConfig> configs = new ArrayList<>();
		String[] lines = input.split("\n");
		BigInteger ax = null, ay = null, bx = null, by = null, targetX, targetY;
		for (String line : lines) {
			if (line.startsWith("Button A:")) {
				ax = new BigInteger(line.split("\\+")[1].split(",")[0]);
				ay = new BigInteger(line.split("\\+")[2]);
			}
			if (line.startsWith("Button B:")) {
				bx = new BigInteger(line.split("\\+")[1].split(",")[0]);
				by = new BigInteger(line.split("\\+")[2]);
			}
			if (line.startsWith("Prize:")) {
				targetX = new BigInteger(line.split("=")[1].split(",")[0]);
				targetY = new BigInteger(line.split("=")[2]);
				MachineConfig config = new MachineConfig(ax, bx, ay, by, targetX.add(new BigInteger("10000000000000")), targetY.add(new BigInteger("10000000000000")));
				configs.add(config);
			}
		}
		return configs;
	}

	public BigInteger findResult2(List<MachineConfig> configs) {
		BigInteger total = new BigInteger("0");
		for (MachineConfig config : configs) {
			total = total.add(solve(config.aCostX, config.aCostY, config.bCostX, config.bCostY, config.targetX, config.targetY));
		}
		return total;
	}


	public BigInteger solve(BigInteger aX, BigInteger aY, BigInteger bX, BigInteger bY, BigInteger targetX, BigInteger targetY) {
		BigInteger resB = (targetY.multiply(aX).subtract(targetX.multiply(aY))).divide(aX.multiply(bY).subtract(aY.multiply(bX)));
		BigInteger resA = ((targetX.subtract(bX.multiply(resB)))).divide(aX);
		
		if (aY.multiply(resA).add(bY.multiply(resB)).longValue() == targetY.longValue() &&
		aX.multiply(resA).add(bX.multiply(resB)).longValue() == targetX.longValue()) {
			return resA.multiply(new BigInteger("3")).add(resB);
		} else {
			return new BigInteger("0");
		}
	}

	static class MachineConfig {
		BigInteger aCostX, aCostY, bCostX, bCostY, targetX, targetY;

		public MachineConfig(BigInteger aCostX, BigInteger bCostX, BigInteger aCostY, BigInteger bCostY, BigInteger targetX, BigInteger targetY) {
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
			Task2 task2 = new Task2();
			var configs = task2.parseConfig2(input);
			System.out.println("Result is: " + task2.findResult2(configs).longValue());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


}
