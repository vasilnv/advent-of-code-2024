package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {
	private long calculateSimScore(List<Integer> arr1, Map<Integer, Integer> arr2) {
		long totalSum = 0;
		for (Integer val : arr1) {
			if (arr2.containsKey(val)) {
				totalSum += (long)val * arr2.get(val);
			}
		}
		return totalSum;
	}
	
	public long calculateSimScoreFromList(List<Integer> arr1, List<Integer> arr2) {
		Map<Integer, Integer> map = new HashMap<>();
		for (Integer val : arr2) {
			map.putIfAbsent(val, 0);
			map.computeIfPresent(val, (k, v) -> map.get(k) + 1);
		}
		return calculateSimScore(arr1, map);
	}
	
	public static void main(String[] args) {
		File file = new File("src/main/resources/day1.txt");
		List<Integer> arr1 = new ArrayList<>();
		Map<Integer, Integer> arr2 = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = reader.readLine()) != null) {
				String[] vals = line.split("\\s+");
				Integer val1 = Integer.parseInt(vals[0]);
				Integer val2 = Integer.parseInt(vals[1]);
				arr1.add(val1);
				arr2.putIfAbsent(val2, 0);
				arr2.computeIfPresent(val2, (k, v) -> v + 1);
			}
			
			Task2 task2 = new Task2();
			System.out.println("RESULT IS:" + task2.calculateSimScore(arr1, arr2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
