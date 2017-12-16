package game_model;

import game_prefab.Collider;
import timer.GameTimer;

public class GameModel {
	private GameTimer timer;
	public Collider c;
	
	public GameModel(GameTimer timer){
		this.timer = timer;
		c = new Collider(20,50,50,50,50);
	}
}
