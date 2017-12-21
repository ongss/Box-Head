package game_prefab;

import game_physic.Boundary;

public class FixedObject extends Boundary{
	private int hp;
	private boolean destroyAble;
	
	public FixedObject(int hp,boolean destroyAble,double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		this.hp = hp;
		this.destroyAble = destroyAble;
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
