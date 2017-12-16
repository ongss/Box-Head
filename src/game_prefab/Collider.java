package game_prefab;

import javafx.scene.canvas.GraphicsContext;

public class Collider extends Boundary {
	private final double FICTION_COEFFICIENT = 0.08;
	private final double REFECT_CEFFICIENT = 0.2;
	private double velocityX,velocityY,angularVelocity;
	private int mass;
	
	public Collider(int mass,double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		this.mass = mass;
		this.angularVelocity = 10;
		this.velocityX = 0;
		this.velocityY = 0;
	}

	public void checkHit(Boundary other,Vector2D v) {
		Vector2D f = this.touch(other);
		if(other instanceof Collider) {
			f.multipleBy(this.FICTION_COEFFICIENT);
			if(f.size() == 0) return;
			Point2D p = this.overlapPoint(other);
			((Collider) other).AddForce(new Force2D(p,f));
			this.AddForce(new Force2D(p,f.inverse()));
		}
		else if(other instanceof FixedObject) {
			f.multipleBy(this.FICTION_COEFFICIENT);
			if(f.size() == 0) return;
			Point2D p = this.overlapPoint(other);
			this.AddForce(new Force2D(p,f.inverse()));
		}
	}
	
	public void AddForce(Force2D f) {
		velocityX += f.getSizeX()/this.mass;
		if(velocityX > super.TERMINAL_VELOCITY) velocityX = super.TERMINAL_VELOCITY;
		velocityY += f.getSizeY()/this.mass;
		if(velocityY > super.TERMINAL_VELOCITY) velocityY = super.TERMINAL_VELOCITY;
		angularVelocity += f.getSizeX()*(this.getX()-f.getPosX()) - f.getSizeY()*(this.getY()-f.getPosY())/this.mass;
		if(Math.abs(angularVelocity) > super.TERMINAL_ANGULAR_VELOCITY) angularVelocity = super.TERMINAL_ANGULAR_VELOCITY;
	}
	
	public void fictionForce() {
		double fictionForce = 10*mass*FICTION_COEFFICIENT;
		if(Math.abs(this.velocityX) < fictionForce/mass) this.velocityX = 0;
		else if(this.velocityX > 0) this.velocityX -= fictionForce/mass;
		else if(this.velocityX < 0) this.velocityX += fictionForce/mass;
		if(Math.abs(this.velocityY) < fictionForce/mass) this.velocityY = 0;
		else if(this.velocityY > 0) this.velocityY -= fictionForce/mass;
		else if(this.velocityY < 0) this.velocityY += fictionForce/mass;	
		if(Math.abs(this.angularVelocity) < fictionForce/(10*mass)) this.angularVelocity = 0;
		else if(this.angularVelocity > 0) this.angularVelocity -= fictionForce/(10*mass);
		else if(this.angularVelocity < 0) this.angularVelocity += fictionForce/(10*mass);
	}
	
	public void updatePosition() {
		this.move(velocityX, velocityY);
		this.rotate(angularVelocity);
	}

	
}
