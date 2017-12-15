package game_prefab;

public class Vector2D {
	private double x,y;
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public void multipleBy(double m) {
		this.x *= m;
		this.y *= m;
	}
	
	public void inverse() {
		this.x = -this.x;
		this.y = -this.y;
	}
	
	public double size() {
		return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);
	}
	
	public double getSlope() {
		return y/x;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	
}
