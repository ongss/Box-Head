package game_object;

import game_prefab.Charector;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Zombie extends Charector{
	public static final int MASS = 50;
	public static final int HP = 100;
	public static final int ATTACK = 20;
	public static final double ATTACK_FORCE = 10;
	public static final double SPEED = 5;
	
	public Zombie(double posX, double posY) {
		super(999999999, HP, ATTACK, ATTACK_FORCE, SPEED, posX, posY);
	}
	
	public void draw(GraphicsContext gc) {
		super.draw(gc,Color.GREEN);
	}
	
}
