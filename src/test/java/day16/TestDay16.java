package day16;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay16 extends TestCase {
    String input = "###############\n" +
            "#.......#....E#\n" +
            "#.#.###.#.###.#\n" +
            "#.....#.#...#.#\n" +
            "#.###.#####.#.#\n" +
            "#.#.#.......#.#\n" +
            "#.#.#####.###.#\n" +
            "#...........#.#\n" +
            "###.#.#####.#.#\n" +
            "#...#.....#.#.#\n" +
            "#.#.#.###.#.#.#\n" +
            "#.....#...#.#.#\n" +
            "#.###.#.#.#.#.#\n" +
            "#S..#.....#...#\n" +
            "###############";

    @Test
    public void testTask1() {
        Task1 task1 = new Task1(input);
        assertEquals(7036, task1.solve());
    }

    @Test
    public void testTask2() {
        Task2 task2 = new Task2(input, 7036);
        assertEquals(45, task2.solve().size());
    }
}