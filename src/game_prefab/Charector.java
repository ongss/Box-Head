package game_prefab;

public class Charector extends Collider implements DrawAble,WalkAble,TurnAble{
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
	
	
	public boolean isAlive() {
		if(this.hp >= 0) return true;
		return false;
	}

	@Override
	public void turn(String di) {
		
		
	}

	@Override
	public void turnTo(String di) {
		if(di == "upLeft") {
			this.rotateTo(135);
		}
		else if(di == "upRight") {
			this.rotateTo(225);
		}
		else if(di == "downLeft") {
			this.rotateTo(45);
		}
		else if(di == "downRight") {
			this.rotateTo(315);
		}
		else if(di == "up") {
			this.rotateTo(180);
		}
		else if(di == "down") {
			this.rotateTo(0);
		}
		else if(di == "left") {
			this.rotateTo(90);
		}
		else if(di == "right") {
			this.rotateTo(270);
		}
		
	}

	@Override
	public void walk(String di) {
		if(di == "upLeft") {
			this.setVelocity(-this.speed/Math.pow(2,0.5),-this.speed/Math.pow(2,0.5));
		}
		else if(di == "upRight") {
			this.setVelocity(this.speed/Math.pow(2,0.5),-this.speed/Math.pow(2,0.5));
		}
		else if(di == "downLeft") {
			this.setVelocity(-this.speed/Math.pow(2,0.5),this.speed/Math.pow(2,0.5));
		}
		else if(di == "downRight") {
			this.setVelocity(this.speed/Math.pow(2,0.5),this.speed/Math.pow(2,0.5));
		}
		else if(di == "up") {
			this.setVelocity(0,-this.speed);
		}
		else if(di == "down") {
			this.setVelocity(0,this.speed);
		}
		else if(di == "right") {
			this.setVelocity(this.speed,0);
		}
		else if(di == "left") {
			this.setVelocity(-this.speed,0);
		}
	}

	@Override
	public void walkTo(double x,double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void still() {
		this.setVelocity(0, 0);
	}

}
