package game_physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SupportingPoint extends Point2D {
	public Vector2D normal;
	public double penetration;
	
	public SupportingPoint() {
		super();
		normal = new Vector2D();
		penetration = -Double.MAX_VALUE;
		
	}
	
	public void set(Point2D p,Vector2D normal,double penetration) {
		if(p != null) this.moveTo(p);
		else p = null;
		this.penetration = penetration;
		this.normal = normal;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setStroke(Color.RED);
		gc.strokeLine(this.getX(), this.getY(), this.getX()+normal.tryMultipleBy(penetration).getX(), this.getY()+normal.tryMultipleBy(penetration).getY());
	}

}
