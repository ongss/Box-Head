package application;

import java.util.Vector;


import game_control.GameController;
import game_physic.Boundary;
import game_physic.Collider;
import game_physic.Edge;
import game_physic.Point2D;
import game_physic.Vector2D;
import javafx.application.Application;
import javafx.stage.Stage;
import scene.SceneManager;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
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

}