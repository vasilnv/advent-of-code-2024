package day13;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestDay13 {
	String input = "Button A: X+94, Y+34\n" +
			"Button B: X+22, Y+67\n" +
			"Prize: X=8400, Y=5400\n" +
			"\n" +
			"Button A: X+26, Y+66\n" +
			"Button B: X+67, Y+21\n" +
			"Prize: X=12748, Y=12176\n" +
			"\n" +
			"Button A: X+17, Y+86\n" +
			"Button B: X+84, Y+37\n" +
			"Prize: X=7870, Y=6450\n" +
			"\n" +
			"Button A: X+69, Y+23\n" +
			"Button B: X+27, Y+71\n" +
			"Prize: X=18641, Y=10279";

	@Test
	public void testTask1ConfigParser() {
		Task1 task1 = new Task1();
		List<Task1.MachineConfig> configs = task1.parseConfig(input);
		assertEquals(4, configs.size());
		assertEquals(94, configs.get(0).aCostX);
		assertEquals(21, configs.get(1).bCostY);
		assertEquals(7870, configs.get(2).targetX);
	}

	@Test
	public void testTask1Solver() {
		Task1 task1 = new Task1();
		List<Task1.MachineConfig> configs = task1.parseConfig(input);
		var config = configs.get(0);
		assertEquals(280, task1.solve(config.aCostX, config.aCostY, config.bCostX, config.bCostY, config.targetX, config.targetY));
		assertEquals(480, task1.findResult(configs));
	}

	@Test
	public void testTask2Solver() {
		Task2 task2 = new Task2();
		List<Task2.MachineConfig> configs = task2.parseConfig2(input);
		var config = configs.get(0);
//		assertEquals(351351351631L, task2.solve(config.aCostX, config.aCostY, config.bCostX, config.bCostY, config.targetX, config.targetY));
//		assertEquals(1545093008511L, task2.findResult(configs));
	}
}