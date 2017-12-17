package application;

import game_control.GameController;
import game_prefab.Boundary;
import game_prefab.Collider;
import game_prefab.Point2D;
import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneManager;

public class Main extends Application {
	//TEST
	Boundary b;
	Point2D a;
	
	@Override
	public void start(Stage primaryStage) {
		test();
		try {
			SceneManager.initialize(primaryStage);
			SceneManager.gotoMainMenu();
			primaryStage.setTitle("BOX HEAD");
			primaryStage.centerOnScreen();
			primaryStage.setResizable(false);
			primaryStage.sizeToScene();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() throws Exception {
		// TODO Auto-generated method stub
		GameController.stopGame();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void test() {
		b = new Collider(1,100,100,10,10);
		a = new Point2D(200,95);
		System.out.println(b.isInBoundary(a));
	}
}