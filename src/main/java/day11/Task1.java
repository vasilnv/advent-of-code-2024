package day11;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task1 {
	Cache<Long, List<Long>> cache = Caffeine.newBuilder()
			.maximumSize(1000)
			.build();
	
	public List<Long> convertInput(String input) {
		List<Long> result = new ArrayList<>();
		String[] nums = input.split(" ");
		for (String num : nums) {
			result.add(Long.parseLong(num));
		}
		return result;
	}

	public List<Long> getArrangementAfter(List<Long> initialArrangement, int blinksNumber) {
		List<Long> nextArrangement = new ArrayList<>();
		for (int i = 0; i < blinksNumber; i++) {
//			System.out.println("BLINK NUMBER " + i);
			nextArrangement = new ArrayList<>();
			for (int j = 0; j < initialArrangement.size(); j++) {
				if (initialArrangement.get(j) == 0) {
					nextArrangement.add(initialArrangement.get(j) + 1);
				} else if (cache.getIfPresent(initialArrangement.get(j)) != null) {
//					System.out.println("CACHE HIT");
					nextArrangement.addAll(Objects.requireNonNull(cache.getIfPresent(initialArrangement.get(j))));
				} else {
					if (String.valueOf(initialArrangement.get(j)).length() % 2 == 0) {
						// 4 / 2 = 2 - 1 0,1 and 1,2
						int firstHalfIndex = String.valueOf(initialArrangement.get(j)).length() / 2;
						var firstHalf = Long.parseLong(String.valueOf(initialArrangement.get(j)).substring(0, firstHalfIndex));
						var secondHalf = Long.parseLong(String.valueOf(initialArrangement.get(j)).substring(firstHalfIndex));
						nextArrangement.add(firstHalf);
						nextArrangement.add(secondHalf);
						cache.put(initialArrangement.get(j), List.of(firstHalf, secondHalf));
					} else {
						nextArrangement.add(initialArrangement.get(j) * 2024);
						cache.put(initialArrangement.get(j), List.of(initialArrangement.get(j) * 2024));
					}
				}
			}
			initialArrangement = new ArrayList<>(nextArrangement);
			System.gc();
		}
		return nextArrangement;
	}
	
	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day11.txt"));
			Task1 task1 = new Task1();
			var inputNums = task1.convertInput(input);
			var res = task1.getArrangementAfter(inputNums, 25);
			System.out.println("Result is: " + res.size());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
