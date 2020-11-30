import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TicTacToeBoardTest {
	
	TicTacToeBoard emptyBoard;
	TicTacToeBoard board1;
	
	@BeforeEach
	void before() {
		emptyBoard = new TicTacToeBoard();
		board1 = new TicTacToeBoard();
	}

	@Test
	void shouldInitializeBoard() {
		assertEquals(emptyBoard.getBoard().length, 3);
		assertEquals(emptyBoard.getBoard()[0].length, 3);
		assertEquals(emptyBoard.getCell(new Coords(0, 2)), CellValue.DEFAULT);
	}
	
	@Test
	void shouldCopyExistingBoard() {
		board1.move(new Move(0, 0, CellValue.X));
		assertEquals(board1.getCell(new Coords(0, 0)), CellValue.X);
		TicTacToeBoard boardCopy = new TicTacToeBoard(board1);
		assertEquals(boardCopy.getCell(new Coords(0, 0)), CellValue.X);
		boardCopy.move(new Move(0, 1, CellValue.O));
		assertEquals(boardCopy.getCell(new Coords(0, 1)), CellValue.O);
		assertEquals(board1.getCell(new Coords(0, 1)), CellValue.DEFAULT);
	}

}
