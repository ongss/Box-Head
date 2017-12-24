package game_prefab;

import game_physic.Boundary;
import game_physic.Collider;

public class FixedObject extends Collider{
	private int hp;
	private boolean destroyAble;
	
	public FixedObject(int hp,boolean destroyAble,double posX, double posY, double width, double height) {
		super(999999999,posX, posY, width, height);
		this.destroyAble = destroyAble;
		this.hp = hp;
	}

	public void reciveDamage(int damage) {
		this.hp -= damage;
		if(this.hp < 0) this.hp = 0;
	}
	
	public boolean isDestroyed() {
		if(this.destroyAble && this.hp <= 0) return true;
		return false;
	}
}
