package game_prefab;

import javafx.scene.canvas.GraphicsContext;

public class Collider extends Boundary implements Comparable<Collider>{
	private final double FICTION_COEFFICIENT = 0.08;
	private final double REFECT_CEFFICIENT = 0.2;
	private double angularVelocity;
	public Vector2D velocity;
	private int mass;
	
	public Collider(int mass,double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		this.mass = mass;
		this.angularVelocity = 0;
		this.velocity = new Vector2D(0,0);
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
		velocity.setX(velocity.getX() + f.getSizeX()/this.mass);
		if(velocity.getX() > super.TERMINAL_VELOCITY) velocity.setX(super.TERMINAL_VELOCITY);
		velocity.setY(velocity.getY() + f.getSizeY()/this.mass); 
		if(velocity.getY() > super.TERMINAL_VELOCITY) velocity.setY(super.TERMINAL_VELOCITY);
		angularVelocity += f.getSizeX()*(this.getX()-f.getPosX()) - f.getSizeY()*(this.getY()-f.getPosY())/this.mass;
		if(Math.abs(angularVelocity) > super.TERMINAL_ANGULAR_VELOCITY) angularVelocity = super.TERMINAL_ANGULAR_VELOCITY;
	}
	
	public void fictionForce() {
		double fictionForce = 10*mass*FICTION_COEFFICIENT;
		if(Math.abs(this.velocity.getX()) < fictionForce/mass) this.velocity.setX(0);
		else if(this.velocity.getX() > 0) this.velocity.setX(velocity.getX() - fictionForce/mass);
		else if(this.velocity.getX() < 0) this.velocity.setX(velocity.getX() + fictionForce/mass);
		if(Math.abs(this.velocity.getY()) < fictionForce/mass) this.velocity.setY(0);
		else if(this.velocity.getY() > 0) this.velocity.setY(velocity.getY() - fictionForce/mass);
		else if(this.velocity.getY() < 0) this.velocity.setY(velocity.getY() + fictionForce/mass);	
		if(Math.abs(this.angularVelocity) < fictionForce/(10*mass)) this.angularVelocity = 0;
		else if(this.angularVelocity > 0) this.angularVelocity -= fictionForce/(10*mass);
		else if(this.angularVelocity < 0) this.angularVelocity += fictionForce/(10*mass);
	}
	
	public void updatePosition() {
		this.move(velocity.getX(), velocity.getY());
		this.rotate(angularVelocity);
	}

	@Override
	public int compareTo(Collider other) {
		if(this.getX() > other.getX()) return 1;
		if(this.getX() < other.getX()) return -1;
		if(this.getY() > other.getY()) return 1;
		if(this.getY() < other.getY()) return -1;
		return 0;
	}

	public void setVelocity(double x,double y) {
		this.velocity.setX(x);
		this.velocity.setY(y);
	}
	
}
