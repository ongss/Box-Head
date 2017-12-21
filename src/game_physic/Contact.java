package game_physic;


import java.awt.Color;
import java.awt.Graphics2D;

public class Contact {

    private Collider ra;
    private Collider rb;
    
    public double penetration;
    public final Point2D point = new Point2D();
    public final Vector2D normal = new Vector2D();

    public Contact(Collider ra, Collider rb, double penetration, Vector2D normal, Point2D point) {
        this.ra = ra;
        this.rb = rb;
        this.penetration = penetration;
        this.normal.set(normal);
        this.point.moveTo(point);
    }

    public Collider getRa() {
        return ra;
    }

    public Collider getRb() {
        return rb;
    }

    public double getPenetration() {
        return penetration;
    }

    public Vector2D getNormal() {
        return normal;
    }

    public Point2D getPoint() {
        return point;
    }

    public void resolveCollision() {
        // vap1
        Vector2D vap1 = new Vector2D();
        vap1.set(ra.velocity);
        
        Vector2D r1 = new Vector2D();
        r1.set(ra.getPos().vector(point));
                
        Vector2D val1 = new Vector2D();
        val1.cross(r1,ra.angularVelocity);
        vap1.add(val1);
        
        // vap2
        Vector2D vap2 = new Vector2D();
        vap2.set(rb.velocity);
        
        Vector2D r2 = new Vector2D();
        r2.set(rb.getPos().vector(point));
                
        Vector2D val2 = new Vector2D();
        val2.cross(r2,rb.angularVelocity);
        vap2.add(val2);
        
        // relative velocity
        Vector2D vrel = new Vector2D();
        vrel.set(vap1);
        vrel.sub(vap2);
        
        if (vrel.dot(normal) > 0) {
            return;
        }
        
        double e = 0.5; // restitution hard coded
        
        double raxn = r1.cross(normal);
        double rbxn = r2.cross(normal);
        
        double totalMass = (1 / ra.mass) + (1 / rb.mass) + ((raxn * raxn) / ra.inertia) + ((rbxn * rbxn) / rb.inertia);
        
        double j = (-(1 + e) * vrel.dot(normal)) / totalMass;
        
        Vector2D impulse = new Vector2D();
        impulse.set(normal);
        impulse.scale(j);
        
        ra.applyImpulse(impulse, point);
        impulse.scale(-1);
        rb.applyImpulse(impulse, point);
    }
    
    public void correctPosition() {
        double f = penetration / (ra.mass + rb.mass);
        Vector2D p = new Vector2D();
        
        p.set(normal);
        p.scale(f * rb.mass * 0.8);
        ra.move(p);
        
        p.set(normal);
        p.scale(f * ra.mass * 0.8);
        rb.move(p.inverse());
    }

    public int hashCode() {
        int hash = 3;
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contact c = (Contact) obj;
        if (ra == c.ra && rb == c.rb) {
            return true;
        }
        else if (ra == c.rb && rb == c.ra) {
            return true;
        }
        return false;
    }
    
}