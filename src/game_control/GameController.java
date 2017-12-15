package game_control;

import game_model.GameModel;
import scene.GameOver;
import scene.SceneManager;
import timer.GameTimer;

public class GameController {
	
	private static GameModel model;
	private static GameDisplay gameDisplay;
	private static GameOver gameOver;
	private static GameLogic logic;
	private static GameTimer timer;
	
	public static void newGame() {
		timer = new GameTimer();
		model = new GameModel(timer);
		gameDisplay = new GameDisplay(model,timer);
		logic = new GameLogic(model,gameDisplay,timer);
		SceneManager.gotoSceneOf(gameDisplay);
		timer.startTimer();
		logic.startGame();
		gameDisplay.startAnimation();
		
	}
	
	public static void pauseGame() {
		
	}
	
	public static void resumeGame() {
		
	}
	
	public static void stopGame() {
		timer.stopTimer();
		gameDisplay.stopAnimation();
		logic.stopGame();
	}
	
	public static void gameOver() {
		gameOver = new GameOver();
		SceneManager.gotoSceneOf(gameOver);
	}
}
