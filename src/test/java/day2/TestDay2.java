package day2;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestDay2 {
	@Test
	public void testTask1() {
		assertTrue(new Task1().checkOrder(List.of(7,6,4,2,1)));
		assertFalse(new Task1().checkOrder(List.of(1,3,2,4,5)));
		assertFalse(new Task1().checkOrder(List.of(9,7,6,2,1)));
	}

	@Test
	public void testTask2() {
		assertTrue(Task2.testCheck(List.of(7,6,4,2,1)));
		assertTrue(Task2.testCheck(List.of(1,3,2,4,5)));
		assertFalse(Task2.testCheck(List.of(9,7,6,2,1)));
		assertTrue(Task2.testCheck(List.of(8,6,4,4,1)));
		assertTrue(Task2.testCheck(List.of(1,3,6,7,9)));
		assertFalse(Task2.testCheck(List.of(1,2,7,8,9)));
		assertTrue(Task2.testCheck(List.of(1,2,3,5,15)));
		assertTrue(Task2.testCheck(List.of(1,2,3,1,5)));
	}

}