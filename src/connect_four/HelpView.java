package connect_four;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelpView extends ScrollPane {
	// Logo
	private VBox helpVBox;
	private Image logo;
	private ImageView logoImageView;

	// Label
	private Label title, horizontalLabel, verticalLabel, diagonalLabel;
	private Label explanationLabel, explanationLabel2, funLabel;

	// Images for explanation
	private Image horizontalImage, verticalImage, diagonalImage;
	private ImageView horizontalView, verticalView, diagonalView;
	private GridPane imagePane;
	protected Button playButton;

	// Pop out

	private Label popLabel, popExLabel, popToggleLabel, popChooseLabel, popClickLabel;
	private Image choseBImage, popOutImage, popOutAfterImage;
	private ImageView choseBImageView, popOutImageView, popOutAfterImageView;
	private GridPane imagePane2;

	private Scene scene3;
	protected Stage stage;

	public HelpView(Stage stage) {
		this.stage = stage;
		this.title = new Label("Help");
		this.title.setId("title");
		this.explanationLabel = new Label(
				"The aim of this game is to put four of your token next to eachother. Put your token either:");

		this.logo = new Image("connect_four/logo.png", 500, 150, true, false);
		this.logoImageView = new ImageView(this.logo);

		this.imagePane = new GridPane();
		this.horizontalLabel = new Label("horizontal");
		this.horizontalImage = new Image("connect_four/horizontal.PNG", 100, 100, true, false);
		this.horizontalView = new ImageView(this.horizontalImage);
		this.imagePane.add(this.horizontalLabel, 0, 0);
		this.imagePane.add(this.horizontalView, 1, 0);
		this.verticalLabel = new Label("vertical");
		this.verticalImage = new Image("connect_four/vertical.PNG", 100, 100, true, false);
		this.verticalView = new ImageView(this.verticalImage);
		this.imagePane.add(this.verticalLabel, 0, 1);
		this.imagePane.add(this.verticalView, 1, 1);
		this.diagonalLabel = new Label("diagonal");
		this.diagonalImage = new Image("connect_four/diagonal.PNG", 100, 100, true, false);
		this.diagonalView = new ImageView(this.diagonalImage);
		this.imagePane.add(this.diagonalLabel, 0, 2);
		this.imagePane.add(this.diagonalView, 1, 2);
		this.imagePane.setId("ImagePaneStyler");

		this.explanationLabel2 = new Label("before your opponents does to win this game!");

		// Pop out
		this.popLabel = new Label("Pop Out");
		this.popLabel.setId("fun-label");
		this.popExLabel = new Label(
				"In the version Pop out you can drop your bottom token out of the gameboard instead of dropping one.");

		this.imagePane2 = new GridPane();
		this.popToggleLabel = new Label("Choose the pop out button");
		this.choseBImage = new Image("connect_four/popOut.PNG", 100, 100, true, false);
		this.choseBImageView = new ImageView(this.choseBImage);
		this.imagePane2.add(this.popToggleLabel, 0, 0);
		this.imagePane2.add(this.choseBImageView, 1, 0);

		this.popChooseLabel = new Label("Click on a token of yours in the bottom line ");
		this.popOutImage = new Image("connect_four/popOne.PNG", 100, 100, true, false);
		this.popOutImageView = new ImageView(this.popOutImage);
		this.imagePane2.add(this.popChooseLabel, 0, 1);
		this.imagePane2.add(this.popOutImageView, 1, 1);

		this.popClickLabel = new Label("to pop it out the gameboard");
		this.popOutAfterImage = new Image("connect_four/popTwo.PNG", 100, 100, true, false);
		this.popOutAfterImageView = new ImageView(this.popOutAfterImage);
		this.imagePane2.add(this.popClickLabel, 0, 2);
		this.imagePane2.add(this.popOutAfterImageView, 1, 2);
		this.imagePane2.setId("ImagePaneStyler");

		this.funLabel = new Label("HAVE FUN!");
		this.funLabel.setId("fun-label");
		this.playButton = new Button("PLAY!");

		// Elements into VBox
		this.helpVBox = new VBox();
		this.helpVBox.getChildren().addAll(this.logoImageView, this.title, this.explanationLabel, this.imagePane,
				this.explanationLabel2, this.popLabel, this.popExLabel, this.imagePane2, this.funLabel,
				this.playButton);
		this.helpVBox.getStyleClass().add("VBox");

		this.setContent(this.helpVBox);
		this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
	}

	public void start() {
		this.scene3 = new Scene(this, 700, 700);
		scene3.getStylesheets().add(getClass().getResource("HelpView.css").toExternalForm());
		stage.setScene(scene3);
		stage.setTitle("Connect Four");
		stage.show();
	}

}
