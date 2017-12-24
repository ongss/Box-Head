package game_prefab;



import game_object.Player;
import game_physic.DrawAble;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HpBar implements DrawAble {
	public static final double WIDTH = 40;
	public static final double HEIGHT = 10;
	public static final double POS_X = 0;
	public static final double POS_Y = -40;
	public static final Color[] color = {Color.RED,Color.ORANGE,Color.YELLOWGREEN,Color.LIGHTGREEN};
	private double posX,posY;
	private Player player;
	private int maxHp;
	private int curHp;
	
	
	public HpBar(Player player) {
		this.maxHp = player.HP;
		this.curHp = player.getCurrentHp();
		this.player = player;
	}
	
	public void update() {
		posX = player.getX() + POS_X;
		posY = player.getY() + POS_Y;
		curHp = player.getCurrentHp();
		if(curHp < 0) curHp = 0;
	}
	
	public double getPercentHp() {
		return this.curHp/this.maxHp*100;
	}

	@Override
	public void draw(GraphicsContext gc) {
		this.update();
		gc.setFill(color[(int) Math.floor(Math.abs(getPercentHp()-1)/25)]);
		gc.fillRect(this.posX-WIDTH/2, this.posY-HEIGHT/2, WIDTH*getPercentHp()/100, HEIGHT);
		gc.setStroke(Color.BLACK);
		gc.strokeRect(this.posX-WIDTH/2, this.posY-HEIGHT/2, WIDTH, HEIGHT);
	}
	
}
