package day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    static Map<Character, Coordinate> directionsMap = Map.ofEntries(
               Map.entry('A', new Coordinate(3, 2)),
            Map.entry('0', new Coordinate(3,1)),
            Map.entry('1', new Coordinate(2, 0)),
            Map.entry('2', new Coordinate(2,1)),
            Map.entry('3', new Coordinate(2,2)),
            Map.entry( '4', new Coordinate(1, 0)),
            Map.entry( '5', new Coordinate(1, 1)),
            Map.entry('6', new Coordinate(1, 2)),
            Map.entry('7', new Coordinate(0,0)),
            Map.entry( '8', new Coordinate(0, 1)),
            Map.entry( '9', new Coordinate(0,2))
    );

    static Map<Character, Coordinate> robotDirectionsMap = Map.ofEntries(
            Map.entry('^', new Coordinate(0, 1)),
            Map.entry('A', new Coordinate(0, 2)),
            Map.entry('<', new Coordinate(1, 0)),
            Map.entry('v', new Coordinate(1, 1)),
            Map.entry('>', new Coordinate(1, 2))
    );

    Map<String, String> bestCombos = new HashMap<>();

    public static long solve(String input) {
        Task1 task1 = new Task1();
        String[] lines = input.split("\n");
        long result = 0;
        for (var line : lines) {
            int commandsLength = task1.getDirectionsMovements(task1.getDirectionsMovements(task1.getInitRobotMovements(line, true), true, robotDirectionsMap.get('A')), true, robotDirectionsMap.get('A')).length();
            int numericCode = task1.extractNumericCode(line);
            result += (long) numericCode * commandsLength;
            System.out.println("FOR LINE: " + line + " NUMERIC CODE IS " + numericCode + " COMMANDS LENGTH: " + commandsLength + " AND RESULT IS: " + numericCode * commandsLength);
        }
        return result;
    }

    public String checkCombos(String subset, Coordinate initialPos, boolean isInitialCheck) {
//       if (bestCombos.containsKey(subset)) {
//           System.out.println("CACHE HIT FOR SUBSET: " + subset + " WITH COMBO: " + bestCombos.get(subset));
//           return bestCombos.get(subset);
//       }
        Map<Character, Integer> occurences = new HashMap<>();
        for (var c: subset.toCharArray()) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }
        char[] c = new char[2];
        int i = 0;
        for (var entry : occurences.keySet()) {
            c[i] = entry;
            i++;
        }
        List<String> combinations;
         combinations = generateCombinations(subset.length(), c[0], occurences.getOrDefault(c[0], 0), c[1], occurences.getOrDefault(c[1], 0));

        long minLength = Long.MAX_VALUE;
        String bestCombo = null;
        for (var combo : combinations) {
            if (isComboValid(combo, initialPos, isInitialCheck)) {
                String commands = getDirectionsMovements(getDirectionsMovements(combo, false, initialPos), false, robotDirectionsMap.get('A'));
                long commandsLength = commands.length();
                if (commandsLength < minLength) {
                    bestCombo = combo;
                    minLength = commandsLength;
                }
            }
        }
        bestCombos.put(subset, bestCombo);
        return bestCombo;
    }

    private boolean isComboValid(String combo, Coordinate initialPos, boolean isInitial) {
        int startX = initialPos.x;
        int startY = initialPos.y;

        for (var c : combo.toCharArray()) {
            if (c == '<') startY--;
            if (c == '>') startY++;
            if (c == '^') startX--;
            if (c == 'v') startX++;
            if (isInitial && startX == 3 && startY == 0) return false;
            if (!isInitial && startX == 0 && startY == 0) return false;
        }
        return true;
    }

    public static List<String> generateCombinations(int n, char value1, int occurrences1, char value2, int occurrences2) {
        List<String> results = new ArrayList<>();

        char[] combination = new char[n];

        generateCombinationsRecursive(results, combination, 0, value1, occurrences1, value2, occurrences2);
        return results;
    }

    private static void generateCombinationsRecursive(List<String> results, char[] combination, int index, char value1, int remaining1, char value2, int remaining2) {
        if (index == combination.length) {
            results.add(new String(combination));
            return;
        }

        if (remaining1 > 0) {
            combination[index] = value1;
            generateCombinationsRecursive(results, combination, index + 1, value1, remaining1 - 1, value2, remaining2);
        }

        if (remaining2 > 0) {
            combination[index] = value2;
            generateCombinationsRecursive(results, combination, index + 1, value1, remaining1, value2, remaining2 - 1);
        }
    }

    public String getInitRobotMovements(String input, boolean isChecking) {
        System.out.println();
        System.out.println();
        System.out.println("GETTING INITAL ROBOT MOVEMENTS");
        var begin = directionsMap.get('A');
        StringBuilder result = new StringBuilder();
        for (var c : input.toCharArray()) {
            StringBuilder current = new StringBuilder();
            var target = directionsMap.get(c);
            var upDown = begin.x - target.x;
            var leftRight = target.y - begin.y;

            if (leftRight < 0) {
                current.append("<".repeat(Math.abs(leftRight)));
            }
            if (upDown < 0) {
                current.append("v".repeat(Math.abs(upDown)));
            }
            if (upDown > 0) {
                current.append("^".repeat(upDown));
            }
            if (leftRight > 0) {
                current.append(">".repeat(leftRight));
            }
            String bestCombo;
            bestCombo = checkCombos(current.toString(), new Coordinate(begin.x, begin.y), true);
            result.append(bestCombo);
            result.append("A");
            begin = directionsMap.get(c);
        }
        if (isChecking) {
            System.out.println("RESULT FOR " + input + " IS " + result);
        }
        return result.toString();
    }

    public String getDirectionsMovements(String input, boolean isChecking, Coordinate begin) {
        if (isChecking) {
            System.out.println();
            System.out.println("GETTING NEXT ROBOT MOVEMENTS");
        }
        StringBuilder result = new StringBuilder();
        for (var c : input.toCharArray()) {
            StringBuilder currRes = new StringBuilder();
            var target = robotDirectionsMap.get(c);
            var upDown = begin.x - target.x;
            var leftRight = target.y - begin.y;
            if (leftRight > 0) {
                currRes.append(">".repeat(leftRight));
            }
            if (upDown < 0) {
                currRes.append("v".repeat(Math.abs(upDown)));
            }
            if (leftRight < 0) {
                currRes.append("<".repeat(Math.abs(leftRight)));
            }
            if (upDown > 0) {
                currRes.append("^".repeat(upDown));
            }
            String bestCombo = currRes.toString();
            if (isChecking) {
                bestCombo = checkCombos(currRes.toString(), new Coordinate(begin.x, begin.y), false);
            }
            result.append(bestCombo);
            result.append("A");
            begin = robotDirectionsMap.get(c);
        }
        if (isChecking) {
            System.out.println("RESULT FOR " + input + " IS: " + result.toString());
        }
        return result.toString();
    }

    public int extractNumericCode(String code) {
        // Regular expression to capture numbers without leading zeros
        Pattern pattern = Pattern.compile("0*(\\d+)A");
        Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // Group 1 contains the extracted number
        }
        return -1; // Return an empty string if no match found
    }

    static class Coordinate {
        int x;
        int y;
        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day21.txt"));
            System.out.println("Result is: " + Task1.solve(input));
            long res = Task1.solve(input);
            if (res != 278568) {
                System.err.println("THERE IS AN ERROR!!!!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
