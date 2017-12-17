package game_prefab;

public interface WalkAble {
	public static final double SPEED = 15;
	public void walk(String di);
	public void walkTo(double x,double y);
	public void still();
}
