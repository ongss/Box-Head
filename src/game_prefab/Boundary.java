package game_prefab;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Boundary implements RotateAble,MoveAble{
	private Point2D center;
	private Point2D topLeft,topRight,bottomLeft,bottomRight;
	private double deg;
	
	public Boundary(double posX,double posY,double width,double height) {
		this.center = new Point2D(posX,posY);
		this.topLeft = new Point2D(posX-width/2,posY-height/2);
		this.topRight = new Point2D(posX+width/2,posY-height/2);
		this.bottomLeft = new Point2D(posX-width/2,posY+height/2);
		this.bottomRight = new Point2D(posX+width/2,posY+height/2);
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
		topLeft.rotate(center, this.deg+135);
		topRight.rotate(center, this.deg+45);
		bottomLeft.rotate(center, this.deg+225);
		bottomRight.rotate(center, this.deg+315);
	}
	
	public void rotateTo(double deg) {
		this.deg = deg;
		topLeft.rotate(center, this.deg+135);
		topRight.rotate(center, this.deg+45);
		bottomLeft.rotate(center, this.deg+225);
		bottomRight.rotate(center, this.deg+315);
	}
	
	public boolean isInBoundary(Point2D p) {
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
	
	public Point2D overlapPoint(Boundary other) {
		if(isInBoundary(other.topRight) && isInBoundary(other.bottomRight)) return new Point2D((other.topRight.getX()+other.bottomRight.getX())/2,(other.topRight.getY()+other.bottomRight.getY())/2);
		if(isInBoundary(other.topLeft) && isInBoundary(other.bottomLeft)) return new Point2D((other.topLeft.getX()+other.bottomLeft.getX())/2,(other.topLeft.getY()+other.bottomLeft.getY())/2);
		if(isInBoundary(other.topLeft) && isInBoundary(other.topRight)) return new Point2D((other.topLeft.getX()+other.topRight.getX())/2,(other.topLeft.getY()+other.topRight.getY())/2);
		if(isInBoundary(other.bottomLeft) && isInBoundary(other.bottomRight)) return new Point2D((other.bottomLeft.getX()+other.bottomRight.getX())/2,(other.bottomLeft.getY()+other.bottomRight.getY())/2);
		if(isInBoundary(other.topLeft)) return other.topLeft;
		if(isInBoundary(other.bottomLeft)) return other.bottomLeft;
		if(isInBoundary(other.bottomRight)) return other.bottomRight;
		if(isInBoundary(other.topRight)) return other.topRight;
		return null;
	}
	
	public Vector2D touch(Boundary other) {
		if(!isOverlap(other)) return new Vector2D(0,0);
		Point2D p = overlapPoint(other);
		return new Vector2D(this.center.getX()-p.getX(),this.center.getY()-p.getY());
	}
	
	public Point2D getPos() {
		return this.center;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.WHITE);
		
		gc.strokeLine(this.topLeft.getX(), this.topLeft.getY(), this.topRight.getX(), this.topRight.getY());
		gc.strokeLine(this.topRight.getX(), this.topRight.getY(), this.bottomRight.getX(), this.bottomRight.getY());
		gc.strokeLine(this.bottomRight.getX(), this.bottomRight.getY(), this.bottomLeft.getX(), this.bottomLeft.getY());
		gc.strokeLine(this.bottomLeft.getX(), this.bottomLeft.getY(), this.topLeft.getX(), this.topLeft.getY());
		
		
		/*gc.moveTo(this.topLeft.getX(), this.topLeft.getX());
		gc.beginPath();
		gc.lineTo(this.topRight.getX(), this.topRight.getY());
		gc.lineTo(this.bottomRight.getX(), this.bottomRight.getY());
		gc.lineTo(this.bottomLeft.getX(), this.bottomLeft.getY());
		gc.lineTo(this.topLeft.getX(), this.topLeft.getX());
		gc.closePath();
		gc.fill();*/
	}
	
	public double getX() {
		return this.center.getX();
	}
	
	public double getY() {
		return this.center.getY();
	}
}
