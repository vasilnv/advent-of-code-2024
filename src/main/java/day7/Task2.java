package day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task2 extends Task1 {
	@Override
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
			currSequence.add(target);
			currSequence.addAll(sequences);
			allInOne.add(currSequence);
		}
		return allInOne;
	}

	@Override
	public boolean isSeqCorrect(List<Long> seq, int index, long currVal) {
		if (index == seq.size()) {
			return currVal == seq.get(0);
		}
		if (currVal > seq.get(0)) {
			return false;
		}
		boolean result1;
		boolean result2 = false;
		boolean result3 = false;
		
		if (index > 1) {
			var part1 = String.valueOf(currVal);
			var part2 = String.valueOf(seq.get(index));
			String all = part1 + part2;
			Long newRes = Long.parseLong(all);
			result3 = isSeqCorrect(seq, index + 1, newRes);
		}
		
		if (index == 1) {
			result1 = isSeqCorrect(seq, index + 1, seq.get(index));
		} else {
			result1 = isSeqCorrect(seq, index + 1, currVal + seq.get(index));
			result2 = isSeqCorrect(seq, index + 1, currVal * seq.get(index));
		}

		return result1 || result2 || result3;
	}

	public static long calculateTotalScore(String input) {
		Task2 task2 = new Task2();
		List<List<Long>> sequences = task2.getSequences(input);
		long totalScore = 0;
		for (List<Long> sequence : sequences) {
			if (task2.isSeqCorrect(sequence, 1, sequence.get(1))) {
				totalScore += sequence.get(0);
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
