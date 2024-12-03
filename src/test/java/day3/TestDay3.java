package day3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDay3 {
	@Test
	public void testTask1() {
		String input = "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))";
		Task1 task1 = new Task1();
		assertEquals(161, task1.findResult(input));
	}

	@Test
	public void testTask2() {
		String input = "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))";
		assertEquals(48, Task2.findResultEnablement(input));
	}
	

}