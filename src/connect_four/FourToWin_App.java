package connect_four;

import javafx.application.Application;
import javafx.stage.Stage;

public class FourToWin_App extends Application {
	private EnterView enterView;
	private MainView mainView;
	private HelpView helpView;
	private ConnectFour_Model model;
	private ConnectFour_Controller controller;

	public void start(Stage stage) {
		this.model = new ConnectFour_Model();
		this.mainView = new MainView(stage, this.controller, this.model);
		this.enterView = new EnterView(stage);
		this.helpView = new HelpView(stage);
		this.controller = new ConnectFour_Controller(this.enterView, this.mainView, this.helpView, this.model);

		this.enterView.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
