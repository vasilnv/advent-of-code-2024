package day24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Task2 extends Task1 {
    String input;
    ExecutorService executor = Executors.newSingleThreadExecutor();

    public List<String> calculateTargetZ(String input, Function<Pair, Long> applyOn) {
        this.input = input;
        loadInput(input);

        List<String> xs = calculated.keySet().stream().filter(x -> x.startsWith("x")).sorted().collect(Collectors.toList());
        List<String> ys = calculated.keySet().stream().filter(x -> x.startsWith("y")).sorted().collect(Collectors.toList());
        long x = 0;
        for (int i = 0; i < xs.size(); i++) {
            x += (long) (calculated.get(xs.get(i)) * Math.pow(2, i));
        }
        long y = 0;
        for (int i = 0; i < ys.size(); i++) {
            y += (long) (calculated.get(ys.get(i)) * Math.pow(2, i));
        }

        long targetZ = applyOn.apply(new Pair(x, y));
        long currZ = solve();

        System.out.println("Target Z: " + targetZ + " Current Z: " + currZ);
        long currTarget = targetZ;
        List<Long> remaindersTarget = new ArrayList<>();
        while (currTarget > 0) {
            remaindersTarget.add(currTarget % 2);
            currTarget /= 2;
        }

        long currCurrZ = currZ;
        List<Long> remaindersCurr = new ArrayList<>();
        while (currCurrZ > 0) {
            remaindersCurr.add(currCurrZ % 2);
            currCurrZ /= 2;
        }

        System.out.println("Target: ");
        for (int i = 0; i < remaindersTarget.size(); i++) {
            if (i < remaindersCurr.size()) {
                System.out.print(remaindersCurr.get(i));
            } else {
                remaindersCurr.add(0L);
                System.out.print(0);
            }
        }
        System.out.println("\nCurrent: ");
        for (int i = 0; i < remaindersTarget.size(); i++) {
            System.out.print(remaindersTarget.get(i));
        }

        List<String> candidates = solve2(input);
        return candidates;
    }

    class Pair {
        long x;
        long y;

        public Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public List<String> solve2(String input) {
        this.loadInput(input);
        String finalZ = "z45";
        Set<String> candidates = new HashSet<>();
        for (int i = 0; i < rules.size(); i++) {
            String opRes = rules.get(i).split("->")[1].trim();
            String[] predicate = rules.get(i).split("->")[0].split(" ");
            String operand1 = predicate[0].trim();
            String operand2 = predicate[2].trim();
            String op = predicate[1].trim();

            if (opRes.startsWith("z") && !op.equals("XOR") && !opRes.equals(finalZ)) {
                candidates.add(opRes);
            } else if (op.equals("XOR") && !isXYZ(operand1) && !isXYZ(operand2) && !isXYZ(opRes)) {
                candidates.add(opRes);
            } else if (op.equals("AND")) {
                for (var subRule : rules) {
                    if (!subRule.equals(rules.get(i))) {
                        String[] subpredicate = subRule.split("->")[0].split(" ");
                        String subOp1 = subpredicate[0].trim();
                        String subOp2 = subpredicate[2].trim();
                        String subOp = subpredicate[1].trim();
                        if ((opRes.equals(subOp1) || opRes.equals(subOp2)) && !subOp.equals("OR")) {
                            candidates.add(opRes);
                        }
                    }
                }
            } else if (op.equals("XOR")) {
                for (var subRule : rules) {
                    if (!subRule.equals(rules.get(i))) {
                        String[] subpredicate = subRule.split("->")[0].split(" ");
                        String subOp1 = subpredicate[0].trim();
                        String subOp2 = subpredicate[2].trim();
                        String subOp = subpredicate[1].trim();
                        if ((opRes.equals(subOp1) || opRes.equals(subOp2)) && subOp.equals("OR")) {
                            candidates.add(opRes);
                        }
                    }
                }
            }
        }
        // remove init
        candidates.remove("tss");
        return new ArrayList<>(candidates).stream().sorted().collect(Collectors.toList());
    }

    private static boolean isXYZ(String wire) {
        return wire.startsWith("x") || wire.startsWith("y") || wire.startsWith("z");
    }

    public static void main(String[] args) {
        try {
            String input = Files.readString(Path.of("src/main/resources/day24.txt"));
            Task2 solver = new Task2();
            System.out.println("\nResult is: " + solver.calculateTargetZ(input, pair -> pair.x + pair.y));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
