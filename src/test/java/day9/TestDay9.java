package day9;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay9 {
	
	@Test
	public void testTask1() {
		String input = "233313312141413140101";
		Task1 task1 = new Task1();
		task1.generateRealDisk(input);
		task1.compactDisk();
		assertEquals(1930, task1.calculateChecksum());
	}

	@Test
	public void testTask2() {
		String input = "2333133121414131402";
		Task1 task2 = new Task2();
		task2.generateRealDisk(input);
		task2.compactDisk();
		assertEquals(2858, task2.calculateChecksum());
	}

}