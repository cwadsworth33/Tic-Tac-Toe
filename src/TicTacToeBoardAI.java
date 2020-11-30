import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TicTacToeBoardAI {
	
	private BoardCoordinates coords;
	
	public TicTacToeBoardAI(BoardCoordinates coords) {
		this.coords = coords;
	}
	
	public List<Move> actions(TicTacToeBoard board, CellValue playerToMove) {
		return coords.getTicTacToeBoardCoordinates().stream()
			.filter((Coords c) -> board.getCell(c) == CellValue.DEFAULT)
			.map((Coords c) -> new Move(c.getX(), c.getY(), playerToMove))
			.collect(Collectors.toList());
	}
	
	public CellValue playerValueToMove(TicTacToeBoard board, CellValue firstPlayerVal) {
		int xOnBoard = (int) coords.getTicTacToeBoardCoordinates().stream()
			.map((Coords c) -> board.getCell(c))
			.filter(cv -> cv == CellValue.X)
			.count();
		int yOnBoard = (int) coords.getTicTacToeBoardCoordinates().stream()
			.map((Coords c) -> board.getCell(c))
			.filter(cv -> cv == CellValue.O)
			.count();
		if (xOnBoard == yOnBoard) {
			return firstPlayerVal;
		} else {
			return xOnBoard > yOnBoard ? CellValue.O : CellValue.X;
		}
	}
	
	public TicTacToeBoard result(TicTacToeBoard board, Move move) {
		TicTacToeBoard copy = new TicTacToeBoard(board);
		copy.move(move);
		return copy;
	}
	
	public boolean threeInARow(TicTacToeBoard board, List<Coords> row, CellValue playerVal) {
		return row.stream().filter(c -> board.getCell(c) == playerVal).count() == 3;
	}
	
	public boolean hasWon(TicTacToeBoard board, CellValue playerVal) {
		return threeInARow(board, coords.getColOne(), playerVal) 
				|| threeInARow(board, coords.getColTwo(), playerVal)
				|| threeInARow(board, coords.getColThree(), playerVal)
				|| threeInARow(board, coords.getRowOne(), playerVal)
				|| threeInARow(board, coords.getRowTwo(), playerVal)
				|| threeInARow(board, coords.getRowThree(), playerVal)
				|| threeInARow(board, coords.getDiagOne(), playerVal)
				|| threeInARow(board, coords.getDiagTwo(), playerVal);
	}
	
	public boolean isFull(TicTacToeBoard board) {
		return coords.getTicTacToeBoardCoordinates().stream()
				.filter(c -> board.getCell(c) == CellValue.DEFAULT).count() == 0;
	}
	
	public boolean isTerminalPosition(TicTacToeBoard board) {
		return isFull(board) || hasWon(board, CellValue.X) || hasWon(board, CellValue.O);
	}
	
	public int evaluatePosition(TicTacToeBoard board, CellValue firstPlayerVal) {
		CellValue playerValToMove = playerValueToMove(board, firstPlayerVal);
		CellValue oppositePlayer = playerValToMove == CellValue.X ? CellValue.O : CellValue.X;
		int MAX = 1;
		int MIN = -1;
		int NEUTRAL = 0;
		int evaluation;
		if (hasWon(board, playerValToMove)) {
			evaluation = playerValToMove == CellValue.X ? MAX : MIN;
		} else if (hasWon(board, oppositePlayer)) {
			evaluation = oppositePlayer == CellValue.X ? MAX : MIN;
		} else if (isFull(board)) {
			evaluation = NEUTRAL;
		} else {
			List<Move> possibleMoves = actions(board, playerValToMove);
			evaluation = possibleMoves.stream()
				.map(m -> evaluatePosition(result(board, m), firstPlayerVal))
				.reduce(0, playerValToMove == CellValue.X ? Integer::max : Integer::min);
		}
		return evaluation;
	}
	
	public Move findMove(TicTacToeBoard board, CellValue firstPlayerVal) {
		CellValue playerToMove = playerValueToMove(board, firstPlayerVal);
		List<Move> possibleMoves = actions(board, playerToMove);
		List<Integer> evaluations = possibleMoves.stream()
				.map(m -> evaluatePosition(result(board, m), firstPlayerVal))
				.collect(Collectors.toList());
		Integer maxVal = Collections.max(evaluations);
		Integer index = evaluations.indexOf(maxVal);
		return possibleMoves.get(index);
	}
}
