package game_prefab;

import java.util.TreeSet;
import java.util.Vector;

import game_object.Player;
import game_physic.Boundary;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import scene.SceneManager;

public class Grid extends Boundary{
	private static final Font FONT = new Font("Monospace", 10);
	public static double WIDHT = 50;
	public static double HEIGHT = 50;
	
	private TreeSet<Charector> charector;
	private int distanceFormPlayer;
	
	public Grid(double posX,double posY) {
		super(posX,posY,Grid.WIDHT,Grid.HEIGHT);
		charector = new TreeSet<>();
		this.distanceFormPlayer = -1;
	}
	
	public void addCharector(Charector c) {
		charector.add(c);
	}
	
	public void removeCharector(Charector c) {
		charector.remove(c);
	}
	
	public void clearCharector() {
		charector.clear();
	}
	
	public boolean addFixOject() {
		return true;
	}
	
	public boolean hasCharector() {
		return !charector.isEmpty();
	}
	
	public Vector<Charector> outOfGrid(){
		Vector<Charector> c = new Vector<>();
		for(Charector x : charector) {
			if(!this.isInBoundary(x.getPos())) c.add(x);
		}
		return c;
	}
	
	public Vector<Charector> getCharector(){
		Vector<Charector> c = new Vector<>();
		for(Charector x : charector) {
			c.add(x);
		}
		return c;
	}
	
	public void setPlayerDistance(int dis) {
		this.distanceFormPlayer = dis;
	}
	
	public int getPlayerDistance() {
		return this.distanceFormPlayer;
	}
	
	public boolean isPlayerIn() {
		for(Charector c: charector) {
			if(c instanceof Player) return true; 
		}
		return false;
	}
	
	public void draw(GraphicsContext gc) {
		super.draw(gc,Color.WHITE);
		if(isPlayerIn()) super.draw(gc, Color.WHITE);
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setFill(Color.WHITE);
		gc.setFont(FONT);
		gc.fillText(Integer.toString(getPlayerDistance()), super.getX(), super.getY());
	}
	
	public int getPosX() {
		return (int) Math.floor(super.getX()/Grid.WIDHT);
	}
	
	public int getPosY() {
		return (int) Math.floor(super.getY()/Grid.HEIGHT);
	}
}