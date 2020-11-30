import java.util.Scanner;

public class TicTacToeGame {
	public static void printWelcome() {
		System.out.println("Welcome to Tic Tac Toe");
	}
	
	public static String getPlayerName(Scanner myScanner) {
		System.out.println("What is your player name?");
		String playerName = myScanner.nextLine();
		return playerName;
	}
	
	public static CellValue getPlayerCellValue(Scanner myScanner) {
		System.out.println("Play as X or O? (X moves first)");
		String userInput = "";
		while (!userInput.equals("X") && !userInput.equals("x") && !userInput.equals("O") && !userInput.equals("o")) {
			userInput = myScanner.nextLine();
			if (!userInput.equals("X") && !userInput.equals("x") && !userInput.equals("O") && !userInput.equals("o")) {
				System.out.println("Please enter 'X' or 'x' or 'O' or 'o'");
			}
		}
		if (userInput.equals("X") || userInput.equals("x")) {
			return CellValue.X;
		} 
		return CellValue.O;
	}
	
	public static Move getPlayerMove(TicTacToeBoard board, Scanner myScanner, CellValue playerValue, String playerName) {
		int xUserInput = -1;
		int yUserInput = -1;
		boolean invalidCoords = true;
		while (invalidCoords) {
			while (xUserInput < 0 || xUserInput > 2) {
				System.out.println(playerName + ", " + "Enter x coordinate of move");
				xUserInput = myScanner.nextInt();
				if (xUserInput < 0 || xUserInput > 2) {
					System.out.println(playerName + ", " + "Please enter a number from 0 to 2");
				}
			}
			while (yUserInput < 0 || yUserInput > 2) {
				System.out.println("Enter y coordinate of move");
				yUserInput = myScanner.nextInt();
				if (yUserInput < 0 || yUserInput > 2) {
					System.out.println(playerName + ", " + "please enter a number from 0 to 2");
				}
			}
			CellValue curVal = board.getCell(new Coords(xUserInput, yUserInput));
			if (curVal != CellValue.DEFAULT) {
				System.out.println("That square has already been filled. Please enter valid coordinates.");
				xUserInput = -1;
				yUserInput = -1;
			} else {
				invalidCoords = false;
			}
		}
		return new Move(xUserInput, yUserInput, playerValue);
	}
	
	public static Move getComputerMove(TicTacToeBoardAI ai, TicTacToeBoard board, CellValue firstPlayerValue) {
		System.out.println("Computer is thinking...");
		return ai.findMove(board, firstPlayerValue);
	}
	
}
