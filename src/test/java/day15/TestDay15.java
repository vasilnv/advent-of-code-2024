package day15;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestDay15 {
	String smallInput = "########\n" +
			"#..O.O.#\n" +
			"##@.O..#\n" +
			"#...O..#\n" +
			"#.#.O..#\n" +
			"#...O..#\n" +
			"#......#\n" +
			"########\n" +
			"\n" +
			"<^^>>>vv<v>>v<<";

	String input = "##########\n" +
			"#..O..O.O#\n" +
			"#......O.#\n" +
			"#.OO..O.O#\n" +
			"#..O@..O.#\n" +
			"#O#..O...#\n" +
			"#O..O..O.#\n" +
			"#.OO.O.OO#\n" +
			"#....O...#\n" +
			"##########\n" +
			"\n" +
			"<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^\n" +
			"vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v\n" +
			"><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<\n" +
			"<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^\n" +
			"^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><\n" +
			"^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^\n" +
			">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^\n" +
			"<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>\n" +
			"^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>\n" +
			"v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^";

	@Test
	public void testTask1CanPush() {
		Task1 task1 = new Task1();
		char[][] board = task1.convertBoard(smallInput);
		char[] movements = task1.getMovements(smallInput, board.length);
		assertTrue(movements[0] == '<');
		assertTrue(board[0][0] == '#');
	}

	@Test
	public void testTask1Movements() {
		Task1 task1 = new Task1();
		char[][] board = task1.convertBoard(smallInput);
		char[] movements = task1.getMovements(smallInput, board.length);

		assertTrue(task1.canPush(new int[] {2,2}, new int[] {-1,0}));
		task1.push(new int[] {2,2}, new int[] {-1,0});
		assertTrue(board[1][2] == '@');
		assertTrue(board[2][2] == '.');

		String testSmallInput = "########\n" +
				"#..O.O.#\n" +
				"##@OO..#\n" +
				"#...O..#\n" +
				"#.#.O..#\n" +
				"#...O..#\n" +
				"#......#\n" +
				"########\n" +
				"\n" +
				"<^^>>>vv<v>>v<<";
		task1 = new Task1();
		board = task1.convertBoard(testSmallInput);
		movements = task1.getMovements(testSmallInput, board.length);

		assertTrue(task1.canPush(new int[] {2,2}, new int[] {0,1}));
		task1.push(new int[] {2,2}, new int[] {0,1});
		assertTrue(board[2][3] == '@');
		assertTrue(board[2][4] == 'O');
		assertTrue(board[2][5] == 'O');
		assertTrue(board[2][6] == '.');
	}

	@Test
	public void testTask1SmallInput() {
		Task1 task1 = new Task1();
		char[][] board = task1.convertBoard(smallInput);
		char[] movements = task1.getMovements(smallInput, board.length);
	
		task1.playAll();
		assertEquals(2028, task1.calculateRes());
	}

	@Test
	public void testTask1() {
		Task1 task1 = new Task1();
		char[][] board = task1.convertBoard(input);
		char[] movements = task1.getMovements(input, board.length);

		task1.playAll();
		assertEquals(10092, task1.calculateRes());
	}

}