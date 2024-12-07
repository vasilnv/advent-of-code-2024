package day7;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestDay7 {
	String input;
	@Before
	public void setUp() throws Exception {
		input = "190: 10 19\n" +
				"3267: 81 40 27\n" +
				"83: 17 5\n" +
				"156: 15 6\n" +
				"7290: 6 8 6 15\n" +
				"161011: 16 10 13\n" +
				"192: 17 8 14\n" +
				"21037: 9 7 18 13\n" +
				"292: 11 6 16 20";
	}
	
	@Test
	public void testTask1() {
		Task1 task1 = new Task1();
		List<List<Long>> sequences = task1.getSequences(input);
		assertFalse(sequences.isEmpty());
		assertEquals(9, sequences.size());
		assertEquals(Long.valueOf(190), sequences.get(0).get(sequences.get(0).size() - 1));
		
		assertTrue(task1.isSeqCorrect(sequences.get(0), sequences.get(0).size() - 2, 190));
		assertTrue(task1.isSeqCorrect(sequences.get(1), sequences.get(1).size() - 2, 3267));
		assertFalse(task1.isSeqCorrect(sequences.get(2), sequences.get(2).size() - 2, 83));
		assertFalse(task1.isSeqCorrect(sequences.get(5), sequences.get(5).size() - 2, 161011));
		assertTrue(task1.isSeqCorrect(sequences.get(8), sequences.get(8).size() - 2, 292));
		
		assertEquals(3749, Task1.calculateTotalScore(input));
	}

	@Test
	public void testTask2() {
		Task2 task2 = new Task2();
		List<List<Long>> sequences = task2.getSequences(input);
		assertFalse(sequences.isEmpty());
		assertEquals(9, sequences.size());
		assertEquals(Long.valueOf(190), sequences.get(0).get(0));
		assertTrue(task2.isSeqCorrect(sequences.get(4), 1, 6));
		assertEquals(11387, Task2.calculateTotalScore(input));
	}

}