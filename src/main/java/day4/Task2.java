package day4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Task2 {
	int totalOccurences = 0;
	char[] nextWords = new char[] {'M', 'S'};
	int[][] movements = new int[][] {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

	public int findOccurrences(char[][] s) {
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[i].length; j++) {
				if (s[i][j] == 'A') {
					Queue<Candidate> candidates = checkNextWord(s, i, j);
					Queue<Candidate> nextCandidates = new LinkedList<>();
					while (!candidates.isEmpty()) {
						Candidate candidate = candidates.poll();
						nextCandidates.addAll(checkNextMove(s, candidate.nextWord, candidate.i, candidate.j, candidate.movement));
					}
					candidates.addAll(nextCandidates);
					if (candidates.size() == 4) {
						totalOccurences++;
					}
				}
			}
		}
		return totalOccurences;
	}

	private Queue<Candidate> checkNextWord(char[][] s, int currI, int currJ) {
		Queue<Candidate> candidates = new LinkedList<>();
		for (int[] movement : movements) {
			Set<Character> nextPossible = new HashSet<>();
			nextPossible.add('M');
			nextPossible.add('S');
			if (currI + movement[0] >= 0 && currI + movement[0] < s.length
					&& currJ + movement[1] >= 0 && currJ + movement[1] < s[0].length) {
				if (nextPossible.contains(s[currI + movement[0]][currJ + movement[1]])) {
					nextPossible.remove(s[currI + movement[0]][currJ + movement[1]]);
					int[] nextMovement = new int[2];
					if (movement[0] == 1 && movement[1] == 1) {
						nextMovement = new int[] {-1, -1};
					}
					if (movement[0] == -1 && movement[1] == 1) {
						nextMovement = new int[] {1, -1};
					}
					if (movement[0] == -1 && movement[1] == -1) {
						nextMovement = new int[] {1, 1};
					}
					if (movement[0] == 1 && movement[1] == -1) {
						nextMovement = new int[] {-1, 1};
					}
					candidates.offer(new Candidate(currI, currJ, nextMovement, nextPossible.iterator().next()));
				}
			}
		}
		return candidates;
	}

	private Queue<Candidate> checkNextMove(char[][] s, char nextWordToCheck, int currI, int currJ, int[] movement) {
		Queue<Candidate> candidates = new LinkedList<>();
		if (currI + movement[0] >= 0 && currI + movement[0] < s.length
				&& currJ + movement[1] >= 0 && currJ + movement[1] < s[0].length) {
			if (s[currI + movement[0]][currJ + movement[1]] == nextWordToCheck) {
				candidates.offer(new Candidate(currI + movement[0], currJ + movement[1], movement, nextWordToCheck));
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
			Task2 task1 = new Task2();
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
		public char nextWord;
		
		public Candidate(int i, int j, int[] movement, char nextWord) {
			this.i = i;
			this.j = j;
			this.movement = movement;
			this.nextWord = nextWord;
		}
	}

}
