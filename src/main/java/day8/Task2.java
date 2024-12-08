package day8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task2 extends Task1 {
	@Override
	public char[][] updateCoordinateMap(char[][] input) {
		for (var entry : uniqueSymbolsCoordinates.entrySet()) {
			for (var pair1 : entry.getValue()) {
				for (var pair2 : entry.getValue()) {
					if (!pair1.equals(pair2)) {
						int diffX = pair1.x - pair2.x;
						int diffY = pair1.y - pair2.y;

						int newCoordinateX1 = pair1.x;
						int newCoordinateY1 = pair1.y;

						while (newCoordinateX1 < input.length && newCoordinateX1 >= 0 && newCoordinateY1 >= 0 && newCoordinateY1 < input[0].length) {
							input[newCoordinateX1][newCoordinateY1] = '#';
							newCoordinateX1 += diffX;
							newCoordinateY1 += diffY;
						}
						
						int newCoordinateX2 = pair2.x;
						int newCoordinateY2 = pair2.y;

						while (newCoordinateX2 >= 0 && newCoordinateX2 < input.length && newCoordinateY2 < input[0].length && newCoordinateY2 >= 0) {
							input[newCoordinateX2][newCoordinateY2] = '#';
							newCoordinateX2 -= diffX;
							newCoordinateY2 -= diffY;
						}
					}
				}
			}
		}
		return input;
	}

	public static int calculateRes(String input) {
		Task2 task2 = new Task2();
		char[][] inputBoard = task2.convertInput(input);
		task2.findAllUniqueSymbols(inputBoard);
		task2.updateCoordinateMap(inputBoard);
		task2.printBoard(inputBoard);
		return task2.calculateNewChars(inputBoard);
	}

	public static void main(String[] args) {
		try {
			String inputAll = Files.readString(Path.of("src/main/resources/day8.txt"));
			System.out.println("RESULT IS: " + calculateRes(inputAll)); //962
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
