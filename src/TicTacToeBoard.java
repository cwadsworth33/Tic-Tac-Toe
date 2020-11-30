
public class TicTacToeBoard {
	private CellValue[][] board;

	public TicTacToeBoard() {
		board = new CellValue[3][3];
		for (int i=0; i<board.length; i++) {
			for (int j=0; j<board[i].length; j++) {
				board[i][j] = CellValue.DEFAULT;
			}
		}
	}
	
	public TicTacToeBoard(TicTacToeBoard existingBoard) {
		board = new CellValue[3][3];
		for (int i=0; i<existingBoard.getBoard().length; i++) {
			for (int j=0; j<existingBoard.getBoard().length; j++) {
				board[i][j] = existingBoard.getBoard()[i][j];
			}
		}
	}
	
	public void move(Move m) {
		this.board[m.getX()][m.getY()] = m.getCellVal();
	}
	
	public CellValue[][] getBoard() {
		return board;
	}

	public void setBoard(CellValue[][] board) {
		this.board = board;
	}
	
	public CellValue getCell(Coords coords) {
		return this.board[coords.getX()][coords.getY()];
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		} else if (!(o instanceof TicTacToeBoard)) {
			return false;
		} else {
			TicTacToeBoard otherBoard = (TicTacToeBoard) o;
			for (int i=0; i<board.length; i++) {
				for (int j=0; j<board.length; j++) {
					if (board[i][j] != otherBoard.getCell(new Coords(i, j))) {
						return false;
					}
				}
			}
			return true;
		}
	}
	
	private String enumToString(CellValue v) {
		if (v == CellValue.X) {
			return "X";
		} else if (v == CellValue.O) {
			return "O";
		} else {
			return "-";
		}
	}
	
	@Override
	public String toString() {
		return "" + enumToString(board[0][2]) + " " + enumToString(board[1][2]) + " " + enumToString(board[2][2]) + "\r\n"
				+ enumToString(board[0][1]) + " " + enumToString(board[1][1]) + " " + enumToString(board[2][1]) + "\r\n"
				+ enumToString(board[0][0]) + " " + enumToString(board[1][0]) + " " + enumToString(board[2][0]) + "\r\n";
	}
		
}