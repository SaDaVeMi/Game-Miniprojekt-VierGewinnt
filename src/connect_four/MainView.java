package connect_four;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends BorderPane {
	private Scene scene2;
	private ConnectFour_Model model;

	// Controllelements for Menubar
	private Menu menu;
	protected MenuItem restartItem, helpItem, quitItem;
	private MenuBar menuBar;
	protected Stage stage;

	// TopBox
	private VBox topBox;

	// Controllelements for Logo
	private Image logo;
	private ImageView logoImageView;
	private VBox centerVBox;

	// Controllelements for PlayerView
	private Label playerOneLabel, playerTwoLabel;
	private HBox bottomHBox;
	private HBox player1HBox;
	private HBox player2HBox;
	protected Button player1Btn;
	protected Button player2Btn;

	// Controllelements for GameBorder
	protected Button chooseButton[];
	protected Button gameBorderButtons[][];
	protected GridPane chooseBorder;
	private GridPane gameBorder;

	// Create custom Dialog
	protected Dialog<ButtonType> showWinner;
	protected ButtonType restart;
	protected ButtonType quit;

	//Pop Out elements
	protected boolean isPopOut;
	protected ToggleGroup toggle;
	protected RadioButton putRadioButton, popRadioButton;
	protected HBox toggleHBox;

	public MainView(Stage stage, ConnectFour_Controller controller, ConnectFour_Model model) {
		this.stage = stage;
		this.setId("root");
		this.model = model;

		this.initGUI();

		// Create custom dialog
		this.showWinner = new Dialog<>();
		this.restart = new ButtonType("Play again");
		this.quit = new ButtonType("Quit");
		showWinner.getDialogPane().getButtonTypes().addAll(restart, quit);

	}

	private void initGUI() {
		// menuItems
		this.helpItem = new MenuItem("Help");
		this.restartItem = new MenuItem("Restart");
		this.quitItem = new MenuItem("Quit");
		// MenuItems into menu
		this.menu = new Menu("Menu");
		this.menu.getItems().addAll(this.helpItem, this.restartItem, this.quitItem);
		// Menu into menuBar
		this.menuBar = new MenuBar();
		this.menuBar.getMenus().add(this.menu);

		// Logo
		this.logo = new Image("connect_four/logo.png", 500, 150, true, false);
		this.logoImageView = new ImageView(this.logo);

		// PlayerBox
		this.playerOneLabel = new Label("Player 1");
		this.playerOneLabel.setId("player-label");
		this.playerTwoLabel = new Label("Player 2");
		this.playerTwoLabel.setId("player-label");
		this.player1Btn = new Button("0");
		this.player1Btn.getStyleClass().add("yellow-Button");
		this.player2Btn = new Button("0");
		this.player2Btn.getStyleClass().add("red-Button");
		this.player1HBox = new HBox(this.player1Btn, this.playerOneLabel);
		this.player2HBox = new HBox(this.playerTwoLabel, this.player2Btn);
		this.bottomHBox = new HBox();
		this.bottomHBox.getChildren().addAll(this.player1HBox, this.player2HBox);
		this.bottomHBox.setId("bottomHBox");

		// Selection for Players
		this.chooseBorder = new GridPane();
		this.chooseButton = new Button[ConnectFour_Model.COLUMN_INDEX];
		for (int i = 0; i < this.chooseButton.length; i++) {
			this.chooseButton[i] = new Button();
			this.chooseButton[i].setId("border-button");
			this.chooseBorder.add(this.chooseButton[i], i, 0);
		}

		// GameBorder
		this.gameBorder = new GridPane();
		this.gameBorderButtons = new Button[ConnectFour_Model.ROW_INDEX][ConnectFour_Model.COLUMN_INDEX];
		for (int i = 0; i < this.gameBorderButtons.length; i++) {
			for (int a = 0; a < ConnectFour_Model.COLUMN_INDEX; a++) {
				this.gameBorderButtons[i][a] = new Button(" ");
				this.gameBorder.add(this.gameBorderButtons[i][a], a, i);
			}
		}

		// GameBorder put together
		this.centerVBox = new VBox();
		this.centerVBox.setId("centerVBox");
		this.gameBorder.setMinHeight(375); // minHeight Property doesn't work in CSS for GridPane Layout
		this.centerVBox.getChildren().addAll(this.logoImageView, this.chooseBorder, this.gameBorder, this.bottomHBox);
		this.chooseBorder.getStyleClass().add("chooseBorder");
		this.gameBorder.getStyleClass().add("GameBorder");

		this.toggle = new ToggleGroup();
		this.putRadioButton = new RadioButton("Drop");
		this.popRadioButton = new RadioButton("Pop out");
		this.putRadioButton.setToggleGroup(this.toggle);
		this.putRadioButton.setSelected(true);
		this.popRadioButton.setToggleGroup(this.toggle);

		this.toggleHBox = new HBox();
		this.toggleHBox.getChildren().addAll(this.putRadioButton, this.popRadioButton);
		this.toggleHBox.getStyleClass().add("toggleHBox");
		this.setBottom(this.toggleHBox);
		this.toggleHBox.setVisible(false);

		// Controlls into BorderPane
		this.setTop(this.menuBar);
		this.setCenter(this.centerVBox);
	}

	public void setPlayerOneLabel(String playerName) {
		this.playerOneLabel.setText(playerName);
	}

	public void setPlayerTwoLabel(String playerName) {
		this.playerTwoLabel.setText(playerName);
	}

	public void start() {
		this.scene2 = new Scene(this, 550, 710);
		scene2.getStylesheets().add(getClass().getResource("MainViewStyler.css").toExternalForm());
		stage.setScene(scene2);
		stage.setTitle("Connect Four");
		
		//Pop Out version only opens when chosen
		this.toggleHBox.setManaged(this.model.isPopOut);

		this.toggleHBox.setVisible(this.model.isPopOut);

		stage.show();

	}

	public void stop(ActionEvent a) {
		this.stage.hide();
	}

	public void stop() {
		this.stage.hide();
	}
}
