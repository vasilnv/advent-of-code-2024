package day12;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay12 {
	String simpleInput;
	String input1;
	String input2;
	
	@Before
	public void setUp() throws Exception {
		simpleInput = "AAAA\n" +
				"BBCD\n" +
				"BBCC\n" +
				"EEEC";
		
		input1 = "OOOOO\n" +
				"OXOXO\n" +
				"OOOOO\n" +
				"OXOXO\n" +
				"OOOOO";
		
		input2 = "RRRRIICCFF\n" +
				"RRRRIICCCF\n" +
				"VVRRRCCFFF\n" +
				"VVRCCCJFFF\n" +
				"VVVVCJJCFE\n" +
				"VVIVCCJJEE\n" +
				"VVIIICJJEE\n" +
				"MIIIIIJJEE\n" +
				"MIIISIJEEE\n" +
				"MMMISSJEEE";
	}
	
	@Test
	public void testTask1Simple() {
		Task1 task1 = new Task1();
		char[][] board = task1.initBoard(simpleInput);
		assertEquals(140, task1.getTotalPrice(board));
	}

	@Test
	public void testTask1Input1() {
		Task1 task1 = new Task1();
		char[][] board = task1.initBoard(input1);
		assertEquals(772, task1.getTotalPrice(board));
	}

	@Test
	public void testTask1Input2() {
		Task1 task1 = new Task1();
		char[][] board = task1.initBoard(input2);
		assertEquals(1930, task1.getTotalPrice(board));
	}
	
	@Test
	public void testTask2Simple() {
		Task2 task2 = new Task2();
		char[][] board = task2.initBoard(simpleInput);
		assertEquals(80, task2.getTotalPrice(board));

	}

	@Test
	public void testTask2Input1() {
		Task2 task2 = new Task2();
		char[][] board = task2.initBoard(input1);
		assertEquals(436, task2.getTotalPrice(board));
	}

	@Test
	public void testTask2Input2() {
		Task2 task2 = new Task2();
		char[][] board = task2.initBoard(input2);
		assertEquals(1206, task2.getTotalPrice(board));
	}

	@Test
	public void testTask2CustomInput() {
		String custom = "EEEEE\n" +
				"EXXXX\n" +
				"EEEEE\n" +
				"EXXXX\n" +
				"EEEEE";
		Task2 task2 = new Task2();
		char[][] board = task2.initBoard(custom);
		assertEquals(236, task2.getTotalPrice(board));
		
		custom = "AAAAAA\n" +
				"AAABBA\n" +
				"AAABBA\n" +
				"ABBAAA\n" +
				"ABBAAA\n" +
				"AAAAAA";
		task2 = new Task2();
		board = task2.initBoard(custom);
		assertEquals(368, task2.getTotalPrice(board));
	}
	

}