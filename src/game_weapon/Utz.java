package game_weapon;

public class Utz extends Weapon{
	
	private static String NAME = "Utz";
	private static double QUALITY = 100;
	private static int DAMAGE = 5;
	private static boolean PASS = false;
	private static boolean HOLD = true;
	
	public Utz() {
		super(NAME, QUALITY, DAMAGE, PASS, HOLD);
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		decreaseQuality();
	}
}
