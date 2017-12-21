package game_physic;

import java.util.Map;

import javafx.scene.canvas.GraphicsContext;

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
	
    public Contact checkBoxBox(Collider other,GraphicsContext gc) {

        SupportingPoint SUP_POINT_RESULT_A = getClosestSupportingPoint(other);
        SupportingPoint SUP_POINT_RESULT_B = other.getClosestSupportingPoint(this);
        
        if(SUP_POINT_RESULT_A != null) SUP_POINT_RESULT_A.draw(gc);
        if(SUP_POINT_RESULT_B != null) SUP_POINT_RESULT_B.draw(gc);
        
        if (SUP_POINT_RESULT_A == null || SUP_POINT_RESULT_B == null) {
            return null;
        }
        
        Point2D contactPoint = null;
        Vector2D normal = new Vector2D();
        double penetration = -Integer.MAX_VALUE;
        
        if (SUP_POINT_RESULT_A != null) {
            contactPoint = SUP_POINT_RESULT_A;
            penetration = SUP_POINT_RESULT_A.penetration;
            normal.set(SUP_POINT_RESULT_A.normal);
        }

        if (SUP_POINT_RESULT_B != null 
                && SUP_POINT_RESULT_B.penetration > penetration) {
            
            contactPoint = SUP_POINT_RESULT_B;
            penetration = SUP_POINT_RESULT_B.penetration;
            normal.set(SUP_POINT_RESULT_B.normal);
            
            //Collider bTmp = other;
            //other = this;
            //this = bTmp;
        }
        
        return new Contact(other, this, -penetration, normal, contactPoint);
    }
	
	public Contact checkContact(Collider other,GraphicsContext gc) {
		SupportingPoint sp1 = this.getClosestSupportingPoint(other);
		SupportingPoint sp2 = other.getClosestSupportingPoint(this);
		if(sp1 != null) sp1.draw(gc);
		if(sp2 != null) sp2.draw(gc);
		if(sp1 == null || sp2 == null) return null;
		Point2D contactPnt = null;
		Vector2D normal = new Vector2D();
		double penetration = -Double.MAX_VALUE;
		if(sp1.penetration >= sp2.penetration) {
			contactPnt = sp1.get();
			penetration = sp1.penetration;
			normal.set(sp1.normal);
		}
		else {
			contactPnt = sp2.get();
			penetration = sp2.penetration;
			normal.set(sp2.normal);
		}
		return new Contact(this,other,-penetration,normal,contactPnt);
	}
	
	
	 private SupportingPoint getClosestSupportingPoint(Collider boxA) {
	        SupportingPoint supportingPointResult = new SupportingPoint();
    		SupportingPoint SUP_POINT_RESULT_TMP = new SupportingPoint();
			Vector2D V_TMP = new Vector2D();
			supportingPointResult.set(null, null, -Integer.MAX_VALUE);
	        for (Edge edge : boxA.edges) {
	            SUP_POINT_RESULT_TMP.set(null, null, 0);
	            Vector2D normal = edge.normal;
	            boolean allPositive = true;
	            for (Point2D v : this.vertices) {
	                V_TMP.set(v.vector());
	                V_TMP.sub(edge.a.getX(), edge.a.getY());
	                double distance = normal.dot(V_TMP);
	                if (distance < SUP_POINT_RESULT_TMP.penetration) {
	                    allPositive = false;
	                    SUP_POINT_RESULT_TMP.set(v, normal, distance);
	                }
	            }
	            // not collides - all vertices dot normal are positive
	            if (allPositive) {
	                supportingPointResult.set(null, null, 0);
	                return null;
	            }
	            else if (SUP_POINT_RESULT_TMP.penetration > supportingPointResult.penetration) {
	                supportingPointResult.set(SUP_POINT_RESULT_TMP,
	                         SUP_POINT_RESULT_TMP.normal, SUP_POINT_RESULT_TMP.penetration);
	            }
	        }
	        return supportingPointResult;
	    }
	
	public SupportingPoint getSupportingPoint(Collider other) {
		SupportingPoint sp = new SupportingPoint();
		SupportingPoint pntTmp = new SupportingPoint();
		Vector2D vecTmp = new Vector2D();
		boolean allPositive = true;
		for(Edge edge : edges) {
			pntTmp.set(null, null, 0);
			allPositive = true;
			for(Point2D p : other.vertices) {
				vecTmp.set(p.vector());
				vecTmp.sub(edge.a.getX(),edge.a.getY());
				double distance = edge.normal.dot(vecTmp);
				System.out.println(distance);
				if(distance < pntTmp.penetration) {
					allPositive = false;
					pntTmp.set(p, edge.normal, distance);
				}
			}
			if(allPositive) {
				return null;
			}
			else if(pntTmp.penetration > sp.penetration) {
				sp.set(pntTmp.get(),pntTmp.normal,pntTmp.penetration);
			}
		}
		return sp;
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
		
		applyTorque(this.getPos().vector(p).cross(f));
	}
	
	public void applyImpulse(Vector2D f,Point2D p) {
		velocity.add(new Vector2D(f).multipleBy(1/mass));
		
		angularVelocity += (this.getPos().vector(p).cross(f) / inertia);
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
}
