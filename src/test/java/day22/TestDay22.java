package day22;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay22 extends TestCase {

    @Test
    public void testTask1() {
        int secretNumber = 123;
        Task1 solver = new Task1();
        assertEquals(15887950L, solver.calcNewNumber(secretNumber, 1));
    }

    @Test
    public void testTask1_10Steps() {
        int secretNumber = 123;
        Task1 solver = new Task1();
        assertEquals(5908254L, solver.calcNewNumber(secretNumber, 10));
    }

    @Test
    public void testTask1SmallInput() {
        String smallInput = "1\n" +
                "10\n" +
                "100\n" +
                "2024";
        Task1 solver = new Task1();
        assertEquals(37327623, solver.solve(smallInput));
    }

}