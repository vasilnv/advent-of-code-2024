package day19;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay19 extends TestCase {
    String input = "r, wr, b, g, bwu, rb, gb, br\n" +
            "\n" +
            "brwrr\n" +
            "bggr\n" +
            "gbbr\n" +
            "rrbgbr\n" +
            "ubwu\n" +
            "bwurrg\n" +
            "brgr\n" +
            "bbrgwb";

    @Test
    public void testTask1() {
        Task1 task1 = new Task1();
        task1.convertInput(input);
        assertEquals(6, task1.solve());
    }

    @Test
    public void testTask2() {
        Task1 solver = new Task2();
        solver.convertInput(input);
        assertEquals(16, solver.solve());
    }

}