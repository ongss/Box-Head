package game_model;

import game_object.Arena;
import game_object.Player;
import game_object.Zombie;
import game_prefab.Collider;
import timer.GameTimer;

public class GameModel {
	private GameTimer timer;
	public Player player;
	public Zombie zombie;
	public Arena arena;
	
	public GameModel(GameTimer timer){
		this.timer = timer;
		player = new Player(50,50);
		zombie = new Zombie(100,100);
		arena = new Arena(10,10);
		arena.addPlayer(player);
		arena.addMonster(zombie);
	}
}
