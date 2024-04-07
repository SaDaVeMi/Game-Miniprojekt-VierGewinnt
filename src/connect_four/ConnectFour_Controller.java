package connect_four;

import java.util.Optional;

import connect_four.ConnectFour_Model.PlayerColor;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.util.Pair;

public class ConnectFour_Controller {
	private EnterView enterView;
	private MainView mainView;
	private HelpView helpView;
	private ConnectFour_Model model;

	// boolean for scene switch
	private boolean isSceneStarted = false;

	// boolean for pop out
	protected boolean isPopOut = false;

	public ConnectFour_Controller(EnterView enterView, MainView mainView, HelpView helpView, ConnectFour_Model model) {
		this.enterView = enterView;
		this.mainView = mainView;
		this.helpView = helpView;
		this.model = model;

		// Animation for textfield
		this.enterView.playerName1.setOnMouseClicked(this::startTxtFieldAnimation);
		this.enterView.playerName2.setOnMouseClicked(this::startTxtFieldAnimation);

		// Click animation for Button
		this.enterView.startBtn.setOnAction(this::startButtonAnimation);
		this.enterView.quitBtn.setOnAction(this::startButtonAnimation);

		// Set Line for Players
		for (int row = 0; row < ConnectFour_Model.ROW_INDEX; row++) {
			for (int col = 0; col < ConnectFour_Model.COLUMN_INDEX; col++) {
				this.mainView.gameBorderButtons[row][col].setOnMouseEntered(this::chosenColEnter);
			}
		}
		for (int row = 0; row < ConnectFour_Model.ROW_INDEX; row++) {
			for (int col = 0; col < ConnectFour_Model.COLUMN_INDEX; col++) {
				this.mainView.gameBorderButtons[row][col].setOnMouseExited(this::chosenColExit);
			}
		}

		for (int row = 0; row < ConnectFour_Model.ROW_INDEX; row++) {
			for (int col = 0; col < ConnectFour_Model.COLUMN_INDEX; col++) {
				this.mainView.gameBorderButtons[row][col].setOnAction(this::putDisc);
			}
		}
		// SetOnAction MenuButtons
		this.mainView.helpItem.setOnAction(this::changeHelpScene);
		this.mainView.restartItem.setOnAction(this::restartGame);
		this.mainView.quitItem.setOnAction(mainView::stop);

		// from helpView back to mainView
		this.helpView.playButton.setOnAction(this::startButtonAnimation);

		// Add listener WinCount
		this.model.winCountYProperty().addListener((observable, oldValue, newValue) -> {
			this.mainView.player1Btn.setText(Integer.toString(this.model.getWinCountY()));
		});

		this.model.winCountRProperty().addListener((observable, oldValue, newValue) -> {
			this.mainView.player2Btn.setText(Integer.toString(this.model.getWinCountR()));
		});

	}

	private void startTxtFieldAnimation(MouseEvent e) {
		TextField txtField = (TextField) e.getSource();
		KeyValue kV = new KeyValue(txtField.maxWidthProperty(), 160);
		KeyFrame kF = new KeyFrame(Duration.millis(250), kV);
		Timeline tl = new Timeline(kF);
		tl.setAutoReverse(true);
		tl.play();
	}

	private void startButtonAnimation(ActionEvent e) {
		Button btn = (Button) e.getSource();
		ScaleTransition shape = new ScaleTransition(Duration.millis(400), btn);
		shape.setFromX(1.05);
		shape.setFromY(1.05);
		shape.setToX(0.85);
		shape.setToY(0.85);
		shape.setToX(1);
		shape.setToY(1);
		shape.setOnFinished((a) -> {
			if (btn == this.enterView.startBtn) {
				if (this.enterView.popOutRadioButton.isSelected()) {
					this.model.isPopOut = true;
				}
				this.changeScene(e);
			} else if (btn == this.enterView.quitBtn) {
				this.enterView.stop(e);
			} else if (btn == this.helpView.playButton) {
				this.changeBack(e);
			}
		});
		shape.play();
	}

	// Help for player which line
	public void chosenColEnter(Event event) {
		Button btn = (Button) event.getSource();
		for (int i = 0; i < ConnectFour_Model.ROW_INDEX; i++) {
			for (int j = 0; j < ConnectFour_Model.COLUMN_INDEX; j++) {
				if (btn == this.mainView.gameBorderButtons[i][j]) {
					if (this.model.nextMove == PlayerColor.R) {
						this.mainView.chooseButton[j].setId("red1-Button");
					} else if (this.model.nextMove == PlayerColor.Y) {
						this.mainView.chooseButton[j].setId("yellow1-Button");
					}
				}
			}
		}
	}

	public void chosenColExit(MouseEvent event) {
		Button btn = (Button) event.getSource();
		for (int i = 0; i < ConnectFour_Model.ROW_INDEX; i++) {
			for (int j = 0; j < ConnectFour_Model.COLUMN_INDEX; j++) {
				if (btn == this.mainView.gameBorderButtons[i][j]) {
					this.mainView.chooseButton[j].setId("Exit-button");
				}
			}
		}
	}

