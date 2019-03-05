import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
	
	private int row; //posizione colonna
	private int column; //posizione riga
	protected World world = new World();
	
	public GameObject(int y, int x) {
		this.row = y;
		this.column = x;
	}
	
	public void setInRow(int y) {
		if(y >= 0 && y < world.getRow() - 1) {
			this.row = y;
		}
	}
	
	public int getRowElement() {
		return this.row;
	}
	
	public void setInColumn(int x) {
		if(x >= 0 && x < world.getColumn() - 1) {
			this.column = x;
		}
	}
	
	public int getColumnElement() {
		return this.column;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public void draw(GraphicsContext context) {}
}
