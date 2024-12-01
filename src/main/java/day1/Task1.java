package day1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
	public int calculateTotalDistance(List<Integer> arr1, List<Integer> arr2) {
		arr1.sort(Integer::compareTo);
		arr2.sort(Integer::compareTo);

		int totalSum = 0;
		for (int i = 0; i < arr1.size(); i++) {
			totalSum += Math.abs(arr1.get(i) - arr2.get(i));
		}
		return totalSum;
	}
	
	public static void main(String[] args) {
		File file = new File("src/main/resources/day1.txt");
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while ((line = reader.readLine()) != null) {
				String[] vals = line.split("\\s+");
				Integer val1 = Integer.parseInt(vals[0]);
				Integer val2 = Integer.parseInt(vals[1]);
				arr1.add(val1);
				arr2.add(val2);
			}
			Task1 task1 = new Task1();
			System.out.println("RESULT IS:" + task1.calculateTotalDistance(arr1, arr2));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
