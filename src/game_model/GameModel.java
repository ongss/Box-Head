package game_model;

import game_prefab.Collider;
import timer.GameTimer;

public class GameModel {
	private GameTimer timer;
	public Collider c,c2;
	
	public GameModel(GameTimer timer){
		this.timer = timer;
		c = new Collider(20,50,50,50,50);
		c2 = new Collider(20,50,50,200,200);
	}
}
