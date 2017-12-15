package game_prefab;

public class Point {
	private double x,y;
	
	public Point(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void move(double x,double y) {
		this.x += x;
		this.y += y;
	}
	
	public double slope(Point other) {
		return (this.y - other.y)/(this.x - other.x);
	}
	
	public double constance(Point other) {
		return this.y - slope(other)*this.x;
	}
	
	public boolean sameSite(Point other,double slope,double constance) { 
		return (other.x >= (other.y-constance)/slope) == (this.x >= (this.y-constance)/slope);
	}
	
	public double distance(Point other) {
		return Math.pow(Math.pow(this.y - other.y, 2) + Math.pow(this.x - other.x, 2), 0.5);
	}
	
	public double distance(double slope,double constance) {
		return Math.abs(this.y - this.x*slope - constance)/Math.pow(Math.pow(slope, 2) + 1,0.5);
	}
	
	public void rotate(Point center,double deg) {
		double r = distance(center);
		this.x = center.x + r*Math.cos(Math.toRadians(deg));
		this.y = center.y + r*Math.sin(Math.toRadians(deg));
	}
	
	public Point nextPos(Vector2D v) {
		return new Point(this.x + v.getX(),this.y + v.getX());
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
}
