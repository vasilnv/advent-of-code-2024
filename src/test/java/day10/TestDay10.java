package day10;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay10 {
	@Test
	public void testTask1() {
		String input = "89010123\n" +
				"78121874\n" +
				"87430965\n" +
				"96549874\n" +
				"45678903\n" +
				"32019012\n" +
				"01329801\n" +
				"10456732\n";
		
		Task1 task1 = new Task1();
		int[][] board = task1.getBoard(input);
		assertEquals(36, task1.search(board));
	}

	@Test
	public void testTask2() {
		String input = "89010123\n" +
				"78121874\n" +
				"87430965\n" +
				"96549874\n" +
				"45678903\n" +
				"32019012\n" +
				"01329801\n" +
				"10456732\n";

		Task2 task2 = new Task2();
		int[][] board = task2.getBoard(input);
		assertEquals(81, task2.search(board));
	}

}