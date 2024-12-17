package day17;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.function.Function;

public class TestDay17 extends TestCase {
    @Test
    public void testTask1() {
        String input = "Register A: 729\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,1,5,4,3,0";
        Task1 task1 = new Task1(input);
        assertEquals("4,6,3,5,6,3,5,2,1,0", task1.executeOperations());
    }

    @Test
    public void testTask1SimpleTests() {
        String input = "Register A: 0\n" +
                "Register B: 0\n" +
                "Register C: 9\n" +
                "\n" +
                "Program: 2,6";
        Task1 task1 = new Task1(input);
        assertEquals("", task1.executeOperations());
        assertEquals(1, (long)task1.getRegisters().get("B"));

        input = "Register A: 10\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 5,0,5,1,5,4";
        task1 = new Task1(input);
        assertEquals("0,1,2", task1.executeOperations());

    }

    public void testCase3() {
        String input = "Register A: 2024\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,1,5,4,3,0";
        Task1 task1 = new Task1(input);
        assertEquals("4,2,5,6,7,7,7,7,3,1,0", task1.executeOperations());
        assertEquals(0, (long)task1.getRegisters().get("A"));

    }

    public void testCase4() {
        String input = "Register A: 2024\n" +
                "Register B: 29\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 1,7";
        Task1 task1 = new Task1(input);
        assertEquals("", task1.executeOperations());
        assertEquals(26, (long)task1.getRegisters().get("B"));
    }

    public void testCase5() {
        String input = "Register A: 2024\n" +
                "Register B: 2024\n" +
                "Register C: 43690\n" +
                "\n" +
                "Program: 4,0";
        Task1 task1 = new Task1(input);
        assertEquals("", task1.executeOperations());
        assertEquals(44354, (long)task1.getRegisters().get("B"));
    }

    public void testTask2() {
        String input = "Register A: 2024\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 0,3,5,4,3,0";
        assertEquals(117440, Task2.solve(input, task2 -> {
            long a = task2.getRegisters().get("A");
            return a % 8;
        }));
    }

    public void testTask2_Custom() {
        String input = "Register A: 62769524\n" +
                "Register B: 0\n" +
                "Register C: 0\n" +
                "\n" +
                "Program: 2,4,1,7,7,5,0,3,4,0,1,7,5,5,3,0";
        Function<Task2, Long> myFnc = task2 -> {
            long a = task2.getRegisters().get("A");
            long b = a % 8;
            b = b ^ 7;
            long c = (long)(a / Math.pow(2L, b));
            b = b ^ c;
            b = b ^ 7;
            return b % 8;
        };

        Task2 task2 = new Task2(input);
        System.out.println(20975098411045L * 8L);
        Task1 task1 = new Task1(input);
        long i = 0;
        while (true) {
            task1.registers.put("A", i);
            if (task1.executeOperations().equals("3,0")) {
                System.out.println("FOR 3,0 FOUND i = " + i);
                break;
            }

            if (task1.executeOperations().equals("5,3,0")) {
                System.out.println("FOR 5,3,0 FOUND i = " + i);
                break;
            }

            i++;
        }
        task2.getRegisters().put("A", 258394985014171L);
        task1.getRegisters().put("A", 258394985014171L);
        System.out.println(myFnc.apply(task2));
        System.out.println(task1.executeOperations());
    }

}