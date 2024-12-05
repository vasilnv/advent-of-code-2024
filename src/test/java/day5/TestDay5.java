package day5;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestDay5 extends TestCase {
    String input;
    List<List<Integer>> pairs;
    List<List<Integer>> sequences;

    @Before
    public void setUp() throws Exception {
        input = "47|53\n" +
                "97|13\n" +
                "97|61\n" +
                "97|47\n" +
                "75|29\n" +
                "61|13\n" +
                "75|53\n" +
                "29|13\n" +
                "97|29\n" +
                "53|29\n" +
                "61|53\n" +
                "97|53\n" +
                "61|29\n" +
                "47|13\n" +
                "75|47\n" +
                "97|75\n" +
                "47|61\n" +
                "75|61\n" +
                "47|29\n" +
                "75|13\n" +
                "53|13\n" +
                "\n" +
                "75,47,61,53,29\n" +
                "97,61,53,29,13\n" +
                "75,29,13\n" +
                "75,97,47,61,53\n" +
                "61,13,29\n" +
                "97,13,75,29,47";

        pairs = Task1.getPairs(input);
        sequences = Task1.extractSequences(input);

    }

    @Test
    public void testTask1() {

        Task1 task1 = new Task1();
        task1.createGraph(pairs);

        assertTrue(task1.checkSequence(sequences.get(0)));
        assertTrue(task1.checkSequence(sequences.get(1)));
        assertTrue(task1.checkSequence(sequences.get(2)));
        assertFalse(task1.checkSequence(sequences.get(3)));
        assertFalse(task1.checkSequence(sequences.get(4)));
        assertFalse(task1.checkSequence(sequences.get(5)));

        assertEquals(143, Task1.getTotalScore(sequences, pairs));
    }

    @Test
    public void testTask2() {
        assertEquals(123, Task1.getTotalScoreErroneous(sequences, pairs));
    }

}