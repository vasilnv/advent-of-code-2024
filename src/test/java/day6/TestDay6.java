package day6;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay6 {
	
	@Test
	public void testTask1() {
		String input = "....#.....\n" +
				".........#\n" +
				"..........\n" +
				"..#.......\n" +
				".......#..\n" +
				"..........\n" +
				".#..^.....\n" +
				"........#.\n" +
				"#.........\n" +
				"......#...";

		Task1 task1 = new Task1();
		char[][] board = task1.generateBoard(input);
		assertEquals(41, task1.solve(board));
	}

	@Test
	public void testTask2() {
		String input = 
				"....#.....\n" +
				".........#\n" +
				"..........\n" +
				"..#.......\n" +
				".......#..\n" +
				"..........\n" +
				".#..^.....\n" +
				"........#.\n" +
				"#.........\n" +
				"......#...";

		Task2 task2 = new Task2();
		assertEquals(6, task2.findSolutions(input));
	}


}