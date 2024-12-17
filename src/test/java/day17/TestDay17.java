package day17;

import junit.framework.TestCase;
import org.junit.Test;

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

}