package game_physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Edge {
	public Point2D a,b;
	public Vector2D normal;
	
	public Edge(Point2D a,Point2D b) {
		this.a = a;
		this.b = b;
		this.normal = new Vector2D();
	}
	
	public void recalculateNormal() {
		this.normal.set(b.getX(),b.getY());
		this.normal.sub(a.getX(),a.getY());
		this.normal.set(-this.normal.getY(),this.normal.getX());
		this.normal.normalize();
	}
	
	public void draw(GraphicsContext gc,Color color) {
		gc.setStroke(color);
		gc.strokeLine(this.b.getX(), this.b.getY(), this.a.getX(), this.a.getY());
		gc.setStroke(Color.YELLOW);
		gc.strokeLine((this.b.getX()+this.a.getX())/2,(this.b.getY()+this.a.getY())/2,(this.b.getX()+this.a.getX())/2+normal.getX()*5,(this.b.getY()+this.a.getY())/2+normal.getY()*5);
	}
	
}
