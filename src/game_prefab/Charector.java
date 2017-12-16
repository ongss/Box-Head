package game_prefab;

public class Charector extends Collider implements DrawAble{
	public static final double CHARECTOR_WIDTH = 50;
	public static final double CHARECTOR_HEIGHT = 50;
	
	private int hp;
	private int attack;
	private double speed;
	private double force;

	public Charector(int mass,int hp,int attack,double force, double speed, double posX, double posY) {
		super(mass, posX, posY, CHARECTOR_WIDTH, CHARECTOR_HEIGHT);
		this.hp = hp;
		this.attack = attack;
		this.force = force;
		this.speed = speed;
	}
	
	public void attack(Charector other) {
		Force2D f = new Force2D(other.getX(),other.getY(),this.getX()-other.getX(),this.getY()-other.getY());
		other.reciveDamage(this.attack);
		other.AddForce(f.toUnit().multipleBy(force));
	}
	
	public void reciveDamage(int damage) {
		this.hp -= damage;
		if(this.hp < 0) this.hp = 0;
	}
	
	public int compareTo(Charector other) {
		if(this.getX() > other.getX()) return 1;
		if(this.getX() < other.getX()) return -1;
		if(this.getY() > other.getY()) return 1;
		if(this.getY() < other.getY()) return -1;
		return 0;
	}

}
