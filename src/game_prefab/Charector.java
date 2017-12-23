package game_prefab;

import game_physic.Collider;
import game_physic.DrawAble;
import game_physic.Force2D;
import game_physic.Vector2D;

public class Charector extends Collider implements DrawAble,WalkAble,TurnAble{
	public static final double WIDTH = 50;
	public static final double HEIGHT = 50;
	
	private int hp;
	private int attack;
	private double speed;
	private double walkSpeedX = 0,walkSpeedY = 0;
	private double force;

	public Charector(int mass,int hp,int attack,double force, double speed, double posX, double posY) {
		super(mass, posX, posY, WIDTH, HEIGHT);
		this.hp = hp;
		this.attack = attack;
		this.force = force;
		this.speed = speed;
	}
	
	public void attack(Charector other) {
		other.reciveDamage(this.attack);
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
			this.setWalkSpeed(-this.speed/Math.pow(2,0.5),-this.speed/Math.pow(2,0.5));
		}
		else if(di == "upRight") {
			this.setWalkSpeed(this.speed/Math.pow(2,0.5),-this.speed/Math.pow(2,0.5));
		}
		else if(di == "downLeft") {
			this.setWalkSpeed(-this.speed/Math.pow(2,0.5),this.speed/Math.pow(2,0.5));
		}
		else if(di == "downRight") {
			this.setWalkSpeed(this.speed/Math.pow(2,0.5),this.speed/Math.pow(2,0.5));
		}
		else if(di == "up") {
			this.setWalkSpeed(0,-this.speed);
		}
		else if(di == "down") {
			this.setWalkSpeed(0,this.speed);
		}
		else if(di == "right") {
			this.setWalkSpeed(this.speed,0);
		}
		else if(di == "left") {
			this.setWalkSpeed(-this.speed,0);
		}
	}
	
	private void setWalkSpeed(double x,double y) {
		this.still();
		walkSpeedX = x;
		walkSpeedY = y;
		this.setVelocity(this.velocity.getX()+walkSpeedX, this.velocity.getY()+walkSpeedY);
	}

	@Override
	public void walkTo(double x,double y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void still() {
		this.setVelocity(this.velocity.getX()-walkSpeedX, this.velocity.getY()-walkSpeedY);
		walkSpeedX = 0;
		walkSpeedY = 0;
	}

}
