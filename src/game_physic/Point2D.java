package game_physic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Point2D {
	private double x,y;
	
	public Point2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Point2D(Point2D p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public Point2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void moveTo(Point2D p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	public void moveTo(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(double x,double y) {
		this.x += x;
		this.y += y;
	}
	
	public Vector2D vector() {
		return new Vector2D(this.x,this.y);
	}
	
	public Vector2D vector(Point2D other) {
		// this -> other
		return new Vector2D(other.x - this.x,other.y - this.y);
	}
	
	public double slope(Point2D other) {
		return (this.y - other.y)/(this.x - other.x);
	}
	
	public double constance(Point2D other) {
		return this.y - slope(other)*this.x;
	}
	
	public boolean sameSite(Point2D other,double slope,double constance,Point2D slopeOrigin) { 
		if(!Double.isInfinite(slope)) return (other.x >= (other.y-constance)/slope) == (this.x >= (this.y-constance)/slope);
		if((this.getX() <= slopeOrigin.getX()) == (other.getX() <= slopeOrigin.getX())) return true;
		return false;
	}
	
	public double distance(Point2D other) {
		return Math.pow(Math.pow(this.y - other.y, 2) + Math.pow(this.x - other.x, 2), 0.5);
	}
	
	public double distance(double slope,double constance) {
		return Math.abs(this.y - this.x*slope - constance)/Math.pow(Math.pow(slope, 2) + 1,0.5);
	}
	
	public void rotate(Point2D center,double deg) {
		double r = distance(center);
		this.x = center.x + r*Math.cos(Math.toRadians(deg));
		this.y = center.y + r*Math.sin(Math.toRadians(deg));
	}
	
	public Point2D nextPos(Vector2D v) {
		return new Point2D(this.x + v.getX(),this.y + v.getX());
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public Point2D get() {
		return this;
	}
	
	public String toString() {
		return "x : "+this.x+" y : "+this.y;
	}
	
	public void draw(GraphicsContext gc,Color color) {
		gc.setFill(color);
		gc.fillOval(this.x, this.y, 5, 5);
	}

}
