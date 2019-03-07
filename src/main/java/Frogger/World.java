package Frogger;
import java.util.ArrayList;

public class World {
	
	private GameObject[][] world;
	private final int row = 13;
	private final int column = 14;
	private ArrayList<Goal> goals;
	private Goal g1;
	private Goal g2;
	private Goal g3;
	private Goal g4;
	
	public World() {
		this.world = new GameObject[row][column];
		this.goals = new ArrayList<Goal>();
		g1 = new Goal(2);
		g2 = new Goal(5);
		g3 = new Goal(8);
		g4 = new Goal(11);
		goals.add(g1);
		goals.add(g2);
		goals.add(g3);
		goals.add(g4);
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public ArrayList<Goal> getGoals() {
		return this.goals;
	}
	
	public void setElement(GameObject element) {
		this.world[element.getRowIndex()][element.getColumnIndex()] = element;
	}
	
	public boolean checkPosition(PlayerObject playerObject) {
		for(Goal g: goals) {
			if(playerObject.getColumnIndex() == g.getGoalColumn() && playerObject.getRowIndex() == g.getGoalRow() && !g.isStepped()) {
				g.step();
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		goals.clear();
		goals.add(g1);
		goals.add(g2);
		goals.add(g3);
		goals.add(g4);
	}
	
	protected class Goal {
		
		private int column;
		private int row = 0;
		private boolean stepped;
		
		public Goal(int column) {
			this.column = column;
			this.stepped = false;
		}
		
		public void step() {
			this.stepped = true;
		}
		
		public int getGoalColumn() {
			return this.column;
		}
		
		public int getGoalRow() {
			return this.row;
		}
		
		public boolean isStepped() {
			return this.stepped;
		}
	}
}
