package game_object;

import game_prefab.Charector;

public class Devil extends Charector {
	public static final int MASS = 50;
	public static final int HP = 100;
	public static final int ATTACK = 20;
	public static final double ATTACK_FORCE = 10;
	public static final double SPEED = 5;
	
	public Devil(double posX, double posY) {
		super(MASS, HP, ATTACK, ATTACK_FORCE, SPEED, posX, posY);
	}

}
