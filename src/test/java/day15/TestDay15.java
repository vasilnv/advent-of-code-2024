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

	@Test
	public void testTask2() {
		String smallInputTask2 = "#######\n" +
				"#...#.#\n" +
				"#.....#\n" +
				"#..OO@#\n" +
				"#..O..#\n" +
				"#.....#\n" +
				"#######\n" +
				"\n" +
				"<vv<<^^<<^^";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(smallInputTask2);
		char[] movements = task2.getMovements(smallInputTask2, board.length);

		task2.pushLeft(new int[] {3,10}, new int[] {0,-1});
		assertTrue(board[3][9] == '@');
		assertTrue(board[3][8] == ']');
		assertTrue(board[3][7] == '[');
		assertTrue(board[3][6] == ']');
		assertTrue(board[3][5] == '[');
		task2.pushUpDown(new int[] {3,9}, new int[] {1,0});
		assertTrue(board[4][9] == '@');
		assertTrue(board[3][9] == '.');
		assertTrue(board[5][9] == '.');
		task2.pushUpDown(new int[] {4,9}, new int[] {1,0});
		assertTrue(board[5][9] == '@');
		assertTrue(board[4][9] == '.');
		assertTrue(board[6][9] == '#');
		task2.pushLeft(new int[] {5,9}, new int[] {0,-1});
		assertTrue(board[5][8] == '@');
		assertTrue(board[5][9] == '.');
		assertTrue(board[5][7] == '.');
		task2.pushLeft(new int[] {5,8}, new int[] {0,-1});
		assertTrue(board[5][7] == '@');
		assertTrue(board[5][8] == '.');
		assertTrue(board[5][6] == '.');
		task2.pushUpDown(new int[] {5,7}, new int[] {-1,0});
		assertTrue(board[4][7] == '@');
		assertTrue(board[3][7] == ']');
		assertTrue(board[3][6] == '[');
		assertTrue(board[2][7] == '[');
		assertTrue(board[2][8] == ']');
		assertTrue(board[2][5] == '[');
		assertTrue(board[2][6] == ']');
		task2.pushLeft(new int[] {4,7}, new int[] {0, -1});
		task2.pushLeft(new int[] {4,6}, new int[] {0, -1});
		task2.pushUpDown(new int[] {4,5}, new int[] {-1,0});
		assertEquals('@', board[3][5]);
		task2.pushUpDown(new int[] {3,5}, new int[] {-1,0});
		assertEquals('@', board[2][5]);
		assertEquals('[', board[1][5]);
		assertEquals(']', board[1][6]);
	}

	@Test
	public void testTask2LeftRoght() {
		String smallInputTask2 = "#######\n" +
				"#...#.#\n" +
				"#.....#\n" +
				"#..@O.#\n" +
				"#..O..#\n" +
				"#.....#\n" +
				"#######\n" +
				"\n" +
				"<vv<<^^<<^^";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(smallInputTask2);
		char[] movements = task2.getMovements(smallInputTask2, board.length);

		task2.pushRight(new int[] {3,7}, new int[] {0,1});
		assertTrue(board[3][8] == '@');
		assertTrue(board[3][9] == '[');
		assertTrue(board[3][10] == ']');
	}

	@Test
	public void testTask2CanMove() {
		String smallInputTask2 = "#######\n" +
				"#...#@#\n" +
				"#######\n" +
				"\n" +
				"<vv<<^^<<>>^^";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(smallInputTask2);
		char[] movements = task2.getMovements(smallInputTask2, board.length);

		task2.playAll();
		assertEquals(board[1][11], '@');
	}

	@Test
	public void testTask2CanMoveBoxes() {
		String smallInputTask2 = "###########\n" +
				"#...#..O..#\n" +
				"#...#..O..#\n" +
				"#...#OO@OO#\n" +
				"#...#..O..#\n" +
				"#...#..O..#\n" +
				"###########\n" +
				"\n" +
				"<vv<<^^<<>>^^";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(smallInputTask2);
		char[] movements = task2.getMovements(smallInputTask2, board.length);

		task2.playAll();
		assertEquals(board[3][15], '@');
	}

	@Test
	public void testTask2Input() {
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(input);
		char[] movements = task2.getMovements(input, board.length);

		task2.playAll();
		assertEquals(9021, task2.calculateRes());
	}

	@Test
	public void testTask2SmallInputPushUp() {
		String reeallySmallInput = "########\n" +
				"#O......# \n" +
				"#.OO..O# \n" +
				"#..@...# \n" +
				"########\n" +
				"\n" +
				"^^>><<vvvvv";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(reeallySmallInput);
		char[] movements = task2.getMovements(reeallySmallInput, board.length);

		task2.playAll();
		assertEquals(624, task2.calculateRes());
	}

	@Test
	public void testTask2SmallInputPushDown() {
		String reeallySmallInput = "########\n" +
				"#.@.....# \n" +
				"#.OO.OO# \n" +
				"#..O...# \n" +
				"########\n" +
				"\n" +
				">vvvvv";
		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(reeallySmallInput);
		char[] movements = task2.getMovements(reeallySmallInput, board.length);

		task2.playAll();
		assertEquals(1238, task2.calculateRes());
	}
	
	@Test
	public void testTask2SmallExample() {
		String smallExample = "#######\n" +
				"#...#.#\n" +
				"#.....#\n" +
				"#..OO@#\n" +
				"#..O..#\n" +
				"#.....#\n" +
				"#######\n" +
				"\n" +
				"<vv<<^^<<^^";

		Task2 task2 = new Task2();
		char[][] board = task2.convertBoard(smallExample);
		char[] movements = task2.getMovements(smallExample, board.length);
		task2.playAll();
		assertEquals(618, task2.calculateRes());
	}
}