package day20;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay20 extends TestCase {

    String input = "###############\n" +
            "#...#...#.....#\n" +
            "#.#.#.#.#.###.#\n" +
            "#S#...#.#.#...#\n" +
            "#######.#.#.###\n" +
            "#######.#.#...#\n" +
            "#######.#.###.#\n" +
            "###..E#...#...#\n" +
            "###.#######.###\n" +
            "#...###...#...#\n" +
            "#.#####.#.###.#\n" +
            "#.#...#.#.#...#\n" +
            "#.#.#.#.#.#.###\n" +
            "#...#...#...###\n" +
            "###############";

    @Test
    public void testTask1() {
        Task1 solver = new Task1(input);
        assertEquals(10, solver.solve(10));
    }

    @Test
    public void testTask2() {
        Task2 solver = new Task2(input);
        assertEquals(7, solver.solve(74));
    }

}