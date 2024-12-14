package day14;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestDay14 {

	String input = "p=0,4 v=3,-3\n" +
			"p=6,3 v=-1,-3\n" +
			"p=10,3 v=-1,2\n" +
			"p=2,0 v=2,-1\n" +
			"p=0,0 v=1,3\n" +
			"p=3,0 v=-2,-2\n" +
			"p=7,6 v=-1,-3\n" +
			"p=3,0 v=-1,-2\n" +
			"p=9,3 v=2,3\n" +
			"p=7,3 v=-1,2\n" +
			"p=2,4 v=2,-3\n" +
			"p=9,5 v=-3,-3";

	@Test
	public void testTask1() {
		Task1 task1 = new Task1();
		List<Task1.RobotConfig> positions = task1.init(input, 11, 7);
		task1.solve(positions, 100);
		assertEquals(12, task1.calculateSafetyFactor(positions));
	}

	@Test
	public void testTask1Simple() {
		String input = "p=2,4 v=2,-3";

		Task1 task1 = new Task1();
		List<Task1.RobotConfig> positions = task1.init(input, 11, 7);
		task1.solve(positions, 1);
		assertEquals(4, positions.get(0).pX);
		assertEquals(1, positions.get(0).pY);

		positions = task1.init(input, 11, 7);
		task1.solve(positions, 2);
		assertEquals(6, positions.get(0).pX);
		assertEquals(5, positions.get(0).pY);

		positions = task1.init(input, 11, 7);
		task1.solve(positions, 3);
		assertEquals(8, positions.get(0).pX);
		assertEquals(2, positions.get(0).pY);

		positions = task1.init(input, 11, 7);
		task1.solve(positions, 4);
		assertEquals(10, positions.get(0).pX);
		assertEquals(6, positions.get(0).pY);

		positions = task1.init(input, 11, 7);
		task1.solve(positions, 5);
		assertEquals(1, positions.get(0).pX);
		assertEquals(3, positions.get(0).pY);
	}
}