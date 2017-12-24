package game_weapon;

import java.util.ArrayList;
import java.util.List;

import game_object.Player;
import game_physic.DrawAble;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class WeaponManager implements DrawAble{
	List<Weapon> weapons;
	private static final Font font = new Font("Monospace", 15);
	public static final double POS_X = 0,POS_Y = -50;
	private Player player;
	private double posX,posY;
	private int ptr;
	
	public WeaponManager(Player player) {
		this.weapons = new ArrayList<>();
		this.addWeapon(new Pistol());
		this.player = player;
		this.ptr = 0;
	}
	
	public void addWeapon(Weapon w) {
		for(Weapon x: weapons) {
			if(x.name == w.name) {
				x.restore();
				return;
			}
		}
		weapons.add(w);
	}
	
	public String getWeaponName() {
		return weapons.get(ptr).name;
	}
	
	public void shiftLeft() {
		if(this.ptr == 0) this.ptr = weapons.size() - 1; 
		else this.ptr = this.ptr-1;
	}
	
	public void shiftRight() {
		this.ptr = (this.ptr+1)%weapons.size();
	}
	
	public void updatePos() {
		this.posX = player.getX() + POS_X;
		this.posY = player.getY() + POS_Y;
	}
	
	public void fire() {
		weapons.get(ptr).fire();
		if(weapons.get(ptr).getQuality() == 0) {
			weapons.remove(ptr);
			shiftLeft();
		}
	}
	
	public void draw(GraphicsContext gc) {
		this.updatePos();
		gc.setFill(Color.AQUA);
		gc.setFont(font);
		gc.setTextAlign(TextAlignment.CENTER);
		if(weapons.get(ptr).getQuality() == Double.POSITIVE_INFINITY) gc.fillText(this.getWeaponName(),posX,posY);
		else gc.fillText(this.getWeaponName()+" : "+((int) weapons.get(ptr).getQuality()),posX,posY);
	}
}
