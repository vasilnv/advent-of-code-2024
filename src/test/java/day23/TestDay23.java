package day23;

import junit.framework.TestCase;
import org.junit.Test;

public class TestDay23 extends TestCase {
    String input = "kh-tc\n" +
            "qp-kh\n" +
            "de-cg\n" +
            "ka-co\n" +
            "yn-aq\n" +
            "qp-ub\n" +
            "cg-tb\n" +
            "vc-aq\n" +
            "tb-ka\n" +
            "wh-tc\n" +
            "yn-cg\n" +
            "kh-ub\n" +
            "ta-co\n" +
            "de-co\n" +
            "tc-td\n" +
            "tb-wq\n" +
            "wh-td\n" +
            "ta-ka\n" +
            "td-qp\n" +
            "aq-cg\n" +
            "wq-ub\n" +
            "ub-vc\n" +
            "de-ta\n" +
            "wq-aq\n" +
            "wq-vc\n" +
            "wh-yn\n" +
            "ka-de\n" +
            "kh-ta\n" +
            "co-tc\n" +
            "wh-qp\n" +
            "tb-vc\n" +
            "td-yn";

    @Test
    public void testTask1() {
        Task1 solver = new Task1(input);
        assertEquals(7, solver.solve());
    }

}