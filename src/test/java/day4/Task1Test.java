package day4;

import org.junit.Test;

import static org.junit.Assert.*;

public class Task1Test {
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
	
	
}