import javafx.scene.canvas.GraphicsContext;

public class PlayerObject extends GameObject {
	
	private Direction direction;
	
	public PlayerObject(int row, int column) {
		super(row, column);
		this.direction = Direction.IDLE;
	}

	public void checkPosition() {
		if(world.checkPosition(this)) {
			setInRow(world.getRow()-1);
			setInColumn(world.getColumn()/2);
		}
	}
	
	@Override
	public void draw(GraphicsContext context) {
		context.drawImage(Constants.i_up, 50*getColumnElement(), (50*getRowElement())+50);
	}

}
