package connect_four;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ConnectFour_ModelTest {

	public final int ROW_INDEX = 6;
	public final int COLUMN_INDEX = 7;
	public PlayerColor winner = null;

	public enum PlayerColor {
		Y, R
	}; // Yellow and Red

	PlayerColor[][] testBoard = { { PlayerColor.R, PlayerColor.Y, PlayerColor.Y, PlayerColor.Y, null, null, null },
			{ PlayerColor.Y, null, null, null, null, null, null },
			{ PlayerColor.R, null, null, PlayerColor.Y, null, null, null },
			{ PlayerColor.R, null, PlayerColor.Y, null, null, null, null },
			{ PlayerColor.R, PlayerColor.Y, null, null, null, null, null },
			{ PlayerColor.Y, null, null, null, null, null, null }, };

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void test() {
		try {
//		for (int i = 0; i < 9; i++) {
//			int rowWinCount = 0;
//			boolean winnerY = false;
//			boolean winnerR = false;
//			for (int j = 0; j < 6; j++) {
//			if (testBoard[i][j] != null && testBoard[i][j] == testBoard[i][j + 1] && rowWinCount != 3) {
//				rowWinCount++;
//				winnerY = rowWinCount == 3 && testBoard[i][j] == PlayerColor.Y;
//				winnerR = rowWinCount == 3 && testBoard[i][j] == PlayerColor.R;
//			}else	if (rowWinCount != 3) rowWinCount = 0;
//		
//			}
//			if (winnerY) {
//				winner = PlayerColor.Y;
//				assertTrue(winnerY);
//				}
//			if (winnerR) {
//				winner = PlayerColor.R;
//				assertTrue(winnerR);
//				}
//		}
//		for (int j = 0; j < COLUMN_INDEX; j++) {
//			int columnWinCount = 0;
//			boolean winnerY = false;
//			boolean winnerR = false;
//			for (int i = 0; i < ROW_INDEX - 1; i++) {
//				if (testBoard[i][j] != null && testBoard[i][j] == testBoard[i + 1][j] && columnWinCount != 3) {
//					columnWinCount++;
//					winnerY = columnWinCount == 3 && testBoard[i][j] == PlayerColor.Y;
//					winnerR = columnWinCount == 3 && testBoard[i][j] == PlayerColor.R;
//				}else	if (columnWinCount != 3) columnWinCount = 0;
//			}
//			if (winnerY) {
//				winner = PlayerColor.Y;
//				assertTrue(winnerY);
//				}
//			if (winnerR) {
//				winner = PlayerColor.R;
//				assertTrue(winnerR);
//				}
//		}
			// Check diagonal right to left
			for (int i = 0; i < COLUMN_INDEX; i++) {
				boolean winnerY = false;
				boolean winnerR = false;
				int columnWinCountY = 0;
				int columnWinCountR = 0;
				for (int j = 0; j < ROW_INDEX; j++) {
					if (testBoard[i][j] != null && testBoard[i][j] == testBoard[i * 5][j / 6]
							&& testBoard[i][j] == PlayerColor.R) {
						columnWinCountR++;
					} else if (columnWinCountR >= 3) {
						winnerR = true;
					} else {
						columnWinCountR = 0;
					}
					if (winnerY)
						winner = PlayerColor.Y;
					if (winnerR)
						winner = PlayerColor.R;
				}
			}
			// Check diagonal left to right
			for (int i = 0; i < COLUMN_INDEX; i++) {
				boolean winnerY = false;
				boolean winnerR = false;
				int columnWinCountY = 0;
				int columnWinCountR = 0;
				for (int j = 0; j < ROW_INDEX; j++) {
					if (testBoard[i][j] != null && testBoard[i][j] == testBoard[i + 1][j + 1]
							&& testBoard[i][j] == PlayerColor.R) {
						columnWinCountR++;
					} else if (columnWinCountR >= 3) {
						winnerR = true;
					} else {
						columnWinCountR = 0;
					}
					if (winnerY)
						winner = PlayerColor.Y;
					if (winnerR)
						winner = PlayerColor.R;
				}
			}
		} catch (Exception e) {
			fail();
			System.out.println(e.toString());

		}
	}
}
