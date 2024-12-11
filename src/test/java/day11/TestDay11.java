package day11;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestDay11 {
	String input;
	
	@Before
	public void setUp() throws Exception {
		input = "125 17";
	}
	
	@Test
	public void testTask1() {
		Task1 task1 = new Task1();
		List<Long> nums = task1.convertInput(input);
		List<Long> newRes = task1.getArrangementAfter(nums, 6);
		Assert.assertEquals(22, newRes.size());	
	}
	
	@Test
	public void testTask2() {
		Task2 task2 = new Task2();
		List<Long> nums = task2.convertInput(input);
		long res = 0;
		for (Long num : nums) {
			res += task2.getArrangementAfter(num, 6);
		}
		Assert.assertEquals(22, res);
	}

}