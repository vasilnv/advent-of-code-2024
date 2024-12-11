package day11;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task2 extends Task1 {
	Cache<Pair, Long> cache = Caffeine.newBuilder()
			.maximumSize(1000)
			.build();

	public long getArrangementAfter(long num, int blinksLeft) {
		if (blinksLeft == 0) {
			return 1;
		}
		var potentialRes = cache.getIfPresent(new Pair(num, blinksLeft));
		if (potentialRes != null) {
			return potentialRes;
		}
		
		if (num == 0) {
			long res = getArrangementAfter(1, blinksLeft - 1);
			cache.put(new Pair(0, blinksLeft), res);
			return res;
		}
		
		long res = 0;
		if (String.valueOf(num).length() % 2 == 0) {
			// 4 / 2 = 2 - 1 0,1 and 1,2
			int firstHalfIndex = String.valueOf(num).length() / 2;
			var firstHalf = Long.parseLong(String.valueOf(num).substring(0, firstHalfIndex));
			var secondHalf = Long.parseLong(String.valueOf(num).substring(firstHalfIndex));
			long lRes = getArrangementAfter(firstHalf, blinksLeft - 1);
			long rRes = getArrangementAfter(secondHalf, blinksLeft - 1);
			res = lRes + rRes;
		} else {
			res = getArrangementAfter(num * 2024, blinksLeft - 1); 
		}
		cache.put(new Pair(num, blinksLeft), res);
		return res;
	}
	
	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day11.txt"));
			Task2 task2 = new Task2();
			var inputNums = task2.convertInput(input);
			long total = 0;
			for (var num : inputNums) {
				System.out.println("MOVING TO THE NEXT NUMBER: " + num);
				total += task2.getArrangementAfter(num, 75);
			}
			System.out.println("Result is: " + total);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	class Pair {
		long x;
		int y;

		Pair(long x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return this.x == ((Pair)obj).x && this.y == ((Pair)obj).y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.x, this.y);
		}
	}

}