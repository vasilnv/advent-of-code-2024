package day2;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {
	public boolean checkOrder(List<Integer> vals) {
		if (vals.size() < 2) {
			return true;
		}
		boolean isIncreasing = vals.get(0) < vals.get(1);
		for (int i = 1; i < vals.size(); i++) {
			if (isIncreasing && vals.get(i) < vals.get(i - 1)) {
				return false;
			} else if (!isIncreasing && vals.get(i) > vals.get(i - 1)) {
				return false;
			}
			
			if (Math.abs(vals.get(i) - vals.get(i - 1)) < 1 || Math.abs(vals.get(i) - vals.get(i - 1)) > 3) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Task1 task = new Task1();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/day2.txt")));
			String line;
			int totalCount = 0;
			while ((line = reader.readLine()) != null) {
				List<Integer> vals = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
				if (task.checkOrder(vals)) {
					totalCount++;
				}
			}
			System.out.println("RESULT IS " + totalCount);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
