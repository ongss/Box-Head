package scene;

import game_control.GameController;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scene_object.OptionTextSelector;

public class GameOver extends Canvas{
	
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font MENU_FONT = new Font("Monospace", 40);
	private OptionTextSelector option;
	private GraphicsContext gc = this.getGraphicsContext2D();
	
	public GameOver() {
		
		super(SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		option = new OptionTextSelector(2,SceneManager.SCENE_WIDTH / 2,SceneManager.SCENE_HEIGHT / 2);
		option.addOption("restart");
		option.addOption("main menu");
		
		this.draw();
		
		this.addKeyEventHandler();
	}
	
	private void addKeyEventHandler() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.UP) {
					option.moveUp();
					draw();
				}
				if(event.getCode() == KeyCode.DOWN) {
					option.moveDown();
					draw();
				}
				if(event.getCode() == KeyCode.ENTER) {
					if(option.getSelected() == 0) {
						GameController.newGame();
						System.out.println("game is start!");
					}
					else {
						SceneManager.gotoMainMenu();
						System.out.println("coming soon");
					}
				}
			}
		});
	}
	
	void draw() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(TITLE_FONT);
		gc.fillText("Game Over", SceneManager.SCENE_WIDTH / 2, SceneManager.SCENE_HEIGHT / 4);
		gc.setFont(MENU_FONT);
		option.draw(gc);
	}
}
