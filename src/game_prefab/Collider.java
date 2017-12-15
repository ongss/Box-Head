package game_prefab;

import javafx.scene.canvas.GraphicsContext;

public class Collider extends Boundary implements MoveAble,DrawAble,TurnAble {
	private final double FICTION_COEFFICIENT = 0.08;
	private final double REFECT_CEFFICIENT = 0.2;
	private double velocityX,velocityY,angularVelocity;
	private int mass;
	
	public Collider(int mass,double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		this.mass = mass;
		this.angularVelocity = 0;
		this.velocityX = 0;
		this.velocityY = 0;
	}

	public void checkHit(Boundary other,Vector2D v) {
		if(other instanceof Collider) {
			Vector2D f = this.touch(other);
			f.multipleBy(this.FICTION_COEFFICIENT);
			if(f.size() == 0) return;
			Point p = this.overlapPoint(other);
			((Collider) other).AddForce(new Force2D(p,f));
			f.inverse();
			this.AddForce(new Force2D(p,f));
		}
	}
	
	public void AddForce(Force2D f) {
		velocityX += f.getSizeX()/this.mass;
		if(velocityX > TERMINAL_VELOCITY) velocityX = TERMINAL_VELOCITY;
		velocityY += f.getSizeY()/this.mass;
		if(velocityY > TERMINAL_VELOCITY) velocityY = TERMINAL_VELOCITY;
		angularVelocity = f.getSizeX()*(this.getX()-f.getPosX()) - f.getSizeY()*(this.getY()-f.getPosY())/this.mass;
		if(angularVelocity > TERMINAL_ANGULAR_VELOCITY) angularVelocity = TERMINAL_ANGULAR_VELOCITY;
	}
	
	public void fictionForce() {
		double fictionForce = 10*mass*FICTION_COEFFICIENT;
		if(Math.abs(this.velocityX) < fictionForce/mass) this.velocityX = 0;
		else if(this.velocityX > 0) this.velocityX -= fictionForce/mass;
		else if(this.velocityX < 0) this.velocityX += fictionForce/mass;
		if(Math.abs(this.velocityY) < fictionForce/mass) this.velocityY = 0;
		else if(this.velocityY > 0) this.velocityY -= fictionForce/mass;
		else if(this.velocityY < 0) this.velocityY += fictionForce/mass;	
	}
	
	public void updatePosition() {
		this.move(velocityX, velocityY);
		this.rotate(angularVelocity);
	}

	@Override
	public void turn(String r) {
		// TODO Auto-generated method stub
		
	}
	
	
}
