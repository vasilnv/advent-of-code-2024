package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Task1 {
	Stack<Integer> numbers = new Stack<>();
	int totalDots = 0;
	List<Integer> finalNumbers = new ArrayList<>();
	List<Integer> oldNumbers = new ArrayList<>();
	
	public String generateRealDisk(String input) {
		boolean freeBytesFlag = false;
		StringBuilder result = new StringBuilder(new String());
		int index = 0;
		for (char c : input.toCharArray()) {
			if (freeBytesFlag) {
				result.append(String.join("", Collections.nCopies(Character.getNumericValue(c), ".")));
				for (int i = 0; i < Character.getNumericValue(c); i++) {
					oldNumbers.add(-1);
				}
				totalDots += Character.getNumericValue(c);
			} else {
				result.append(String.join("", Collections.nCopies(Character.getNumericValue(c), String.valueOf(index))));
				for (int i = 0; i < Character.getNumericValue(c); i++) {
					numbers.add(index);
					oldNumbers.add(index);
				}
				index++;
			}
			freeBytesFlag = !freeBytesFlag;
		}
		return result.toString();
	}

	public String compactDisk(String converted) {
		int start = 0;
		char[] allElements = converted.toCharArray();
		String result = "";
		
		for (int i = 0; i < oldNumbers.size() - totalDots; i++) {
			if (oldNumbers.get(i) == -1) {
				finalNumbers.add(numbers.pop());
			} else {
				finalNumbers.add(oldNumbers.get(i));
			}
		}
		
//		while (result.length() < converted.length() - totalDots) {
//			if (allElements[start] == '.') {
//				int currNumber = numbers.pop();
//				result += Integer.toString(currNumber);
//				finalNumbers.add(currNumber);
//			} else {
//				result += allElements[start];
//				
//			}
//			start++;
//		}
//		while (result.length() < converted.length()) {
//			result += '.';
//			start++;
//		}
		return result;
	}

	public long calculateChecksum(String input) {
		long checksum = 0;
		for (int i = 0; i < finalNumbers.size(); i++) {
			if (finalNumbers.get(i) != -1) {
				checksum += finalNumbers.get(i) * i;
			}
		}
		return checksum;
	}
	
	public static long getResult(String input) {
		Task1 task1 = new Task1();
		String step1 = task1.generateRealDisk(input);
		String step2 = task1.compactDisk(step1);
		return task1.calculateChecksum(step2);
		
	}
	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day9.txt"));
			System.out.println("Result is: " + getResult(input)); //6332189866718
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

}
