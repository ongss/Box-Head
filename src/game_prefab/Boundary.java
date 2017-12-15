package game_prefab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Boundary {
	private Point center;
	private Point topLeft,topRight,bottomLeft,bottomRight;
	private double deg;
	
	public Boundary(double posX,double posY,double width,double height) {
		this.center = new Point(posX,posY);
		this.topLeft = new Point(posX-width/2,posY-height/2);
		this.topRight = new Point(posX+width/2,posY-height/2);
		this.bottomLeft = new Point(posX-width/2,posY+height/2);
		this.bottomRight = new Point(posX+width/2,posY+height/2);
		this.deg = 0;
	}
	
	public void move(double x,double y) {
		this.center.move(x, y);
		this.topLeft.move(x, y);
		this.topRight.move(x, y);
		this.bottomLeft.move(x, y);
		this.bottomRight.move(x, y);
	}
	
	public void moveTo(double posX,double posY) {
		double x = posX - this.center.getX();
		double y = posY - this.center.getY();
		this.move(x, y);
	}
	
	public void rotate(double deg) {
		this.deg = (this.deg + deg)%360;
		topLeft.rotate(center, deg);
		topRight.rotate(center, deg);
		bottomLeft.rotate(center, deg);
		bottomRight.rotate(center, deg);
	}
	
	public boolean isInBoundary(Point p) {
		if(p.sameSite(center,topLeft.slope(topRight),topLeft.constance(topRight)) && p.sameSite(center,topLeft.slope(bottomLeft),topLeft.constance(bottomLeft)) && p.sameSite(center,bottomRight.slope(topRight),bottomRight.constance(topRight)) && p.sameSite(center,bottomLeft.slope(bottomRight),bottomLeft.constance(bottomRight))) return true;
		return false;
	}
	
	public boolean isInBoundary(Boundary other) {
		if(isInBoundary(other.topLeft) && isInBoundary(other.topRight) && isInBoundary(other.bottomLeft) && isInBoundary(other.bottomRight)) return true;
		return false;
	}
	
	public boolean isOverlap(Boundary other) {
		if(isInBoundary(other.topLeft) || isInBoundary(other.topRight) || isInBoundary(other.bottomLeft) || isInBoundary(other.bottomRight)) return true;
		return false;
	}
	
	public Point overlapPoint(Boundary other) {
		if(isInBoundary(other.topRight) && isInBoundary(other.bottomRight)) return new Point((other.topRight.getX()+other.bottomRight.getX())/2,(other.topRight.getY()+other.bottomRight.getY())/2);
		if(isInBoundary(other.topLeft) && isInBoundary(other.bottomLeft)) return new Point((other.topLeft.getX()+other.bottomLeft.getX())/2,(other.topLeft.getY()+other.bottomLeft.getY())/2);
		if(isInBoundary(other.topLeft) && isInBoundary(other.topRight)) return new Point((other.topLeft.getX()+other.topRight.getX())/2,(other.topLeft.getY()+other.topRight.getY())/2);
		if(isInBoundary(other.bottomLeft) && isInBoundary(other.bottomRight)) return new Point((other.bottomLeft.getX()+other.bottomRight.getX())/2,(other.bottomLeft.getY()+other.bottomRight.getY())/2);
		if(isInBoundary(other.topLeft)) return other.topLeft;
		if(isInBoundary(other.bottomLeft)) return other.bottomLeft;
		if(isInBoundary(other.bottomRight)) return other.bottomRight;
		if(isInBoundary(other.topRight)) return other.topRight;
		return null;
	}
	
	public Vector2D touch(Boundary other) {
		if(!isOverlap(other)) return new Vector2D(0,0);
		Point p = overlapPoint(other);
		return new Vector2D(this.center.getX()-p.getX(),this.center.getY()-p.getY());
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		/*gc.beginPath();
		gc.moveTo(this.topLeft.getX(), this.topLeft.getX());
		gc.lineTo(this.topRight.getX(), this.topRight.getY());
		gc.lineTo(this.bottomRight.getX(), this.bottomRight.getY());
		gc.lineTo(this.bottomLeft.getX(), this.bottomLeft.getY());
		gc.moveTo(this.topLeft.getX(), this.topLeft.getX());
		gc.closePath();
		gc.fill();*/
		gc.fillRect(this.center.getX(), this.center.getY(), 50, 50);
	}
	
	public double getX() {
		return this.center.getX();
	}
	
	public double getY() {
		return this.center.getY();
	}
}
