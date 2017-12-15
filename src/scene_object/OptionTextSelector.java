package scene_object;

import javafx.scene.canvas.GraphicsContext;

public class OptionTextSelector {
	private double posX,posY;
	private int n,selectIndex,count;
	private OptionText []option;
	
	public OptionTextSelector(int n,double x,double y){
		this.option = new OptionText[n];
		this.count = 0;
		this.n = n;
		this.selectIndex = 0;
		this.posX = x;
		this.posY = y;
	}
	
	public void addOption(String s) {
		// TODO: throw error if count > n;
		if(count == 0) option[count] = new OptionText(s,posX,posY+count*50,true);
		else option[count] = new OptionText(s,posX,posY+count*50,false);
		count++;
	}
	
	public void select(int index) {
		option[selectIndex].setSelect(false);
		option[index].setSelect(true);
		selectIndex = index;
	}
	
	public void moveUp() {
		if(getSelected() == 0) return;
		select(selectIndex-1);
	}
	
	public void moveDown() {
		if(getSelected() == n-1) return;
		select(selectIndex+1);
	}
	
	public int getSelected() {
		return selectIndex;
	}
	
	public void draw(GraphicsContext gc) {
		for(OptionText o: option) {
			o.draw(gc);
		}
	}
}
