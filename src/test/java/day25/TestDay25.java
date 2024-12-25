package day25;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay25 extends TestCase {
    String input = "#####\n" +
            ".####\n" +
            ".####\n" +
            ".####\n" +
            ".#.#.\n" +
            ".#...\n" +
            ".....\n" +
            "\n" +
            "#####\n" +
            "##.##\n" +
            ".#.##\n" +
            "...##\n" +
            "...#.\n" +
            "...#.\n" +
            ".....\n" +
            "\n" +
            ".....\n" +
            "#....\n" +
            "#....\n" +
            "#...#\n" +
            "#.#.#\n" +
            "#.###\n" +
            "#####\n" +
            "\n" +
            ".....\n" +
            ".....\n" +
            "#.#..\n" +
            "###..\n" +
            "###.#\n" +
            "###.#\n" +
            "#####\n" +
            "\n" +
            ".....\n" +
            ".....\n" +
            ".....\n" +
            "#....\n" +
            "#.#..\n" +
            "#.#.#\n" +
            "#####";

    @Test
    public void testTask1() {
        Task1 solver = new Task1(input);
        assertEquals(3, solver.solve());

    }

}