package game_prefab;

public class Vector2D {
	private double x,y;
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D multipleBy(double m) {
		this.x *= m;
		this.y *= m;
		return this;
	}
	
	public Vector2D inverse() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	public Vector2D toUnit() {
		this.x = this.x / this.size();
		this.y = this.y / this.size();
		return this;
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
