package day9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Task2 extends Task1 {
    Map<Integer, Integer> groups = new HashMap<>();

    public void generateRealDisk(String input) {
        boolean freeBytesFlag = false;
        StringBuilder result = new StringBuilder(new String());
        int index = 0;
        for (char c : input.toCharArray()) {
            if (freeBytesFlag) {
                result.append(String.join("", Collections.nCopies(Character.getNumericValue(c), ".")));
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    oldNumbers.add(-1);
                }
                totalDots += Character.getNumericValue(c);
            } else {
                result.append(String.join("", Collections.nCopies(Character.getNumericValue(c), String.valueOf(index))));
                for (int i = 0; i < Character.getNumericValue(c); i++) {
                    numbers.add(index);
                    oldNumbers.add(index);
                    groups.put(index, groups.getOrDefault(index, 0) + 1);
                }
                index++;
            }
            freeBytesFlag = !freeBytesFlag;
        }
    }

    @Override
    public void compactDisk() {
        int maxIndex;
        List<Integer> numbersInGroups = groups.keySet().stream().collect(Collectors.toList()).stream().sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        for (int candidateToMove : numbersInGroups) {
            int groupSize = groups.get(candidateToMove);
            maxIndex = oldNumbers.indexOf(candidateToMove);
            int indexToStartAdding = findPlaceToAdd(groupSize, maxIndex);
            if (indexToStartAdding != -1) {
                Collections.replaceAll(oldNumbers, candidateToMove, -1);
                for (int j = 0; j < groupSize; j++) {
                    oldNumbers.set(indexToStartAdding, candidateToMove);
                    indexToStartAdding++;
                }
            }
        }
        finalNumbers = new ArrayList<>(oldNumbers);
    }

    private int findPlaceToAdd(int groupSize, int maxIndex) {
        for (int i = 0; i < maxIndex; i++) {
            if (oldNumbers.get(i) == -1) {
                int start = i;
                int begin = i;
                int currSize = 0;
                while (oldNumbers.get(start) == -1 && start <= maxIndex) {
                    currSize++;
                    start++;
                }
                if (currSize >= groupSize) {
                    return begin;
                }
                i = start;
            }
        }
        return -1;
    }

    public static long getResult(String input) {
        Task1 task2 = new Task2();
        task2.generateRealDisk(input);
        task2.compactDisk();
        return task2.calculateChecksum();

    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day9.txt"));
            System.out.println("Result is: " + getResult(input)); //6353648390778
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
