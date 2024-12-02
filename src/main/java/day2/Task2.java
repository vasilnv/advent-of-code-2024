package day2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 {
	public static boolean checkOrder(List<Integer> vals) {
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

	public static boolean isSafe(List<Integer> levels) {
		for (int i = 0; i < levels.size(); i++) {
			List<Integer> modifiedLevels = new ArrayList<>(levels);
			modifiedLevels.remove(i);
			if (checkOrder(modifiedLevels)) {
				return true;
			}
		}
		return false;
	}

	public static boolean testCheck(List<Integer> levels) {
		return isSafe(levels);
	}

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/day2.txt")));
			String line;
			int totalCount = 0;
			while ((line = reader.readLine()) != null) {
				List<Integer> vals = Arrays.stream(line.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
				boolean result = isSafe(vals);
				if (result) {
					totalCount++;
				}
			}
			// 488 is the correct answer
			System.out.println("RESULT IS " + totalCount);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
