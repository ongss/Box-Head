package game_physic;

public class Force2D extends Vector2D {
	Point2D pos;
	
	public Force2D(double posX,double posY,double sizeX,double sizeY) {
		super(sizeX,sizeY);
		pos = new Point2D(posX,posY);
	}
	
	public Force2D(Point2D p,Vector2D v) {
		super(v.getX(),v.getY());
		pos = new Point2D(p.getX(),p.getY());
	}
	
	@Override
	public Force2D multipleBy(double m) {
		super.multipleBy(m);
		return this;
	}
	
	@Override
	public Force2D inverse() {
		super.inverse();
		return this;
	}
	
	@Override
	public Force2D toUnit() {
		super.toUnit();
		return this;
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
