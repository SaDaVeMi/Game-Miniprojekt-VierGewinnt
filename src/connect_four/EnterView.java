package connect_four;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EnterView extends BorderPane {
	private Stage stage;
	private Scene scene1;

	private VBox vBoxRight, vBoxLeft, vBoxBottom;
	private HBox centerBox;
	private VBox vBox;
	private ImageView front;
	private Image logo;
	protected Label label1, label2;
	protected TextField playerName1, playerName2;
	protected ToggleGroup toggle;
	protected RadioButton standardRadioButton, popOutRadioButton;
	protected Button startBtn, quitBtn;

	public EnterView(Stage stage) {
		this.stage = stage;

		// Control
		this.label1 = new Label("Player 1:");
		this.label2 = new Label("Player 2:");
		this.playerName1 = new TextField();
		this.playerName1.setId("P1");
		this.playerName2 = new TextField();
		this.playerName2.setId("P2");
		this.playerName1.setPromptText("Enter Name");
		this.playerName2.setPromptText("Enter Name");
		this.logo = new Image("connect_four/logo.png", 600, 300, true, false);
		this.front = new ImageView(this.logo);
		this.toggle = new ToggleGroup();
		this.standardRadioButton = new RadioButton("Standard");
		this.standardRadioButton.setToggleGroup(this.toggle);
		this.popOutRadioButton = new RadioButton("Pop Out");
		this.popOutRadioButton.setToggleGroup(this.toggle);
		this.standardRadioButton.setSelected(true);
		this.startBtn = new Button("Start");
		this.quitBtn = new Button("Exit");

		// Layout
		this.setId("root");
		GridPane gPaneTop = new GridPane();
		this.vBoxLeft = new VBox(this.label1, this.playerName1);
		this.vBoxRight = new VBox(this.label2, this.playerName2);
		gPaneTop.getColumnConstraints().add(new ColumnConstraints(180));
		gPaneTop.getColumnConstraints().add(new ColumnConstraints(180));
		gPaneTop.add(vBoxLeft, 0, 0);
		gPaneTop.add(vBoxRight, 1, 0);
		gPaneTop.getStyleClass().add("top");
		this.vBoxBottom = new VBox(this.startBtn, this.quitBtn);
		this.vBoxBottom.setId("bottom");
		this.centerBox = new HBox();
		this.centerBox.getChildren().addAll(this.standardRadioButton, this.popOutRadioButton);
		this.centerBox.getStyleClass().add("HBox");
		this.vBox = new VBox();
		this.vBox.getChildren().addAll(this.front, this.centerBox);
		this.setTop(gPaneTop);
		this.setCenter(this.vBox);
		this.setBottom(this.vBoxBottom);
	}

	public void start() {
		this.scene1 = new Scene(this);
		this.scene1.getStylesheets().add(getClass().getResource("EnterView.css").toExternalForm());
		this.stage.setScene(scene1);
		this.stage.setTitle("Start Game");
		this.stage.show();
	}

	public void stop(ActionEvent a) {
		this.stage.hide();
	}

	public TextField getPlayerName1() {
		return this.playerName1;
	}

	public TextField getPlayerName2() {
		return this.playerName2;
	}

}
