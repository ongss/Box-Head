package game_prefab;

import java.util.TreeSet;
import java.util.Vector;

public class Grid extends Boundary{
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
	
	public void setPlayerDistance(int dis) {
		this.distanceFormPlayer = dis;
	}
	
	public int getPlayerDistance() {
		return this.distanceFormPlayer;
	}
}