package game_prefab;

public interface TurnAble {
	public static final double TURN_SPEED = 15;
	public void turn(String di);
	public void turnTo(String di);
	
}
