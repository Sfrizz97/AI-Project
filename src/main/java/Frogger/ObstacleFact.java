package Frogger;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("obstacle")
public class ObstacleFact {

	@Param(0)
	private int row;
	@Param(1)
	private int column;
	@Param(2)
	private int dir; //0 left->right, 1 right->left
	
	public ObstacleFact() {}
	
	public ObstacleFact(int row, int column, int dir) {
		this.row = row;
		this.column = column;
		this.dir = dir;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int row) {
		this.row = row;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public int getDir() {
		return this.dir;
	}
}
