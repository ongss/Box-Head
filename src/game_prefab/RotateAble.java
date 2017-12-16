package game_prefab;

public interface RotateAble {
	final double TERMINAL_ANGULAR_VELOCITY = 20;
	void rotateTo(double deg);
	void rotate(double deg);
}
