package Frogger;
import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("player")
public class PlayerFact {

	@Param(0)
	private int row;
	@Param(1)
	private int column;
	
	public PlayerFact() {}
	
	public PlayerFact(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int row) {
		this.row = row;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
}
