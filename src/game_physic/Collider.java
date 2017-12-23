package game_physic;

import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Collider extends Boundary implements Comparable<Collider>{
	private final double FICTION_COEFFICIENT = 0.08;
	private final double REFECT_CEFFICIENT = 0.2;
	public int mass,inertia;
	public Boundary boundary;
	
	public double torque;
	public double angularAcceleration;
	public double angularVelocity;
	
	public Vector2D force;
	public Vector2D acceleration;
	public Vector2D velocity;
	
	
	public Collider(int mass,double posX, double posY, double width, double height) {
		super(posX, posY, width, height);
		this.mass = mass;
		this.inertia = mass;
		this.angularVelocity = 0;
		this.velocity = new Vector2D();
		this.angularAcceleration = 0;
		this.acceleration = new Vector2D();
		this.torque = 0;
		this.force = new Vector2D();
	}
	
	public Collider(int mass,double posX, double posY, double width, double height,Boundary boundary) {
		super(posX, posY, width, height);
		this.mass = mass;
		this.inertia = mass;
		this.angularVelocity = 0;
		this.velocity = new Vector2D();
		this.angularAcceleration = 0;
		this.acceleration = new Vector2D();
		this.torque = 0;
		this.force = new Vector2D();
		this.boundary = boundary;
	}
	
	
	public SupportingPoint getSupportingPoint(Collider other) {
		SupportingPoint sp = new SupportingPoint();
		Point2D p = new Point2D();
		Vector2D v = new Vector2D();
		Vector2D n = null;
		boolean allPositive = true;
		double tmp;
		double distance = 0;
		p.moveTo(0,0);
		for(Edge edge : edges) {
			allPositive = true;
			distance = 0;
			for(Point2D vertice : other.vertices) {
				v.set(vertice.vector());
				v.sub(edge.a.getX(),edge.a.getY());
				tmp = edge.normal.dot(v);
				if(distance > tmp) {
					allPositive = false;
					distance = tmp;
					n = edge.normal;
					p.moveTo(vertice.getX(),vertice.getY());
				}			
			}
			if(allPositive) {
				return null;
			}
			else if(sp.penetration < distance) {
				sp.set(p, n, distance);
			}
			
		}
		return sp;
	}
	
	public Contact checkContact(Collider other) {
		Contact c;
		SupportingPoint p1 = this.getSupportingPoint(other);
		SupportingPoint p2 = other.getSupportingPoint(this);
		if(p1 == null || p2 == null) return null;
		if(p1.penetration > p2.penetration) {
			c = new Contact(this,other,p1.penetration,p1.normal,p1);
			return c;
		}
		if(p1.penetration <= p2.penetration) {
			c = new Contact(other,this,p2.penetration,p2.normal,p2);
			return c;
		}
		return null;
	}
	
	public void resloveCollision(Collider other) {
		Vector2D vec = new Vector2D(this.getX()-other.getX(),this.getY()-other.getY());
		if(this.velocity.size() == 0) return;
		System.out.println(this.velocity.size());
		double vecAlongnormal = vec.dot(this.velocity.getUnit());
		if(vecAlongnormal > 0) return;
		double magnitude = -(1+this.REFECT_CEFFICIENT)*vecAlongnormal;
		magnitude /= 1 / this.mass + 1 / other.mass;
		vec = vec.multipleBy(magnitude);
		this.velocity.add(vec.tryMultipleBy(1/this.mass));
		other.velocity.add(vec.tryMultipleBy(1/other.mass));
	}
	
	public void applyTorque(double torque) {
		this.torque += torque;
	}
	
	public void applyForce(Vector2D f,Point2D p) {
		this.force.add(f);
		
		//applyTorque(this.getPos().vector(p).cross(f));
	}
	
	public void applyImpulse(Vector2D f,Point2D p) {
		velocity.add(new Vector2D(f).multipleBy(1/mass));
		
		//angularVelocity += (this.getPos().vector(p).cross(f) / inertia);
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
	
	public void update() {
		this.move(velocity);
		velocity.add(acceleration);
		force.scale(1/mass);
		acceleration.set(force);
		
		this.rotate(angularVelocity);
		angularVelocity += angularAcceleration;
		angularAcceleration += torque / inertia;
		
		force.set(0,0);
		torque = 0;
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
	
	public final Vector2D getVelocity() {
		return this.velocity;
	}
	
	public void setAngularVelocity(double v) {
		if(v > RotateAble.TERMINAL_ANGULAR_VELOCITY) this.angularVelocity = RotateAble.TERMINAL_ANGULAR_VELOCITY;
		else this.angularVelocity = v;
	}
	
	public String toString() {
		String s = "";
		for(Point2D p : vertices) {
			s += p.toString() + "\n";
		}
		return s;
	}
}
