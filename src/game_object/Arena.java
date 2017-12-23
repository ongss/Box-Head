package game_object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import game_physic.Boundary;
import game_physic.Collider;
import game_physic.Contact;
import game_physic.DrawAble;
import game_prefab.Charector;
import game_prefab.FixedObject;
import game_prefab.Grid;
import javafx.scene.canvas.GraphicsContext;

public class Arena extends Boundary implements DrawAble{
	private int width,height;
	private Grid grid[][];
	private Vector<Player> player;
	private Vector<Collider> colliders;
    private final Set<Contact> contacts;
	
	private Vector<FixedObject> fixed;
	
	public Arena(int width, int height) {
		super( width*Grid.WIDHT/2, height*Grid.HEIGHT/2, width*Grid.WIDHT, height*Grid.HEIGHT);
		this.height = height;
		this.width = width;
		grid = new Grid[width][height];
		player = new Vector<>();
		colliders = new Vector<>();
		contacts = new HashSet<Contact>();
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				grid[i][j] = new Grid(Grid.WIDHT*i+Grid.WIDHT/2,Grid.HEIGHT*j+Grid.WIDHT/2);
			}
		}
	}
	
	public void update() {
		changeGrid();
		physicUpdate();
	}
	
	public void clearPlayerDistance() {
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				grid[i][j].setPlayerDistance(-1);
			}
		}
	}
	
	public void updatePlayerDistance() {
		Queue<Grid> queue = new LinkedList<>();
		this.clearPlayerDistance();
		for(Player p : player) {
			int x = (int) Math.floor(p.getX()/Grid.WIDHT);
			int y = (int) Math.floor(p.getY()/Grid.HEIGHT);
			grid[x][y].setPlayerDistance(0);
			queue.add(grid[x][y]);
			while(!queue.isEmpty()) {
				Grid g = queue.peek();
				if(g.getPosX() >= 1 && (grid[g.getPosX()-1][g.getPosY()].getPlayerDistance() == -1 || grid[g.getPosX()-1][g.getPosY()].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()-1][g.getPosY()].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()-1][g.getPosY()]);
				}
				if(g.getPosX() <= this.width-2 && (grid[g.getPosX()+1][g.getPosY()].getPlayerDistance() == -1 || grid[g.getPosX()+1][g.getPosY()].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()+1][g.getPosY()].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()+1][g.getPosY()]);
				}
				if(g.getPosY() >= 1 && (grid[g.getPosX()][g.getPosY()-1].getPlayerDistance() == -1 || grid[g.getPosX()][g.getPosY()-1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()][g.getPosY()-1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()][g.getPosY()-1]);
				}
				if(g.getPosY() <= this.height-2 && (grid[g.getPosX()][g.getPosY()+1].getPlayerDistance() == -1 || grid[g.getPosX()][g.getPosY()+1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()][g.getPosY()+1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()][g.getPosY()+1]);
				}
				if(g.getPosX() >= 1 && g.getPosY() >= 1 && (grid[g.getPosX()-1][g.getPosY()-1].getPlayerDistance() == -1 || grid[g.getPosX()-1][g.getPosY()-1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()-1][g.getPosY()-1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()-1][g.getPosY()-1]);
				}
				if(g.getPosX() >= 1 && g.getPosY() <= this.height-2 && (grid[g.getPosX()-1][g.getPosY()+1].getPlayerDistance() == -1 || grid[g.getPosX()-1][g.getPosY()+1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()-1][g.getPosY()+1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()-1][g.getPosY()+1]);
				}
				if(g.getPosX() <= this.width-2 && g.getPosY() >= 1 && (grid[g.getPosX()+1][g.getPosY()-1].getPlayerDistance() == -1 || grid[g.getPosX()+1][g.getPosY()-1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()+1][g.getPosY()-1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()+1][g.getPosY()-1]);
				}
				if(g.getPosX() <= this.width-2 && g.getPosY() <= this.height-2 && (grid[g.getPosX()+1][g.getPosY()+1].getPlayerDistance() == -1 || grid[g.getPosX()+1][g.getPosY()+1].getPlayerDistance() > g.getPlayerDistance())) {
					grid[g.getPosX()+1][g.getPosY()+1].setPlayerDistance(g.getPlayerDistance()+1); 
					queue.add(grid[g.getPosX()+1][g.getPosY()+1]);
				}
				queue.remove();
			}
		}
	}
	
	public void changeGrid() {
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				for(Charector c: grid[i][j].getCharector()) {
					int newX = (int) Math.floor(c.getX()/Grid.WIDHT);
					int newY = (int) Math.floor(c.getY()/Grid.HEIGHT); 
					if(newX != i || newY != j) {
						grid[newX][newY].addCharector(c);
						grid[i][j].removeCharector(c);
						if(c instanceof Player) updatePlayerDistance();
					}
				}
			}
		}
	}
	
	public void physicUpdate() {
		//checkCollision();
		//resolveCollisions();
		//correctPositions();
		for(Collider c: colliders) {
			c.update();
		}
	}
	
	public void checkCollision() {
		contacts.clear();
		Collections.sort(colliders,new Comparator<Collider>() {
			@Override
			public int compare(Collider o1, Collider o2) {
				if(o1.getX() > o2.getX()) return 1;
				if(o1.getX() < o2.getX()) return -1;
				return 0;
			}
		});
		for(int i=0;i<colliders.size();i++) {
			double minX = colliders.get(i).getX() - 1.5*Charector.WIDTH;
			double minY = colliders.get(i).getX() - 1.5*Charector.HEIGHT;
			double maxX = colliders.get(i).getX() + 1.5*Charector.WIDTH;
			double maxY = colliders.get(i).getY() + 1.5*Charector.HEIGHT;
			int cnt = 1;
			while(i+cnt < colliders.size() && colliders.get(i+cnt).getX() >= minX && colliders.get(i+cnt).getX() <= maxX) {
				if(colliders.get(i+cnt).getY() >= minY && colliders.get(i+cnt).getY() <= maxY) {
					Contact c = colliders.get(i).checkContact(colliders.get(i+cnt));
					if (c != null && !contacts.contains(c)) {
	                    contacts.add(c);
	                   // System.out.println(c.penetration);
	                } 
				}
				cnt++;
			}
		}
	}
	
    private void resolveCollisions() {
        for (Contact contact : contacts) {
            contact.resolveCollision();
        }
    }

    private void correctPositions() {
        for (Contact contact : contacts) {
            contact.correctPosition();
        }
    }
	
	public void addMonster(Zombie m) {
		grid[(int) Math.floor(m.getX()/Grid.WIDHT)][(int) Math.floor(m.getY()/Grid.HEIGHT)].addCharector(m);
		colliders.add(m);
	}
	
	public void addMonster(Devil m) {
		grid[(int) Math.floor(m.getX()/Grid.WIDHT)][(int) Math.floor(m.getY()/Grid.HEIGHT)].addCharector(m);
		colliders.add(m);
	}
	
	public void addPlayer(Player p) {
		grid[(int) Math.floor(p.getX()/Grid.WIDHT)][(int) Math.floor(p.getY()/Grid.HEIGHT)].addCharector(p);
		player.add(p);
		colliders.add(p);
		updatePlayerDistance();
	}
	
	public void addFixedObject(FixedObject o) {
		
	}
	
	public void draw(GraphicsContext gc) {
		for(int i=0;i<width;i++) {
			for(int j=0;j<height;j++) {
				grid[i][j].draw(gc);
			}
		}
	}
}
