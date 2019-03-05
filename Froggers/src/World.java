import java.util.ArrayList;

public class World {
	
	private GameObject[][] world;
	private final int row = 13;
	private final int column = 14;
	private ArrayList<Goal> goals;
	private Goal g1 = new Goal(2);
	private Goal g2 = new Goal(5);
	private Goal g3 = new Goal(8);
	private Goal g4 = new Goal(11);
	
	public World() {
		this.world = new GameObject[row][column];
		this.goals = new ArrayList<Goal>();
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
	
	public void setElement(GameObject element) {
		this.world[element.getRowElement()][element.getColumnElement()] = element;
	}
	
	public boolean checkPosition(PlayerObject playerObject) {
		for(Goal g: goals) {
			if(playerObject.getColumnElement() == g.getGoalColumn() && playerObject.getRowElement() == g.getGoalRow()) {
				goals.remove(g);
				return true;
			}
		}
		return false;
	}
	
	public void reset() {
		if(goals.isEmpty()) {
			goals.add(g1);
			goals.add(g2);
			goals.add(g3);
			goals.add(g4);
		}
	}
	
	private class Goal {
		
		private int column;
		private int row = 0;
		
		public Goal(int row) {
			this.row = row;
		}
		
		public int getGoalColumn() {
			return this.column;
		}
		
		public int getGoalRow() {
			return this.row;
		}
	}
}
