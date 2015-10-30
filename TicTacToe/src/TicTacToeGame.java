//Tony Chen
//Oct 28 2015
//Game logic (or back end) for the tic tac toe game

public class TicTacToeGame {
	
	private char[][] gameBoard;
	
	public TicTacToeGame() {
		//Initialize board with (space) char
		gameBoard = new char[][] {{' ', ' ', ' '},{' ', ' ', ' '},{' ', ' ', ' '}};
	}

	public void insertLetter(char c, int row, int col) {
		gameBoard[row][col] = c;
	}
	
	public char[][] getGameBoard() {
		return gameBoard;
	}
	
	public void clearBoard() {
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				gameBoard[r][c] = ' ';
			}
		}
	}
	
	public boolean calcWin() {
		boolean win = false;
		
			for (int r = 0; r < 3; r++) {
				if ((gameBoard[r][0] != ' ') && (gameBoard[r][0] == gameBoard[r][1]) && (gameBoard[r][1] == gameBoard[r][2])){
					win = true;
					break;
				}
			}
			//Stop checking if already found a win
			if (!win) {
				for (int c = 0; c < 3; c++) {
					if ((gameBoard[0][c] != ' ') && (gameBoard[0][c] == gameBoard[1][c]) && (gameBoard[1][c] == gameBoard[2][c])) {
						win = true;
						break;
					}
				}
			}
			//Stop checking if already found a win
			if (!win) {
				if ( (gameBoard[0][0] != ' ') && (gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][2]) || 
						(gameBoard[0][2] != ' ') && (gameBoard[0][2] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][0]) ) {
					win = true;
				}
			}
		return win;
	}
}
