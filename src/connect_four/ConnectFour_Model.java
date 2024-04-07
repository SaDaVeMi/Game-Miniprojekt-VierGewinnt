package connect_four;

import java.util.ArrayList;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Pair;

public class ConnectFour_Model {
	public static final int ROW_INDEX = 6;
	public static final int COLUMN_INDEX = 7;

	public enum PlayerColor {Y, R}; // Yellow and Red

	protected PlayerColor nextMove = PlayerColor.Y;
	private PlayerColor winner = null;
	protected PlayerColor[][] board = new PlayerColor[ROW_INDEX][COLUMN_INDEX];

	protected ArrayList<Pair<Integer, Integer>> winningLine = new ArrayList<Pair<Integer, Integer>>();

	private SimpleIntegerProperty winCountY = new SimpleIntegerProperty(0);
	private SimpleIntegerProperty winCountR = new SimpleIntegerProperty(0);

	public int getWinCountY() {
		return winCountY.get();
	}

	public void setWinCountY(int value) {
		winCountY.set(value);
	}

	public SimpleIntegerProperty winCountYProperty() {
		return winCountY;
	}

	public int getWinCountR() {
		return winCountR.get();
	}

	public void setWinCountR(int value) {
		winCountR.set(value);
	}

	public SimpleIntegerProperty winCountRProperty() {
		return winCountR;
	}

	protected boolean isPopOut = false;

	public boolean makeMove(int column) {
		boolean moveMade = false;
		int row = findRow(column);
		if (winner == null && row != -1) {
			moveMade = true;
			board[row][column] = nextMove;
			nextMove = (nextMove == PlayerColor.Y) ? PlayerColor.R : PlayerColor.Y;
		}
		checkWinner();
		return moveMade;
	}

	public boolean popMakeMove(int column) {
		boolean moveMade = false;
		int row = 5;
		int highestRow = 0;
		if (winner == null && nextMove == board[row][column]) {
			for (int i = 5; i > 0; i--) {
				board[i][column] = board[i - 1][column];
			}
			if (board[highestRow][column] != null) {
				board[highestRow][column] = null;
			}
			nextMove = (nextMove == PlayerColor.Y) ? PlayerColor.R : PlayerColor.Y;
			moveMade = true;
		}
		checkWinner();
		return moveMade;
	}

	public int findRow(int column) {
		int row = -1; // start point in row where you can put the token
		// Checking for available space in row for the selected column
		for (int i = 5; i > -1; i--) {
			if (board[i][column] == null) {
				row = i;
				break;
			}
		}
		return row;
	}

	private void checkWinner() {

		if (winner == null) {

			// Checking Row
			for (int row = 0; row < ROW_INDEX; row++) {
				int rowWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int col = 0; col < COLUMN_INDEX - 1; col++) {
					if (board[row][col] != null && board[row][col] == board[row][col + 1] && rowWinCount != 3) {
						rowWinCount++;
						winnerY = rowWinCount == 3 && board[row][col] == PlayerColor.Y;
						winnerR = rowWinCount == 3 && board[row][col] == PlayerColor.R;
						// Save position of winning column in array
						if (rowWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row, col + 1));
							this.winningLine.add(new Pair<Integer, Integer>(row, col));
							this.winningLine.add(new Pair<Integer, Integer>(row, col - 1));
							this.winningLine.add(new Pair<Integer, Integer>(row, col - 2));
						}
					} else if (rowWinCount != 3)
						rowWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			// Checking Column
			for (int col = 0; col < COLUMN_INDEX; col++) {
				int columnWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int row = 0; row < ROW_INDEX - 1; row++) {
					if (board[row][col] != null && board[row][col] == board[row + 1][col] && columnWinCount != 3) {
						columnWinCount++;
						winnerY = columnWinCount == 3 && board[row][col] == PlayerColor.Y;
						winnerR = columnWinCount == 3 && board[row][col] == PlayerColor.R;
						// Save position of winning column in array
						if (columnWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row + 1, col));
							this.winningLine.add(new Pair<Integer, Integer>(row, col));
							this.winningLine.add(new Pair<Integer, Integer>(row - 1, col));
							this.winningLine.add(new Pair<Integer, Integer>(row - 2, col));
						}
					} else if (columnWinCount != 3)
						columnWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			// Checking diagonal top left to bottom right

