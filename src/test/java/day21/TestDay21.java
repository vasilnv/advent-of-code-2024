package day21;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay21 extends TestCase {
    String input = "029A\n" +
            "980A\n" +
            "179A\n" +
            "456A\n" +
            "379A\n";

    @Test
    public void testTask1() {
        Task1 task1 = new Task1();
        assertEquals("<A^A^^>AvvvA", task1.getInitRobotMovements("029A", false));
    }

    @Test
    public void testTask1_2() {
        Task1 task1 = new Task1();
        task1.checkCombos("<<^^^", new Task1.Coordinate(3, 2), true);
    }

    @Test
    public void testTask1RobotShortestPath() {
        Task1 task1 = new Task1();
        assertEquals("v<<A>>^A<A>A<AAv>A^A<vAAA^>A", task1.getDirectionsMovements("<A^A^^>AvvvA", true, new Task1.Coordinate(0,2)));
    }


    @Test
    public void testTask1_err() {
        assertEquals(126384L, Task1.solve(input));
    }

    @Test
    public void testTask2() {
        String smallInput = "029A";
        // 64 commands length * 379
        assertEquals(68 * 29, Task2.solve(smallInput, 2));
    }

    @Test
    public void testTask2_Rec() {
        assertEquals(126384, Task2.solve(input, 2));
    }
}