package Frogger;
import Frogger.World.Goal;
import javafx.scene.image.Image;

public class PlayerObject extends GameObject {
	
	private Direction direction;
	private Image frog;
	boolean jumping;
	
	public PlayerObject(int row, int column) {
		super(row, column);
		this.direction = Direction.IDLE;
		this.frog = Constants.i_up;
		this.jumping = false;
		Constants.FROG_Y = (50*row)+50;
		Constants.FROG_X = 50*column;
		animation();
	}
	
	@Override
	public void update() {
		if(world.checkPosition(this)) {
			setInRow(world.getRow()-1);
			setInColumn(world.getColumn()/2);
			updateCoords();
		}
	}
	
	@Override
	public void draw() {
		for(Goal g : world.getGoals()) {
			if(g.isStepped()) {				
				Constants.context.drawImage(Constants.flower_goal, 50*g.getGoalColumn(), (50*g.getGoalRow())+50);
			}
		}
		Constants.context.drawImage(frog, Constants.FROG_X, Constants.FROG_Y);
	}
	
	public void jump(Direction dir) {
			if(dir == Direction.UP) {
				if(getRowIndex() - 1 >= 0/* && (getColumnIndex() != 2 && getColumnIndex() != 5 && getColumnIndex() != 8 && getColumnIndex() != 11)*/) {
					this.jumping = true;
				}
			} else if(dir == Direction.DOWN) {
				if(getRowIndex() + 1 <= world.getRow() - 1) {
					this.jumping = true;
				}
			} else if(dir == Direction.LEFT) {
				if(getColumnIndex() - 1 >= 0) {
					this.jumping = true;
				}
			} else if(dir == Direction.RIGHT) {
				if(getColumnIndex() + 1 <= world.getColumn() - 1) {
					this.jumping = true;
				}
			}
		this.direction = dir;
	}
	
	private synchronized void animation() {
		new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1);
						if(jumping) {
							for(int movement = 0; movement < 50; movement++) {
								try {
									if(direction == Direction.UP) {
										frog = Constants.j_up;
										Constants.FROG_Y--;
									} else if(direction == Direction.DOWN) {
										frog = Constants.j_down;
										Constants.FROG_Y++;
									} else if(direction == Direction.LEFT) {
										frog = Constants.j_left;
										Constants.FROG_X--;
									} else if(direction == Direction.RIGHT) {
										frog = Constants.j_right;
										Constants.FROG_X++;
									}
									Thread.sleep(10);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							if(direction == Direction.UP) {
								frog = Constants.i_up;
								setInRow(getRowIndex()-1);
							} else if(direction == Direction.DOWN) {
								frog = Constants.i_down;
								setInRow(getRowIndex()+1);
							} else if(direction == Direction.LEFT) {
								frog = Constants.i_left;
								setInColumn(getColumnIndex()-1);
							} else if(direction == Direction.RIGHT) {
								frog = Constants.i_right;
								setInColumn(getColumnIndex()+1);
							}
							jumping = false;
						}
						updateCoords();
						direction = Direction.IDLE;
					} catch(Exception e){
						e.printStackTrace();
					}
					
				}
			}
		}.start();
	}
	
	public boolean getJumping() {
		return this.jumping;
	}
	
	private void updateCoords() {
		Constants.FROG_Y = (getRowIndex()*50)+50;
		Constants.FROG_X = getColumnIndex()*50;
	}
}
