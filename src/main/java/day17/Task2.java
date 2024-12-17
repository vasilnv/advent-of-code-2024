package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 {
    Map<String, Long> registers = new HashMap<>();
    List<Long> operands = new ArrayList<>();

    public Task2(String input) {
        String[] lines = input.split("\n");
        for (String line : lines) {
            if (line.startsWith("Register")) {
                String[] parts = line.split(" ");
                registers.put(parts[1].split(":")[0], Long.parseLong(parts[2]));
            }
            else {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String[] program = parts[1].split(",");
                    for (String programElement : program) {
                        operands.add(Long.parseLong(programElement));
                    }
                }
            }
        }
    }

    public static long solve(String input, Function<Task2, Long> function) {
        Task2 task2 = new Task2(input);
        Task1 task1 = new Task1(input);
        long A = 0;
        long prevA = 0;
        long preprevA = 0;
        int operandsSize = task2.operands.size();
        while (true) {
            task2.registers.put("A", prevA);
            if (function.apply(task2) == task2.operands.get(operandsSize - 2)) {
                break;
            }
            prevA++;
        }
        System.out.println("Prev A is " + prevA);
        for (int j = task2.operands.size() - 3; j >= 0; j--) {
            A = prevA * 8 - function.apply(task2) * 8;
            long i = A;
            while (true) {
                task2.registers.put("A", i);
                if (function.apply(task2) == task2.operands.get(j)) {
                    task1.getRegisters().put("A", i);
                    List<Long> toMatch = new ArrayList<>();
                    for (int k = j; k < task2.operands.size(); k++) {
                        toMatch.add(task2.operands.get(k));
                    }
                    String stringToMatch = toMatch.stream().map(Object::toString).collect(Collectors.joining(","));
                    if (task1.executeOperations().equals(stringToMatch)) {
                        prevA = i;
                        System.out.println("MATCHED " + i + " FOR J = " + j + " AND RES AFTER APPLY = " + task2.operands.get(j));
                        break;
                    }
                };
                i++;
            }
        }
        return prevA;
    }

    public Map<String, Long> getRegisters() {
        return registers;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day17.txt"));
            long res = Task2.solve(input, task2 -> {
                long a = task2.getRegisters().get("A");
                long b = a % 8;
                b = b ^ 7;
                long c = (long)(a / Math.pow(2L, b));
                b = b ^ c;
                b = b ^ 7;
                return b % 8;
            });
            System.out.println("Result is:" + res);

            Task1 task1 = new Task1(input);
            task1.getRegisters().put("A", res);
            String newRes = task1.executeOperations();
            System.out.println("RESULT AFTER EXECUTION WITH " + res + " IS:\n" + newRes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
