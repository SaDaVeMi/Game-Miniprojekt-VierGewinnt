# Miniproject "ConnectFour"

The aim of this project is to write the game "Connect Four" into codes.
For this goal we used the MVC Model.

# Introduction
"Connect Four" is a worldwide known game, which can be played by children and adults.
Moreover, it is very popular and has easy rules for players to understand. 
Although the principles of this game are very simple, it might be difficult to programm it.
So we decided to write this game into codes.

# Features
This projects has an elegant css design and it include also a guide for beginners.
Moreover its codes are written simple and well commented for a fast understandig.


*  Two people taking turns on the same computer is possible
*  It changes utomatically alternate colors 
*  Illegal moves are impossible
*  It detect and announce the winner or draw
*  It has a start, main and help view
*  It is possible to play the pop out version of this game
*  It has an scrollbar
*  All buttons works(restart, quit)


# Code Example

How to check a row into codes.

```
for (int i = 0; i < ROW_INDEX; i++) {
				int rowWinCount = 0;
				boolean winnerY = false;
				boolean winnerR = false;
				for (int j = 0; j < COLUMN_INDEX - 1; j++) {
					if (board[i][j] != null && board[i][j] == board[i][j + 1] && rowWinCount != 3) {
						rowWinCount++;
						winnerY = rowWinCount == 3 && board[i][j] == PlayerColor.Y;
						winnerR = rowWinCount == 3 && board[i][j] == PlayerColor.R;
						// Save position of winning column in array
						if (rowWinCount == 3) {this.winningLine.add(new Pair<Integer, Integer>(i, j + 1));
						this.winningLine.add(new Pair<Integer, Integer>(i, j)); this.winningLine.add(new Pair<Integer, Integer>(i, j - 1));
						this.winningLine.add(new Pair<Integer, Integer>(i, j - 2));}
					}else	if (rowWinCount != 3) rowWinCount = 0;
				}
				if (winnerY) {
					winner = PlayerColor.Y;}
				if (winnerR) {
					winner = PlayerColor.R;}
			}
```

# Screenshots
Logo:
https://gitlab.fhnw.ch/saverio.damiani/Game-Miniprojekt-Viergewinnt/blob/master/src/connect_four/logo.png

Gameboard:
https://gitlab.fhnw.ch/saverio.damiani/Game-Miniprojekt-Viergewinnt/blob/master/src/connect_four/diagonal.PNG


# API

*  javafx.application.Application;
*  javafx.stage.Stage;
*  javafx.event.ActionEvent;
*  javafx.scene.Scene;
*  javafx.scene.control.Button;
*  javafx.scene.control.ButtonBar.ButtonData;
*  javafx.scene.control.ButtonType;
*  javafx.scene.control.Dialog;
*  javafx.scene.control.Label;
*  javafx.scene.control.Menu;
*  javafx.scene.control.MenuBar;
*  javafx.scene.control.MenuItem;
*  javafx.scene.image.Image;
*  javafx.scene.image.ImageView;
*  javafx.scene.layout.BorderPane;
*  javafx.scene.layout.GridPane;
*  javafx.scene.layout.HBox;
*  javafx.scene.layout.VBox;
*  javafx.stage.Stage;
*  javafx.animation.KeyFrame;
*  javafx.animation.KeyValue;
*  javafx.animation.ScaleTransition;
*  javafx.animation.Timeline;
*  javafx.event.ActionEvent;
*  javafx.event.Event;
*  javafx.scene.control.Button;
*  javafx.scene.control.ButtonType;
*  javafx.scene.control.Dialog;
*  javafx.scene.control.TextField;
*  javafx.scene.input.MouseEvent;
*  javafx.util.Duration;
*  javafx.util.Pair;
*   java.util.ArrayList;
*  javafx.beans.property.SimpleIntegerProperty;
*  javafx.util.Pair;







