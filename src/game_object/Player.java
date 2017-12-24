package game_object;

import game_prefab.Charector;
import game_prefab.HpBar;
import game_weapon.WeaponManager;
import javafx.scene.canvas.GraphicsContext;

public class Player extends Charector {
	public static final int MASS = 50;
	public static final int HP = 100;
	public static final int ATTACK = 20;
	public static final double ATTACK_FORCE = 10;
	public static final double SPEED = 5;
	public WeaponManager wm;
	public HpBar hpBar;
	
	public Player(double posX, double posY) {
		super(MASS, HP, ATTACK, ATTACK_FORCE, SPEED, posX, posY);
		this.wm = new WeaponManager(this);
		this.hpBar = new HpBar(this);
	}
	
	public void autoRegeneration() {
		
	}
	
	public void shoot() {
		wm.fire();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		super.draw(gc);
		this.wm.draw(gc);
		this.hpBar.draw(gc);
	}
}
