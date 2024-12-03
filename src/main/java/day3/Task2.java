package day3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
	
	public static int findResultEnablement(String input) {
		Pattern pattern = Pattern.compile("mul\\((\\d+)(\\d*)(\\d*),(\\d+)(\\d*)(\\d*)\\)|do\\(\\)|don't\\(\\)");
		Matcher matcher = pattern.matcher(input);
		int totalRes = 0;
		boolean enabled = true;
		while (matcher.find()) {
			String curr = matcher.group();
			if (curr.startsWith("do()")) {
				enabled = true;
				continue;
			} else if (curr.startsWith("don't()")) {
				enabled = false;
				continue;
			}
			if (enabled) {
				totalRes += calculate(curr);
			}
		}
		return totalRes;
	}

	private static int calculate(String expression) {
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
			String line;
			while ((line = reader.readLine()) != null) {
				input.append(line);
			}
			totalScore += findResultEnablement(input.toString());
			
			System.out.println("RESULT IS " + totalScore);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
