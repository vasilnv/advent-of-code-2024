package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Task1 {
	Map<Character, Set<Pair>> uniqueSymbolsCoordinates = new HashMap<>();
	
	public char[][] convertInput(String input) {
		String[] lines = input.split("\n");
		char[][] result = new char[lines.length][lines[0].length()];
		for (int i = 0; i < lines.length; i++) {
			result[i] = lines[i].toCharArray();
		}
		return result;
	}
	
	public Map<Character, Set<Pair>> findAllUniqueSymbols(char[][] input) {
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] != '.') {
					uniqueSymbolsCoordinates.putIfAbsent(input[i][j], new HashSet<>());
					uniqueSymbolsCoordinates.get(input[i][j]).add(new Pair(i, j));
				}
			}
		}
		return uniqueSymbolsCoordinates;
	}
	
	public char[][] updateCoordinateMap(char[][] input) {
		for (var entry : uniqueSymbolsCoordinates.entrySet()) {
			for (var pair1 : entry.getValue()) {
				for (var pair2 : entry.getValue()) {
					if (!pair1.equals(pair2)) {
						int diffX = pair1.x - pair2.x;
						int diffY = pair1.y - pair2.y;

						int newCoordinateX1 = pair1.x + diffX;
						int newCoordinateY1 = pair1.y + diffY;
						int newCoordinateX2 = pair2.x - diffX;
						int newCoordinateY2 = pair2.y - diffY;
						
						if (newCoordinateX1 < input.length && newCoordinateX1 >= 0 && newCoordinateY1 >= 0 && newCoordinateY1 < input[0].length) {
							input[newCoordinateX1][newCoordinateY1] = '#';
						}
						
						if (newCoordinateX2 >= 0 && newCoordinateX2 < input.length && newCoordinateY2 < input[0].length && newCoordinateY2 >= 0) {
							input[newCoordinateX2][newCoordinateY2] = '#';
						}
					}
				}
			}
		}
		return input;
	}
	
	public int calculateNewChars(char[][] input) {
		int result = 0;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				if (input[i][j] == '#') {
					result++;
				}
			}
		}
		return result;
	}

	public void printBoard(char[][] result) {
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j]);
			}
			System.out.println();
		}
	}

	class Pair {
		int x;
		int y;
		
		Pair(int x, int y) {
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

	public static void main(String[] args) {
		try {
			String input = Files.readString(Path.of("src/main/resources/day8.txt"));
			System.out.println("RESULT IS: " + Task1.calculateRes(input)); // 265
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static int calculateRes(String input) {
		Task1 task1 = new Task1();
		char[][] inputBoard = task1.convertInput(input);
		task1.findAllUniqueSymbols(inputBoard);
		task1.updateCoordinateMap(inputBoard);
		return task1.calculateNewChars(inputBoard);
	}
}
