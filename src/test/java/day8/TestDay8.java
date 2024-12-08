package day8;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class TestDay8 {
	String input;
	
	@Before
	public void setUp() throws Exception {
		input = "............\n" +
				"........0...\n" +
				".....0......\n" +
				".......0....\n" +
				"....0.......\n" +
				"......A.....\n" +
				"............\n" +
				"............\n" +
				"........A...\n" +
				".........A..\n" +
				"............\n" +
				"............";	
	}
	
	@Test
	public void testTask1() {
		Task1 task1 = new Task1();
		char[][] mapInput = task1.convertInput(input);
		Map<Character, Set<Task1.Pair>> unqiues = task1.findAllUniqueSymbols(mapInput);
		assertEquals(2, unqiues.size());
		assertEquals(4, unqiues.get('0').size());
		assertEquals(3, unqiues.get('A').size());
		
		char[][] result = task1.updateCoordinateMap(mapInput);
		Map<Character, Set<Task1.Pair>> unqiuesAfter = task1.findAllUniqueSymbols(result);
		assertEquals(3, unqiuesAfter.size());
		task1.printBoard(result);
		assertEquals(14, task1.calculateNewChars(result));
	}
	
	@Test
	public void testTask2Small() {
		String simple = "T.........\n" +
				"...T......\n" +
				".T........\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"..........\n" +
				"..........";
		assertEquals(9, Task2.calculateRes(simple));
	}
	@Test
	public void testTask2() {
		assertEquals(34, Task2.calculateRes(input));
	}

}