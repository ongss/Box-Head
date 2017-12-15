package scene_object;

import game_prefab.DrawAble;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionText implements DrawAble {
	String text;
	boolean isHover;
	double posX,posY;
	
	private static final Font TITLE_FONT = new Font("Monospace", 80);
	private static final Font MENU_FONT = new Font("Monospace", 40);
	
	public OptionText(String t,double posX,double posY,boolean h){
		this.text = t;
		this.isHover = h;
		this.posX = posX;
		this.posY = posY;
	}
	
	public void setSelect(boolean b) {
		isHover = b;
	}
 
	public boolean isSelect() {
		return isHover;
	}
	
	public void draw(GraphicsContext gc) {
		if(isSelect()) {
			gc.setFill(Color.AQUA);
			gc.setFont(MENU_FONT);
			gc.fillText("> "+text+" <", posX, posY);
		}
		else {
			gc.setFill(Color.WHITE);
			gc.setFont(MENU_FONT);
			gc.fillText(text,posX,posY);
		}
		
	}
}
