package day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task1 {
    Map<String, Long> registers = new HashMap<>();
    List<Long> operands = new ArrayList<>();

    public Task1(String input) {
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

    public String executeOperations() {
        List<Long> res = new ArrayList<>();
        for (long i = 0; i < operands.size(); i+=2) {
            long instruction = operands.get((int)i);
            long operand = operands.get((int)i+1);

            if (instruction == 0) {
                long numerator = registers.get("A");
                double denominator = Math.pow(2, getComboOperand(operand));
                long result = (long)(numerator / denominator);
                registers.put("A", result);
            } else if (instruction == 1) {
                long n1 = registers.get("B");
                long result = n1 ^ operand;
                registers.put("B", result);
            } else if (instruction == 2) {
                long n1 = getComboOperand(operand);
                long result = n1 % 8;
                registers.put("B", result);
            } else if (instruction == 3) {
                if (registers.get("A") == 0) {
                    continue;
                }
                i = operand - 2;
            } else if (instruction == 4) {
                long n1 = registers.get("B");
                long n2 = registers.get("C");
                long result = n1 ^ n2;
                registers.put("B", result);
            } else if (instruction == 5) {
                long n1 = getComboOperand(operand);
                long result = n1 % 8;
                res.add(result);
            } else if (instruction == 6) {
                long numerator = registers.get("A");
                double denominator = Math.pow(2, getComboOperand(operand));
                long result = (long)(numerator / denominator);
                registers.put("B", result);
            } else if (instruction == 7) {
                long numerator = registers.get("A");
                double denominator = Math.pow(2, getComboOperand(operand));
                long result = (long)(numerator / denominator);
                registers.put("C", result);
            }
        }
        return res.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public Map<String, Long> getRegisters() {
        return registers;
    }

    private long getComboOperand(Long op) {
        if (op >= 0 && op <= 3) {
            return op;
        }
        if (op == 4) {
            return registers.get("A");
        }
        if (op == 5) {
            return registers.get("B");
        }
        if (op == 6) {
            return registers.get("C");
        }
        return -1;
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day17.txt"));
            Task1 task1 = new Task1(input);
            System.out.println("Result is:" + task1.executeOperations());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
