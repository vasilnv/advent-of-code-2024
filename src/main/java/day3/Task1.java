package day3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
	public int findResult(String input) {
		Pattern pattern = Pattern.compile("mul\\((\\d+)(\\d*)(\\d*),(\\d+)(\\d*)(\\d*)\\)");
		Matcher matcher = pattern.matcher(input);
		int totalRes = 0;
		while (matcher.find()) {
			totalRes += calculate(matcher.group());
		}
		return totalRes;
	}

	private int calculate(String expression) {
		Pattern pattern = Pattern.compile("\\d+\\d*\\d*");
		Matcher matcher = pattern.matcher(expression);
		if (matcher.find()) {
			Integer first = Integer.parseInt(matcher.group());
			if (matcher.find()) {
				Integer second = Integer.parseInt(matcher.group());
				return first * second;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/main/resources/day3.txt")))) {
			int totalScore = 0;
			StringBuilder input = new StringBuilder();
			Task1 task1 = new Task1();
			String line;
			while ((line = reader.readLine()) != null) {
				input.append(line);
			}
			totalScore += task1.findResult(input.toString());			
			System.out.println("RESULT IS " + totalScore);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
