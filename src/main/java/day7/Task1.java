package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
	public List<List<Long>> getSequences(String input) {
		String[] lines = input.split("\n");
		Long target = null;
		List<Long> sequences = null;
		List<List<Long>> allInOne = new ArrayList<>();
		for (int i = 0; i < lines.length; i++) {
			String[] targetAndSeq = lines[i].split(":");
			target = Long.parseLong(targetAndSeq[0]);
			String[] candidates = targetAndSeq[1].trim().split(" ");
			sequences = new ArrayList<>();
			for (String candidate : candidates) {
				sequences.add(Long.parseLong(candidate));
			}
			List<Long> currSequence = new ArrayList<>();
			if (target == null || sequences == null) {
				continue;
			}
			currSequence.addAll(sequences);
			currSequence.add(target);
			allInOne.add(currSequence);
		}
		return allInOne;
	}
	
	public boolean isSeqCorrect(List<Long> seq, int index, long currVal) {
		if (currVal == 0) {
			return true;
		}
		if (currVal < 0 || index < 0) {
			return false;
		}
		
		boolean resultDiv = false;
		boolean resultMinus = false;
		
		if (currVal % seq.get(index) == 0) {
			long div = currVal / seq.get(index);
			if (div == 1) return true;
			resultDiv = isSeqCorrect(seq, index - 1, div);
		}
		
		if (currVal - seq.get(index) >= 0) {
			resultMinus = isSeqCorrect(seq, index - 1, currVal - seq.get(index));
		}
		
		return resultDiv || resultMinus;
	}

	public static long calculateTotalScore(String input) {
		Task1 task1 = new Task1();
		List<List<Long>> sequences = task1.getSequences(input);
		long totalScore = 0;
		for (List<Long> sequence : sequences) {
			if (task1.isSeqCorrect(sequence, sequence.size() - 2, sequence.get(sequence.size() - 1))) {
				totalScore += sequence.get(sequence.size() - 1);
			}
		}
		return totalScore;
	}

	public static void main(String[] args) {
		try {
			String input = Files.readString(Paths.get("src/main/resources/day7.txt"));
			System.out.println("Result is: " + calculateTotalScore(input));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