			// column start point at 0
			for (int row = 0; row < ROW_INDEX - 3; row++) {
				int diagonalWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int col = 0; col < COLUMN_INDEX - 2 - row; col++) {
					if (board[row + col][col] != null && board[row + col][col] == board[row + col + 1][col + 1]
							&& diagonalWinCount != 3) {
						diagonalWinCount++;
						winnerY = diagonalWinCount == 3 && board[row + col][col] == PlayerColor.Y;
						winnerR = diagonalWinCount == 3 && board[row + col][col] == PlayerColor.R;
						if (diagonalWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row + col + 1, col + 1));
							this.winningLine.add(new Pair<Integer, Integer>(row + col, col));
							this.winningLine.add(new Pair<Integer, Integer>(row + col - 1, col - 1));
							this.winningLine.add(new Pair<Integer, Integer>(row + col - 2, col - 2));
						}
					} else if (diagonalWinCount != 3)
						diagonalWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			// row start point at 0
			for (int col = 1; col < COLUMN_INDEX - 3; col++) {
				int diagonalWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int row = 0; row < ROW_INDEX - col; row++) {
					if (board[row][col + row] != null && board[row][col + row] == board[row + 1][col + row + 1]
							&& diagonalWinCount != 3) {
						diagonalWinCount++;
						winnerY = diagonalWinCount == 3 && board[row][col + row] == PlayerColor.Y;
						winnerR = diagonalWinCount == 3 && board[row][col + row] == PlayerColor.R;
						if (diagonalWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row + 1, col + row + 1));
							this.winningLine.add(new Pair<Integer, Integer>(row, col + row));
							this.winningLine.add(new Pair<Integer, Integer>(row - 1, col + row - 1));
							this.winningLine.add(new Pair<Integer, Integer>(row - 2, col + row - 2));
						}
					} else if (diagonalWinCount != 3)
						diagonalWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			// Checking diagonal bottom left to top right

			// column start point at 0
			for (int row = 5; row > ROW_INDEX - 4; row--) {
				int diagonalWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int col = 0; col < row; col++) {
					if (board[row - col][col] != null && board[row - col][col] == board[row - col - 1][col + 1]
							&& diagonalWinCount != 3) {
						diagonalWinCount++;
						winnerY = diagonalWinCount == 3 && board[row - col][col] == PlayerColor.Y;
						winnerR = diagonalWinCount == 3 && board[row - col][col] == PlayerColor.R;
						if (diagonalWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row - col - 1, col + 1));
							this.winningLine.add(new Pair<Integer, Integer>(row - col, col));
							this.winningLine.add(new Pair<Integer, Integer>(row - col + 1, col - 1));
							this.winningLine.add(new Pair<Integer, Integer>(row - col + 2, col - 2));
						}
					} else if (diagonalWinCount != 3)
						diagonalWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			// row start point at 0
			for (int col = 1; col < COLUMN_INDEX - 3; col++) {
				int diagonalWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int row = 5; row > col - 1; row--) {
					if (board[row][col - row + 5] != null
							&& board[row][col - row + 5] == board[row - 1][col - row + 5 + 1]
							&& diagonalWinCount != 3) {
						diagonalWinCount++;
						winnerY = diagonalWinCount == 3 && board[row][col - row + 5] == PlayerColor.Y;
						winnerR = diagonalWinCount == 3 && board[row][col - row + 5] == PlayerColor.R;
						if (diagonalWinCount == 3) {
							this.winningLine.add(new Pair<Integer, Integer>(row - 1, col - row + 6));
							this.winningLine.add(new Pair<Integer, Integer>(row, col - row + 5));
							this.winningLine.add(new Pair<Integer, Integer>(row + 1, col - row + 4));
							this.winningLine.add(new Pair<Integer, Integer>(row + 2, col - row + 3));
						}
					} else if (diagonalWinCount != 3)
						diagonalWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;
				}
				if (winnerR) {
					winner = PlayerColor.R;
				}
			}

			this.countWinner(winner);
		}

	}

	public boolean checkDraw() {
		boolean empty = true;
		int topRow = 0;
		for (int col = 0; col < COLUMN_INDEX; col++) {
			if (this.board[topRow][col] == null)
				empty = false;
		}
		return empty;
	}

	private void countWinner(PlayerColor winner) {
		if (winner == PlayerColor.Y) {
			this.winCountY.set(winCountY.get() + 1);
		} else if (winner == PlayerColor.R) {
			this.winCountR.set(winCountR.get() + 1);
		}
	}

	public PlayerColor getWinner() {
		return this.winner;
	}

	public void restartGame() {
		this.winner = null;
		this.winningLine.clear();
		for (int row = 0; row < ROW_INDEX; row++) {
			for (int col = 0; col < COLUMN_INDEX; col++) {
				this.board[row][col] = null;
			}
		}

	}

}
