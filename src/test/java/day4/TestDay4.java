package day4;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay4 {
	@Test
	public void test1() {
		String input = "MMMSXXMASM\n" +
				"MSAMXMSMSA\n" +
				"AMXSXMAAMM\n" +
				"MSAMASMSMX\n" +
				"XMASAMXAMM\n" +
				"XXAMMXXAMA\n" +
				"SMSMSASXSS\n" +
				"SAXAMASAAA\n" +
				"MAMMMXMMMM\n" +
				"MXMXAXMASX";

		Task1 task1 = new Task1();
		assertEquals(18, task1.findOccurrencesFromString(input));
	}

	@Test
	public void test2() {
		String input = 
				"MMMSXXMSSM\n" +
				"MSAMXMSMSA\n" +
				"AMXSXMAAMM\n" +
				"MSAMASMSMX\n" +
				"XMASAMXAMM\n" +
				"XXAMMXXAMA\n" +
				"SMSMSASXSS\n" +
				"SAXAMASAAA\n" +
				"MAMMMXMMMM\n" +
				"MXMXAXMASX";

		Task2 task2 = new Task2();
		assertEquals(9, task2.findOccurrencesFromString(input));
	}


}