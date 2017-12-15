package scene;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public final class SceneManager {

	private static Stage primaryStage;
	private static Canvas mainMenuCanvas = new MainMenu();
	private static Scene mainMenuScene = new Scene(new Pane(mainMenuCanvas));
	public static final int SCENE_WIDTH = 1000;
	public static final int SCENE_HEIGHT = 750;

	public static void initialize(Stage stage) {
		primaryStage = stage;
		primaryStage.show();
	}

	public static void gotoMainMenu() {
		//TODO Fill Code
		primaryStage.setScene(mainMenuScene);
		mainMenuCanvas.requestFocus();
	}

	public static void gotoSceneOf(Canvas canvas) {
		//TODO Fill Code
		Scene scene = new Scene(new Pane(canvas));
		primaryStage.setScene(scene);
		canvas.requestFocus();
	}
	
	public static void gotoSceneOf(GridPane gridPane) {
		Scene scene = new Scene(gridPane);
		primaryStage.setScene(scene);
		gridPane.requestFocus();
	}
}
