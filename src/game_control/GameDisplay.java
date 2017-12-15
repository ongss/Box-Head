package game_control;

import game_model.GameModel;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import scene.SceneManager;
import timer.GameTimer;

public class GameDisplay extends Canvas {
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;

	private static final Font TEXT_FONT = new Font("Monospace", 80);
	private static final Font SCORE_TIME_FONT = new Font("Monospace", 30);

	private GameTimer timer;
	private GameModel model;
	private Thread gameAnimation;
	private GraphicsContext gc;
	private boolean isAnimationRunning;
	
	public GameDisplay(GameModel model,GameTimer timer){
		this.setHeight(SceneManager.SCENE_HEIGHT);
		this.setWidth(SceneManager.SCENE_WIDTH);
		this.gc = this.getGraphicsContext2D();
		this.isAnimationRunning = false;
		this.model = model;
		this.timer = timer;
		
	}
	
	public void startAnimation() {
		gameAnimation = new Thread(this::animationLoop, "Game Animation Thread");
		isAnimationRunning = true;
		gameAnimation.start();
	}

	
	public void stopAnimation() {
		isAnimationRunning = false;
	}
	
	private void animationLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isAnimationRunning) {
			long now = System.nanoTime();
			if (now - lastLoopStartTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;

				updateAnimation(now);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void updateAnimation(long now) {
		// TODO: fill code
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, SceneManager.SCENE_WIDTH, SceneManager.SCENE_HEIGHT);
		model.c.draw(gc);
		model.c2.draw(gc);
	}
	
	
}
