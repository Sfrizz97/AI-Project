package Frogger;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("jumpLeft")
public class JumpLeft {
	@Param(0)
	private int des_row;
	@Param(1)
	private int des_column;
	
	public JumpLeft(int row, int column) {
		this.des_row = row;
		this.des_column = column;
	}
	
	public JumpLeft() {}
	
	public int getDes_row() {
		return des_row;
	}

	public void setDes_row(int des_row) {
		this.des_row = des_row;
	}

	public int getDes_column() {
		return des_column;
	}

	public void setDes_column(int des_column) {
		this.des_column = des_column;
	}
	
	public String toString() {
		return des_row + " " + des_column + " LEFT" ;
	}
}
