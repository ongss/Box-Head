package game_physic;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Boundary implements RotateAble,MoveAble{
	private Point2D center;
	public final Point2D[] vertices = { new Point2D(),new Point2D(), new Point2D(), new Point2D()};
	public final Edge[] edges = { 
	        new Edge(vertices[0], vertices[1]), 
	        new Edge(vertices[1], vertices[2]), 
	        new Edge(vertices[2], vertices[3]), 
	        new Edge(vertices[3], vertices[0]) };
	private double width,height;
	private double deg;
	
	public Boundary(double posX,double posY,double width,double height) {
		this.center = new Point2D(posX,posY);
		this.vertices[0].moveTo(posX-width/2,posY-height/2);
		this.vertices[1].moveTo(posX+width/2,posY-height/2);
		this.vertices[2].moveTo(posX+width/2,posY+height/2);
		this.vertices[3].moveTo(posX-width/2,posY+height/2);
		this.width = width;
		this.height = height;
		this.deg = 0;
		this.rotate(-1);
		this.rotate(1);
	}
	
	public void move(Vector2D v) {
		move(v.getX(),v.getY());
	}
	
	public void move(double x,double y) {
		this.center.move(x, y);
		this.vertices[0].move(x, y);
		this.vertices[1].move(x, y);
		this.vertices[2].move(x, y);
		this.vertices[3].move(x, y);
	}
	
	public void moveTo(double posX,double posY) {
		double x = posX - this.center.getX();
		double y = posY - this.center.getY();
		this.move(x, y);
	}
	
	public void rotate(double deg) {
		this.deg = (this.deg + deg)%360;
		vertices[0].rotate(center, this.deg+315);
		vertices[1].rotate(center, this.deg+225);
		vertices[2].rotate(center, this.deg+135);
		vertices[3].rotate(center, this.deg+45);
		recalculateEdgesNormal();
	}
	
	public void rotateTo(double deg) {
		this.deg = deg%360;
		vertices[0].rotate(center, this.deg+315);
		vertices[1].rotate(center, this.deg+225);
		vertices[2].rotate(center, this.deg+135);
		vertices[3].rotate(center, this.deg+45);
		recalculateEdgesNormal();
	}
	
	public boolean isInBoundary(Point2D p) {
		for(Edge edge : edges) {
			if(edge.normal.dot(p.vector()) > 0) return false;
		}
		return true;
	}
	
	public boolean isInBoundary(Boundary other) {
		for(Point2D vertice : other.vertices) {
			if(!this.isInBoundary(vertice)) return false;
		}
		return true;
	}
	
	public boolean isOverlap(Boundary other) {
		for(Point2D vertice : other.vertices) {
			if(this.isInBoundary(vertice)) return true;
		}
		return false;
	}
	
	public Point2D getPos() {
		return this.center;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setStroke(Color.WHITE);
		
		edges[0].draw(gc, Color.RED);
		edges[1].draw(gc, Color.WHITE);
		edges[2].draw(gc, Color.WHITE);
		edges[3].draw(gc, Color.WHITE);
		
	}
	
	public void recalculateEdgesNormal() {
		for(Edge edge : edges) {
			edge.recalculateNormal();
		}
	}
	
	public void draw(GraphicsContext gc,Color color) {
		for(Edge edge : edges) {
			edge.draw(gc, color);
		}
	}
	
	public double getX() {
		return this.center.getX();
	}
	
	public double getY() {
		return this.center.getY();
	}
}
