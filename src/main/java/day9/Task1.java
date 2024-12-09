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
	
	public void generateRealDisk(String input) {
		boolean freeBytesFlag = false;
		int index = 0;
		for (char c : input.toCharArray()) {
			if (freeBytesFlag) {
				for (int i = 0; i < Character.getNumericValue(c); i++) {
					oldNumbers.add(-1);
				}
				totalDots += Character.getNumericValue(c);
			} else {
				for (int i = 0; i < Character.getNumericValue(c); i++) {
					numbers.add(index);
					oldNumbers.add(index);
				}
				index++;
			}
			freeBytesFlag = !freeBytesFlag;
		}
	}

	public void compactDisk() {
		for (int i = 0; i < oldNumbers.size() - totalDots; i++) {
			if (oldNumbers.get(i) == -1) {
				finalNumbers.add(numbers.pop());
			} else {
				finalNumbers.add(oldNumbers.get(i));
			}
		}
	}

	public long calculateChecksum() {
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
		task1.generateRealDisk(input);
		task1.compactDisk();
		return task1.calculateChecksum();
		
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
