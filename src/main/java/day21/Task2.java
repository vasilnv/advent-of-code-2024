package day21;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task2 {
    public static int TOTAL_ROBOTS = 25;
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

    Map<ComboPair, Long> cache = new HashMap<>();

    public long getCombinationForRobot(String combo, int robotsLeft, Coordinate robotDirection) {
        if (robotsLeft == 0) {
            return combo.length();
        }

        String res = transform(combo);
        long total = 0;
        Coordinate prevCoordinate = robotDirectionsMap.get('A');
        int i = 0;
        for (var currRes : res.split("A")) {
            if (currRes.isEmpty()) {
                total += 1;
                i++;
                continue;
            }
            Combination currCombination = extractCombo(currRes);
            if (cache.containsKey(new ComboPair(currCombination, robotsLeft - 1, prevCoordinate))) {
                System.out.println("Cache hit for " + currRes + " prev " + prevCoordinate + " left robots:" + (robotsLeft - 1));
                total += cache.get(new ComboPair(currCombination, robotsLeft - 1, prevCoordinate));
                prevCoordinate = robotDirectionsMap.get(combo.charAt(i));
                i++;
                continue;
            }

            List<String> allCombos = generateCombosFor(currRes);
            long min = Long.MAX_VALUE;
            for (var comb : allCombos) {
                String nextComb = comb + "A";
                if (apply(comb, prevCoordinate, false)) {
                    long combRes = getCombinationForRobot(nextComb, robotsLeft - 1, robotDirectionsMap.get('A'));
                    if (combRes < min) {
                        min = combRes;
                    }
                }
            }
            total += min;
            cache.put(new ComboPair(currCombination, robotsLeft - 1, prevCoordinate), min);
            prevCoordinate = robotDirectionsMap.get(combo.charAt(i));
            i++;
        }
        return total;
    }

    private boolean apply(String comb, Coordinate prevCoordinate, boolean isInitial) {
        Coordinate bomb = new Coordinate(3, 0);
        if (!isInitial) {
            bomb = new Coordinate(0, 0);
        }
        Coordinate nextCoordinate = new Coordinate(prevCoordinate.x, prevCoordinate.y);
        for (var move : comb.toCharArray()) {
            nextCoordinate = moveCoordinate(nextCoordinate, move);

            if (nextCoordinate.equals(bomb)) {
                return false;
            }
        }
        return true;
    }

    private Coordinate moveCoordinate(Coordinate prevCoordinate, char move) {
        int nextX = prevCoordinate.x;
        int nextY = prevCoordinate.y;

        if (move == '^') nextX -= 1;
        else if (move == 'v') nextX += 1;
        else if (move == '<') nextY -= 1;
        else if (move == '>') nextY += 1;

        return new Coordinate(nextX, nextY);
    }

    private List<String> generateCombosFor(String currRes) {
        Map<Character, Integer> occurences = new HashMap<>();
        for (var c: currRes.toCharArray()) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }
        char[] c = new char[2];
        int i = 0;
        for (var entry : occurences.keySet()) {
            c[i] = entry;
            i++;
        }
        return generateCombinations(currRes.length(), c[0], occurences.getOrDefault(c[0], 0), c[1], occurences.getOrDefault(c[1], 0));
    }

    private Combination extractCombo(String currRes) {
        Map<Character, Integer> occurences = new HashMap<>();
        for (var c: currRes.toCharArray()) {
            occurences.put(c, occurences.getOrDefault(c, 0) + 1);
        }
        char cx, cy;
        var it = occurences.keySet().iterator();
        cx = it.next();
        if (it.hasNext()) {
            cy = it.next();
        } else {
            return new Combination(cx, 'v', occurences.get(cx), 0);
        }
        return new Combination(cx, cy, occurences.get(cx), occurences.get(cy));
    }

    public List<String> generateCombinations(int n, char value1, int occurrences1, char value2, int occurrences2) {
        List<String> results = new ArrayList<>();

        char[] combination = new char[n];

        generateCombinationsRecursive(results, combination, 0, value1, occurrences1, value2, occurrences2);
        return results;
    }

    private void generateCombinationsRecursive(List<String> results, char[] combination, int index, char value1, int remaining1, char value2, int remaining2) {
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


    private String transform(String input) {
        StringBuilder result = new StringBuilder();
        Coordinate begin = robotDirectionsMap.get('A');
        for (var c : input.toCharArray()) {
            StringBuilder currRes = new StringBuilder();
            var target = robotDirectionsMap.get(c);
            var upDown = begin.x - target.x;
            var leftRight = target.y - begin.y;
            if (upDown > 0) {
                currRes.append("^".repeat(upDown));
            }
            if (leftRight > 0) {
                currRes.append(">".repeat(leftRight));
            }
            if (leftRight < 0) {
                currRes.append("<".repeat(Math.abs(leftRight)));
            }
            if (upDown < 0) {
                currRes.append("v".repeat(Math.abs(upDown)));
            }
            String bestCombo = currRes.toString();
            result.append(bestCombo);
            result.append("A");
            begin = robotDirectionsMap.get(c);
        }
        return result.toString();

    }

    public String getInitRobotMovements(String input) {
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
            result.append(current);
            result.append("A");
            begin = directionsMap.get(c);
        }
        return result.toString();
    }

    static long solve(String input, int total_robots) {
        TOTAL_ROBOTS = total_robots;
        String[] lines = input.split("\n");
        Task2 task2 = new Task2();
        long total = 0;
        for (var line : lines) {
            String robotsInput = task2.getInitRobotMovements(line);
            long combinationRes = 0;
            Coordinate prevCoordinate = directionsMap.get('A');
            int i = 0;
            for (var part : robotsInput.split("A")) {
                if (part.isEmpty()) {
                    combinationRes++;
                    i++;
                    continue;
                }
                List<String> allCombos = task2.generateCombosFor(part);
                long min = Long.MAX_VALUE;
                for (var comb : allCombos) {
                    long combRes = task2.getCombinationForRobot(comb + "A", TOTAL_ROBOTS, robotDirectionsMap.get('A'));
                    if (task2.apply(comb, prevCoordinate, true) && combRes < min ) {
                        min = combRes;
                    }
                }
                combinationRes += min;
                prevCoordinate = directionsMap.get(line.charAt(i));
                i++;
            }
            int numeric = task2.extractNumericCode(line);
            total += combinationRes * numeric;
        }
        return total;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return x + "," + y;
        }
    }

    static class Pair {
        String combination;
        int robotsLeft;

        Pair(String combo, int robotsLeft) {
            this.combination = combo;
            this.robotsLeft = robotsLeft;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair other = (Pair) o;
            return Objects.equals(combination, other.combination) &&
                    Objects.equals(robotsLeft, other.robotsLeft);
        }

        @Override
        public int hashCode() {
            return Objects.hash(combination, robotsLeft);
        }
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day21.txt"));
            long res = solve(input, 25);
            System.out.println("Result is: " + res);
            if (res != 341460772681012L) {
                System.err.println("THERE IS AN ERROR!!!!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
