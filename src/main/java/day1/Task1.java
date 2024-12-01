package day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {
	public static void main(String[] args) {
		File file = new File("src/main/resources/day1.txt");
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line = "";
			while ((line = reader.readLine()) != null) {
				String[] vals = line.split("\\s");
				Integer val1 = Integer.parseInt(vals[0]);
				Integer val2 = Integer.parseInt(vals[3]);
				arr1.add(val1);
				arr2.add(val2);
			}
			
			arr1.sort(Integer::compareTo);
			arr2.sort(Integer::compareTo);
			
			int totalSum = 0;
			for (int i = 0; i < arr1.size(); i++) {
				System.out.println("val 1 is " + arr1.get(i) + " and val 2 is " + arr2.get(i));
				totalSum += Math.abs(arr1.get(i) - arr2.get(i));
			}
			System.out.println("RESULT IS:" + totalSum);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
