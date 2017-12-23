package game_physic;

import java.util.Vector;

public class Vector2D {
	private double x,y;
	
	public Vector2D() {
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2D(Vector2D v) {
		this.x = v.x;
		this.y = v.y;
	}
	
	public Vector2D(double x,double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2D set(Vector2D other) {
		this.x = other.x;
		this.y = other.y;
		return this;
	}
	
	public Vector2D set(double x,double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2D add(Vector2D other) {
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2D add(double x,double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2D sub(Vector2D other) {
		this.x -= other.x;
		this.y -= other.y;
		return other;
	}
	
	public Vector2D sub(double x,double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public Vector2D translate(double x,double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2D tryAdd(Vector2D other){
		return new Vector2D(this.x + other.x,this.y + other.y);
	}
	
	
	public Vector2D multipleBy(double m) {
		this.x *= m;
		this.y *= m;
		return this;
	}
	
	public Vector2D tryMultipleBy(double m) {
		return new Vector2D(this.x*m,this.y*m);
	}
	
	public Vector2D inverse() {
		this.x = -this.x;
		this.y = -this.y;
		return this;
	}
	
	public void scale(double s) {
        this.x *= s;
        this.y *= s;
    }
	
	public Vector2D toUnit() {
		this.x = this.x / this.size();
		this.y = this.y / this.size();
		return this;
	}
	
	public Vector2D getUnit() {
		return new Vector2D(this.x / this.size(),this.y / this.size());
	}
	
	public void normalize() {
        this.scale(1 / this.size());
    }
	
	public double size() {
		return Math.pow(Math.pow(x, 2) + Math.pow(y, 2), 0.5);
	}
	
	public double dot(Vector2D other) {
		return this.x*other.x+this.y*other.y;
	}
	
	public double cross(Vector2D other) {
        return this.x*other.y-this.y*other.x;
    }
	
	public void cross(Vector2D other,double a) {
	    this.x = -a * other.y;
	    this.y = a * other.x;
	}
	    
	
	public double getSlope() {
		return y/x;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public String toString() {
		return "x : "+Double.toString(this.x)+" y : "+Double.toString(this.y);
	}
	
	
}
