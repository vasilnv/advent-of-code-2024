package day4;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Task1 {
	int totalOccurences = 0;
	char[] nextWords = new char[] {'M', 'A', 'S'};
	int[][] movements = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1,1}};

	public int findOccurrences(char[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				int nextWordIndex = 0;
				if (s[i][j] == 'X') {
					Queue<Candidate> candidates = checkNextWord(s, nextWordIndex, i, j);
					nextWordIndex++;
					while (nextWordIndex < nextWords.length) {
						Queue<Candidate> nextCandidates = new LinkedList<>();
						while (!candidates.isEmpty()) {
							Candidate candidate = candidates.poll();
							nextCandidates.addAll(checkNextMove(s, nextWordIndex, candidate.i, candidate.j, candidate.movement));
						}
						candidates.addAll(nextCandidates);
						if (candidates.isEmpty()) {
							break;
						}
						nextWordIndex++;
					}
				}
			}
		}
		return totalOccurences;
	}

	private Queue<Candidate> checkNextWord(char[][] s, int nextWordIndex, int currI, int currJ) {
		Queue<Candidate> candidates = new LinkedList<>();
		for (int[] movement : movements) {
			if (currI + movement[0] >= 0 && currI + movement[0] < s.length 
					&& currJ + movement[1] >= 0 && currJ + movement[1] < s[0].length) {
				if (s[currI + movement[0]][currJ + movement[1]] == nextWords[nextWordIndex]) {
					if (nextWords[nextWordIndex] == 'S') {
						totalOccurences++;
					}
					candidates.offer(new Candidate(currI + movement[0], currJ + movement[1], movement));
				}
			}
		}
		return candidates;
	}

	private Queue<Candidate> checkNextMove(char[][] s, int nextWordIndex, int currI, int currJ, int[] movement) {
		Queue<Candidate> candidates = new LinkedList<>();
		if (currI + movement[0] >= 0 && currI + movement[0] < s.length
				&& currJ + movement[1] >= 0 && currJ + movement[1] < s[0].length) {
			if (s[currI + movement[0]][currJ + movement[1]] == nextWords[nextWordIndex]) {
				if (nextWords[nextWordIndex] == 'S') {
					totalOccurences++;
				}
				candidates.offer(new Candidate(currI + movement[0], currJ + movement[1], movement));
			}
		}
		return candidates;
	}
	public int findOccurrencesFromString(String input) {
		String[] rows = input.split("\n");

		// Create a 2D char array with the same number of rows and columns
		int numRows = rows.length;
		int numCols = rows[0].length(); // Assume all rows have the same length
		char[][] s = new char[numRows][numCols];

		// Fill the 2D char array
		for (int i = 0; i < numRows; i++) {
			s[i] = rows[i].toCharArray();
		}
		
		return findOccurrences(s);
	}

	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/day4.txt"));
			StringBuilder input = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				input.append(line).append("\n");
			}
			Task1 task1 = new Task1();
			System.out.println("RESULT IS: " + task1.findOccurrencesFromString(input.toString()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	class Candidate {
		public int i;
		public int j;
		public int[] movement;
		
		public Candidate(int i, int j, int []movement) {
			this.i = i;
			this.j = j;
			this.movement = movement;
		}
	}
}
