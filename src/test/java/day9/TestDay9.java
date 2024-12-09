package day9;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay9 {
	
	@Test
	public void testTask1() {
		String input = "233313312141413140101";
		Task1 task1 = new Task1();
		String converted = task1.generateRealDisk(input);
		assertEquals("00...111...2...333.44.5555.6666.777.8888910", converted);
		String compacted = task1.compactDisk(converted);
//		assertEquals("00109811188827773336446555566..............", compacted);
		assertEquals(1930, task1.calculateChecksum(compacted));
	}

	@Test
	public void testTask2() {
		String input = "2333133121414131402";
		Task1 task2 = new Task2();
		String converted = task2.generateRealDisk(input);
		assertEquals("00...111...2...333.44.5555.6666.777.888899", converted);
		String compacted = task2.compactDisk(converted);
//		assertEquals("00109811188827773336446555566..............", compacted);
		assertEquals(2858, task2.calculateChecksum(compacted));
	}

}