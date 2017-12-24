package game_weapon;

public abstract class Weapon {
	public String name;
	private double maxQuality;
	private double quality;
	private int damage;
	private boolean pass;
	private boolean hold;
	
	public Weapon(String name,double quality,int damage,boolean pass,boolean hold) {
		this.name = name;
		this.damage = damage;
		this.maxQuality = quality;
		this.quality = quality;
		this.pass = pass;
		this.hold = hold;
	}
	
	public double getQuality() {
		return this.quality;
	}
	
	public void decreaseQuality() {
		this.quality -= 1;
	}
	
	public void restore() {
		this.quality = maxQuality;
	}
	
	public abstract void fire();


}
