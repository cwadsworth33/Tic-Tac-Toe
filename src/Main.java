import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TicTacToeBoard board = new TicTacToeBoard();
		BoardCoordinates coords = BoardCoordinates.getInstance();
		TicTacToeBoardAI ai = new TicTacToeBoardAI(coords);
		Scanner myScanner = new Scanner(System.in);
		
		boolean isGameOver = false;
		CellValue firstPlayer = CellValue.X;
		CellValue playerValue;
		CellValue computerValue;
		
		TicTacToeGame.printWelcome();
		String playerName = TicTacToeGame.getPlayerName(myScanner);
		playerValue = TicTacToeGame.getPlayerCellValue(myScanner);
		if (playerValue == CellValue.X) {
			computerValue = CellValue.O;
		} else {
			computerValue = CellValue.X;
		}
		System.out.println("Game Begin!");
		
		CellValue curPlayer = CellValue.X;
		
		// Game loop
		while (!isGameOver) {
			Move nextMove;
			if (curPlayer == playerValue) {
				nextMove = TicTacToeGame.getPlayerMove(board, myScanner, playerValue, playerName);
			} else {
				nextMove = TicTacToeGame.getComputerMove(ai, board, firstPlayer);
				System.out.println("The computer moves to " + nextMove.getX() + ", " + nextMove.getY());
				System.out.println("");
			}
			board.move(nextMove);
			System.out.println(board);
			if (ai.isTerminalPosition(board)) {
				if (ai.hasWon(board, playerValue)) {
					System.out.println("You win!");
				} else if (ai.hasWon(board, computerValue)) {
					System.out.println("Oh no, the computer wins!");
				} else {
					System.out.println("Game ends in a draw!");
				}
				isGameOver = true;
			}
			curPlayer = curPlayer == CellValue.X ? CellValue.O : CellValue.X;
		}
	}

}
