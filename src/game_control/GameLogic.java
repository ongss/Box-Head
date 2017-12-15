package game_control;

import game_model.GameModel;
import game_prefab.Collider;
import game_prefab.Force2D;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import timer.GameTimer;
import timer.TimerThread;

public class GameLogic {
	private static final int FPS = 60;
	private static final long LOOP_TIME = 1000000000 / FPS;

	private GameTimer timer;
	private GameModel model;
	private GameDisplay gameDisplay;
	private boolean isGameRunning;
	
	
	public GameLogic(GameModel model,GameDisplay gameDisplay,GameTimer timer){
		this.model = model;
		this.gameDisplay = gameDisplay;
		this.timer = timer;
		isGameRunning = false;
	}

	public void startGame() {
		this.isGameRunning = true;
		// TODO: tell canvas to prepare something
		new Thread(this::gameLoop, "Game Loop Thread").start();
		
	}

	public void stopGame() {
		this.isGameRunning = false;
	}
	
	private void gameLoop() {
		long lastLoopStartTime = System.nanoTime();
		while (isGameRunning) {
			long elapsedTime = System.nanoTime() - lastLoopStartTime;
			if (elapsedTime >= LOOP_TIME) {
				lastLoopStartTime += LOOP_TIME;
				
				updateGame(elapsedTime);
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void updateGame(long elapsedTime) {
		// TODO fill code
		addKeyEventHandler();
		//model.c.updatePosition();
		//model.c.fictionForce();
		//model.c2.updatePosition();
		//model.c2.fictionForce();
	}
	
	private void addKeyEventHandler() {
		//TODO Fill Code
		gameDisplay.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.UP) {
					model.c.AddForce(new Force2D(model.c.getX(),model.c.getY(),0,-50));
				}
				if(event.getCode() == KeyCode.DOWN) {
					model.c.AddForce(new Force2D(model.c.getX(),model.c.getY(),0,50));
				}
				if(event.getCode() == KeyCode.LEFT) {
					model.c.AddForce(new Force2D(model.c.getX(),model.c.getY(),-50,0));
				}
				if(event.getCode() == KeyCode.RIGHT) {
					model.c.AddForce(new Force2D(model.c.getX(),model.c.getY(),50,0));
				}
				if(event.getCode() == KeyCode.ENTER) {
					
				}
				if(event.getCode() == KeyCode.W) {
					
				}
				if(event.getCode() == KeyCode.E) {
					
				}
				if(event.getCode() == KeyCode.R) {
				
				}
			}
		});
		
		gameDisplay.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.UP) {
					
				}
				if(event.getCode() == KeyCode.DOWN) {
					
				}
				if(event.getCode() == KeyCode.LEFT) {
					
				}
				if(event.getCode() == KeyCode.RIGHT) {
					
				}
				if(event.getCode() == KeyCode.ENTER) {
					
				}
			}
		});
	}

}
