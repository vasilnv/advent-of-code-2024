package day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Task2 {
	public static void main(String[] args) {
		File file = new File("src/main/resources/day1.txt");
		Set<Integer> arr1 = new HashSet<>();
		Map<Integer, Integer> arr2 = new HashMap<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] vals = line.split("\\s");
				Integer val1 = Integer.parseInt(vals[0]);
				Integer val2 = Integer.parseInt(vals[3]);
				arr1.add(val1);
				arr2.putIfAbsent(val2, 0);
				arr2.computeIfPresent(val2, (k, v) -> v + 1);
			}

			long totalSum = 0;
			for (Integer val : arr1) {
				if (arr2.containsKey(val)) {
					totalSum += (long)val * arr2.get(val);
				}
			}
			System.out.println("RESULT IS:" + totalSum);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
