package day18;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay18 extends TestCase {
    String input = "5,4\n" +
            "4,2\n" +
            "4,5\n" +
            "3,0\n" +
            "2,1\n" +
            "6,3\n" +
            "2,4\n" +
            "1,5\n" +
            "0,6\n" +
            "3,3\n" +
            "2,6\n" +
            "5,1\n" +
            "1,2\n" +
            "5,5\n" +
            "2,5\n" +
            "6,5\n" +
            "1,4\n" +
            "0,4\n" +
            "6,4\n" +
            "1,1\n" +
            "6,1\n" +
            "1,0\n" +
            "0,5\n" +
            "1,6\n" +
            "2,0\n";

    @Test
    public void testTask1() {
        Task1 task1 = new Task1(input, 12, 7, 7);
        assertEquals(22, task1.solve());
    }

    @Test
    public void testTask2() {
        int[] result = new int[2];
        Task2.solve(input, 12, 7, 7, result);
        assertEquals(6, result[0]);
        assertEquals(1, result[1]);
    }

}