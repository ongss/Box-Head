package game_weapon;

public class Pistol extends Weapon{

	private static String NAME = "Pistol";
	private static double QUALITY = Double.POSITIVE_INFINITY;
	private static int DAMAGE = 5;
	private static boolean PASS = false;
	private static boolean HOLD = true;
	
	public Pistol() {
		super(NAME, QUALITY, DAMAGE, PASS, HOLD);
	}

	@Override
	public void fire() {
		// TODO Auto-generated method stub
		decreaseQuality();
	}

}
