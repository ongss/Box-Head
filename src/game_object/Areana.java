package game_object;

import java.util.Vector;

import game_prefab.Boundary;
import game_prefab.Charector;
import game_prefab.DrawAble;
import game_prefab.Grid;

public class Areana extends Boundary implements DrawAble{
	private int width,height;
	private Grid grid[][];
	private Vector<Player> player;
	
	public Areana(int width, int height) {
		super( width*Grid.WIDHT/2, height*Grid.HEIGHT/2, width*Grid.WIDHT, height*Grid.HEIGHT);
		this.height = height;
		this.width = width;
		grid = new Grid[width][height];
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				grid[i][j] = new Grid(Grid.WIDHT*i+Grid.WIDHT/2,Grid.HEIGHT*j+Grid.WIDHT/2);
			}
		}
	}
	
	public void update() {
		changeGrid();
	}
	
	public void clearPlayerDistance() {
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				grid[i][j].setPlayerDistance(-1);
			}
		}
	}
	
	public void setPlayerDistance() {
		
	}
	
	public void changeGrid() {
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				for(Charector c: grid[i][j].outOfGrid()) {
					grid[i][j].removeCharector(c);
					grid[(int) Math.floor(c.getX()/Grid.WIDHT)][(int) Math.floor(c.getY()/Grid.HEIGHT)].addCharector(c);
				}
			}
		}
	}
	
	public void addMonster(Zombie m) {
		grid[(int) Math.floor(m.getX()/Grid.WIDHT)][(int) Math.floor(m.getY()/Grid.HEIGHT)].addCharector(m);
	}
	
	public void addMonster(Devil m) {
		grid[(int) Math.floor(m.getX()/Grid.WIDHT)][(int) Math.floor(m.getY()/Grid.HEIGHT)].addCharector(m);
	}
	
	public void addPlayer(Player p) {
		grid[(int) Math.floor(p.getX()/Grid.WIDHT)][(int) Math.floor(p.getY()/Grid.HEIGHT)].addCharector(p);
		this.player.add(p);
	}
	
}