	public void putDisc(Event event) {
		Button btn = (Button) event.getSource();
		int col = -1;
		int row = -1;
		// Searching for column from event
		for (int i = 0; i < ConnectFour_Model.ROW_INDEX; i++) {
			for (int j = 0; j < ConnectFour_Model.COLUMN_INDEX; j++) {
				if (btn == this.mainView.gameBorderButtons[i][j]) {
					col = j;
				}
			}
		}
		// Searching for available row to put the disc in the column
		if (this.mainView.putRadioButton.isSelected()) {
			row = this.model.findRow(col);
			if (this.model.makeMove(col)) {
				if (this.model.board[row][col] == PlayerColor.R) {
					this.mainView.gameBorderButtons[row][col].getStyleClass().add("red-Button");
					this.chosenColEnter(event);
				} else {
					this.mainView.gameBorderButtons[row][col].getStyleClass().add("yellow-Button");
					this.chosenColEnter(event);
				}
				this.dialog();
			}
		} // Pop out is selected
		else if (this.mainView.popRadioButton.isSelected()) {
			// Error if invalid move
			if (this.model.popMakeMove(col)) {
				this.colorChange(col);
				this.mainView.putRadioButton.setSelected(true);
			}else {
				this.errorDialog();
			}
			this.dialog();
		}
	}

	// Disc change color after Pop out
	private void colorChange(int column) {
		for (int row = 0; row < 6; row++) {
			if (this.model.board[row][column] == PlayerColor.R) {
				this.mainView.gameBorderButtons[row][column].getStyleClass().removeAll("red-Button", "yellow-Button");
				this.mainView.gameBorderButtons[row][column].getStyleClass().add("red-Button");
			} else if (this.model.board[row][column] == PlayerColor.Y) {
				this.mainView.gameBorderButtons[row][column].getStyleClass().removeAll("red-Button", "yellow-Button");
				this.mainView.gameBorderButtons[row][column].getStyleClass().add("yellow-Button");
			} else {
				this.mainView.gameBorderButtons[row][column].getStyleClass().removeAll("red-Button", "yellow-Button");
			}
		}
	}

	private void errorDialog() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Invalid move");
		alert.setContentText("Please choose a disc of your color!");
		alert.show();
	}

	private void dialog() {
		if (this.model.getWinner() != null) {
			this.highlightWinningLine();
			// Custom Dialog
			this.mainView.showWinner.setTitle("Winner");
			this.mainView.showWinner
					.setHeaderText("The winner is " + (model.getWinner() == PlayerColor.Y ? "Yellow" : "Red"));
			this.showDialog();
		} else if (this.model.checkDraw()) {
			this.mainView.showWinner.setTitle("Draw");
			this.mainView.showWinner.setHeaderText("Draw!");
			this.showDialog();
		}
	}

	private void showDialog() {
		Optional<ButtonType> result = this.mainView.showWinner.showAndWait();
		if (result.isPresent() && result.get() == this.mainView.restart) {
			this.restartGame();
		} else if (result.isPresent() && result.get() == this.mainView.quit) {
			this.mainView.stop();
		}
	}

	// Restart Game and remove all StyleClass
	private void restartGame() {
		for (int row = 0; row < ConnectFour_Model.ROW_INDEX; row++) {
			for (int col = 0; col < ConnectFour_Model.COLUMN_INDEX; col++) {
				this.mainView.gameBorderButtons[row][col].getStyleClass().removeAll("red-Button", "yellow-Button", "winning-Line");
			}
		}
		this.model.restartGame();
	}

	private void restartGame(ActionEvent event) {
		for (int row = 0; row < ConnectFour_Model.ROW_INDEX; row++) {
			for (int col = 0; col < ConnectFour_Model.COLUMN_INDEX; col++) {
				this.mainView.gameBorderButtons[row][col].getStyleClass().removeAll("red-Button", "yellow-Button", "winning-Line");
			}
		}
		this.model.restartGame();
	}

	// Change Scene
	public void changeScene(ActionEvent e) {
		this.mainView.start();
		this.saveName();
	}

	public void changeHelpScene(ActionEvent e) {
		if (!isSceneStarted) {
			this.helpView.start();
			this.isSceneStarted = true;
		} else {
			this.helpView.stage.setScene(this.helpView.getScene());
		}
	}

	public void changeBack(ActionEvent e) {
		this.mainView.stage.setScene(this.mainView.getScene());
	}

	public void saveName() {
		String playerName;
		if (this.enterView.getPlayerName1().getLength() == 0) {
			playerName = "Player 1";
			this.mainView.setPlayerOneLabel(playerName);
		} else {
			playerName = this.enterView.playerName1.getText();
			this.mainView.setPlayerOneLabel(playerName);
		}
		if (this.enterView.getPlayerName2().getLength() == 0) {
			playerName = "Player 2";
			this.mainView.setPlayerTwoLabel(playerName);
		} else {
			playerName = this.enterView.playerName2.getText();
			this.mainView.setPlayerTwoLabel(playerName);
		}
	}

	private void highlightWinningLine() {
		for (Pair<Integer, Integer> a : this.model.winningLine)
			this.mainView.gameBorderButtons[a.getKey()][a.getValue()].getStyleClass().add("winning-Line");
	}
}
