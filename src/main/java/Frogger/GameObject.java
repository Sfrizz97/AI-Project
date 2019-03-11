package Frogger;

public abstract class GameObject {
	
	protected int row; //posizione colonna
	protected int column; //posizione riga
	protected World world;
	
	public GameObject(int row, int column, World world) {
		this.row = row;
		this.column = column;
		this.world = world;
	}
	
	public void setInRow(int row) {
		if(row >= 0 && row <= world.getRow() - 1) {
			this.row = row;
		}
	}
	
	public int getRowIndex() {
		return this.row;
	}
	
	public void setInColumn(int column) {
		if(column >= 0 && column <= world.getColumn() - 1) {
			this.column = column;
		}
	}
	
	public int getColumnIndex() {
		return this.column;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public abstract void update();
	
	public abstract void draw();
}
