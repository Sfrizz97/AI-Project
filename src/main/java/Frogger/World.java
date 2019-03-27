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
		if(element.getRowIndex() >= 0 && element.getRowIndex() < this.row && element.getColumnIndex() >= 0 && element.getColumnIndex() < this.column) {
			this.world[element.getRowIndex()][element.getColumnIndex()] = element;
		}
	}
	
	public void clearCell(int row, int column) {
		if(column >= 0 && column < this.column) {
			this.world[row][column] = null;
		}
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
	
	public void print() {
		System.out.println("__________________________");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				if(this.world[i][j] instanceof PlayerObject) {
					System.out.print("F");
				} else if (this.world[i][j] instanceof ObstacleObject) {
					System.out.print("O");
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
		System.out.println("__________________________");
		System.out.println();
		System.out.println();
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

	public void draw() {
		Constants.context.drawImage(Constants.topinfo, 0, 0);
		Constants.context.drawImage(Constants.life_text, 0, 0);
		Constants.context.drawImage(Constants.world, 0, 50);
		for(Goal g : goals) {
			if(g.isStepped()) {				
				Constants.context.drawImage(Constants.flower_goal, 50*g.getGoalColumn(), (50*g.getGoalRow())+50);
			}
		}
	}

	public void clearPlayer(int row, int column) {
		if(this.world[row][column] instanceof PlayerObject) {
			this.world[row][column] = null;
		}
		
	}

	public GameObject getElement(int row, int column) {
		return this.world[row][column];
	}

	public boolean turtleSpawnable(int row, Size size) {
		for(int i = 0; i < 4; i++) {
			if(size == Size.SMALL) {
				if(this.world[row][this.column - 1 - i] instanceof ObstacleObject ) {
					return false;
				}
			} else if(size == Size.MEDIUM && i < 3) {
				if(this.world[row][this.column - 1 - i] instanceof ObstacleObject ) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean logSpawnable(int row, Size size) {
		for(int i = 0; i < 5; i++) {
			if(size == Size.SMALL) {
				if(this.world[row][i] instanceof ObstacleObject ) {
					return false;
				}
			} else if(size == Size.MEDIUM && i < 4) {
				if(this.world[row][i] instanceof ObstacleObject ) {
					return false;
				}
			} else if(size == Size.LARGE && i < 3) {
				if(this.world[row][i] instanceof ObstacleObject ) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean carSpawnable(int row, boolean dir) {
		for(int i = 0; i < 4; i++) {
			if(dir) {
				if(this.world[row][i] instanceof ObstacleObject ) {
					return false;
				}
			} else {
				if(this.world[row][this.column - 1 - i] instanceof ObstacleObject ) {
					return false;
				}
			}
		}
		return true;
	}
}
