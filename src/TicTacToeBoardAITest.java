import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeBoardAITest {
	
	BoardCoordinates coords;
	TicTacToeBoardAI ai;
	TicTacToeBoard board1;

	@BeforeEach
	void setUp() throws Exception {
		coords = BoardCoordinates.getInstance();
		ai = new TicTacToeBoardAI(coords);
		board1 = new TicTacToeBoard();
	}

	@Test
	void testActions() {
		List<Move> emptyBoardActions = coords.getTicTacToeBoardCoordinates().stream()
				.map(c -> new Move(c.getX(), c.getY(), CellValue.X))
				.collect(Collectors.toList());
		assertEquals(emptyBoardActions, ai.actions(board1, CellValue.X));
		board1.move(new Move(0, 0, CellValue.O));
		List<Move> actionsAfterMove = emptyBoardActions.stream()
				.filter(m -> m.getX() != 0 || m.getY() != 0)
				.collect(Collectors.toList());
		assertEquals(actionsAfterMove, ai.actions(board1, CellValue.X));
	}
	
	@Test
	void testPlayerValueToMove() {
		assertEquals(ai.playerValueToMove(board1, CellValue.X), CellValue.X);
		board1.move(new Move(0, 0, CellValue.X));
		assertEquals(ai.playerValueToMove(board1, CellValue.X), CellValue.O);
		board1.move(new Move(1, 1, CellValue.O));
		assertEquals(ai.playerValueToMove(board1, CellValue.X), CellValue.X);
	}
	
	@Test
	void testResult() {
		TicTacToeBoard afterMove = ai.result(board1, new Move(0, 0, CellValue.X));
		assertNotEquals(afterMove, board1);
		board1.move(new Move(0, 0, CellValue.X));
		assertEquals(afterMove, board1);
	}
	
	@Test
	void testThreeInARow() {
		assertFalse(ai.threeInARow(board1, coords.getRowOne(), CellValue.X));
		board1.move(new Move(0, 0, CellValue.X));
		board1.move(new Move(1, 0, CellValue.X));
		board1.move(new Move(2, 0, CellValue.X));
		assertTrue(ai.threeInARow(board1, coords.getRowOne(), CellValue.X));
		assertFalse(ai.threeInARow(board1, coords.getRowOne(), CellValue.O));
		assertFalse(ai.threeInARow(board1, coords.getColOne(), CellValue.X));
	}
	
	@Test
	void testHasWon() {
		assertFalse(ai.hasWon(board1, CellValue.X));
		board1.move(new Move(0, 0, CellValue.X));
		board1.move(new Move(1, 0, CellValue.X));
		board1.move(new Move(2, 0, CellValue.X));
		assertTrue(ai.hasWon(board1, CellValue.X));
		assertFalse(ai.hasWon(board1, CellValue.O));
	}
	
	@Test
	void testIsFull() {
		assertFalse(ai.isFull(board1));
		coords.getTicTacToeBoardCoordinates().stream()
			.forEach(c -> board1.move(new Move(c.getX(), c.getY(), CellValue.X)));
		assertTrue(ai.isFull(board1));
		board1.move(new Move(0, 0, CellValue.DEFAULT));
		assertFalse(ai.isFull(board1));
	}
	
	@Test
	void testEvaluatePosition() {
		assertEquals(ai.evaluatePosition(board1, CellValue.X), 0);
		board1.move(new Move(0, 0, CellValue.X));
		board1.move(new Move(0, 1, CellValue.O));
		board1.move(new Move(1, 0, CellValue.X));
		board1.move(new Move(1, 1, CellValue.O));
		assertEquals(ai.evaluatePosition(board1, CellValue.X), 1);
		assertEquals(ai.evaluatePosition(board1, CellValue.O), -1);
	}
	
	@Test
	void testComputerMove() {
		board1.move(new Move(0, 0, CellValue.X));
		board1.move(new Move(0, 1, CellValue.O));
		board1.move(new Move(1, 0, CellValue.X));
		board1.move(new Move(1, 1, CellValue.O));
		assertEquals(ai.findMove(board1, CellValue.X), new Move(2, 0, CellValue.X));
	}

}
