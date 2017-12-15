package game_prefab;

public class Force2D extends Vector2D {
	Point pos;
	
	public Force2D(double posX,double posY,double sizeX,double sizeY) {
		super(sizeX,sizeY);
		pos = new Point(posX,posY);
	}
	
	public Force2D(Point p,Vector2D v) {
		super(v.getX(),v.getY());
		pos = new Point(p.getX(),p.getY());
	}
	
	public double getPosX() {
		return pos.getX();
	}
	
	public double getPosY() {
		return pos.getY();
	}
	
	public double getSizeX() {
		return super.getX();
	}
	
	public double getSizeY() {
		return super.getY();
	}
}
