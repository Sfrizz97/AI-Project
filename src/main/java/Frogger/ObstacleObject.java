package Frogger;
import javafx.scene.canvas.GraphicsContext;

public class ObstacleObject extends GameObject {
	
	private boolean direction; //true for left -> right, false for right -> left
	private Size size = Size.SMALL; //per default size : SMALL
	
	public ObstacleObject(int x, int y, boolean direction, Size size) {
		super(x, y);
		this.direction = direction;
		this.size = size;
	}
	@Override
	public void draw(GraphicsContext context) {
		// TODO Auto-generated method stub

	}

}
