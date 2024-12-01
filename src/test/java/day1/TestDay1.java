package day1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TestDay1 {

	@Test
	public void calculateTotalDistance() {
		Task1 task1 = new Task1();
		List<Integer> arr1 = new ArrayList<>(List.of(3,4,2,1,3,3));
		List<Integer> arr2 = new ArrayList<>(List.of(4,3,5,3,9,3));
		assertEquals(11, task1.calculateTotalDistance(arr1, arr2));
	}
	
	@Test
	public void calculateSimScore() {
		Task2 task = new Task2();
		List<Integer> arr1 = new ArrayList<>(List.of(3,4,2,1,3,3));
		List<Integer> arr2 = new ArrayList<>(List.of(4,3,5,3,9,3));
		assertEquals(31, task.calculateSimScoreFromList(arr1, arr2));
	}
}