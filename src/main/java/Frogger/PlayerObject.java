package Frogger;
import Frogger.World.Goal;
import javafx.scene.image.Image;

public class PlayerObject extends GameObject {
	
	private Direction direction;
	private Image frog;
	private boolean jumping;
	private boolean moving;
	private boolean playing;
	private int n_life;
	
	public PlayerObject(int row, int column, World world) {
		super(row, column, world);
		this.direction = Direction.IDLE;
		this.frog = Constants.i_up;
		this.jumping = false;
		this.moving = false;
		this.playing = true;
		this.n_life = 3;
		updateGraphicsCoords();
		animation();
	}
	
	private void updateGraphicsCoords() {
		Constants.FROG_Y = (this.row*50)+50;
		Constants.FROG_X = this.column*50;
	}

	@Override
	public void update() {
		if(world.checkPosition(this)) {
			setInRow(world.getRow()-1);
			setInColumn(world.getColumn()/2);
			updateGraphicsCoords();
		}
	}
	
	public boolean jumpInWater() {
		if(this.row <= 5 && this.row >= 1) {
			return true;
		}
		return false;
	}
	
	public void removeLife() {
		this.n_life -= 1;
		setInRow(world.getRow()-1);
		setInColumn(world.getColumn()/2);
		moving = false;
		updateGraphicsCoords();
	}
	
	public boolean checkWin() {
		for(Goal g: world.getGoals()) {
			if(!g.isStepped()) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public void draw() {
		for(int i = 0; i < this.n_life ; i++) {
			Constants.context.drawImage(Constants.life_hearth, 100+i*50, 0);
		}
		Constants.context.drawImage(frog, Constants.FROG_X, Constants.FROG_Y);
	}
	
	public void jump(Direction dir) {
		if(dir == Direction.UP) {
			if(getRowIndex() - 1 >= 0) {
				if(getRowIndex() == 1 && (getColumnIndex() != 2 && getColumnIndex() != 5 && getColumnIndex() != 8 && getColumnIndex() != 11)) {
					this.jumping = false;
				} 
				else if (getRowIndex() == 1) {
					this.jumping = true;
					for(Goal g : world.getGoals()) {
						if(g.getGoalColumn() == getColumnIndex() && g.isStepped()) {
							this.jumping = false;
						}
					}
				} 
				else {
					this.jumping = true;
				}
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
				while(playing) {
					try {
						Thread.sleep(1);
						if(jumping) {
							if(direction == Direction.UP) {
								setInRow(getRowIndex()-1);
							} else if(direction == Direction.DOWN) {
								setInRow(getRowIndex()+1);
							} else if(direction == Direction.LEFT) {
								setInColumn(getColumnIndex()-1);
							} else if(direction == Direction.RIGHT) {
								setInColumn(getColumnIndex()+1);
							}
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
									if(Constants.FROG_X % 50 == 0 && Constants.FROG_Y % 50 == 0) {
										break;
									}
									Thread.sleep(5);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							if(direction == Direction.UP) {
								frog = Constants.i_up;
							} else if(direction == Direction.DOWN) {
								frog = Constants.i_down;
							} else if(direction == Direction.LEFT) {
								frog = Constants.i_left;
							} else if(direction == Direction.RIGHT) {
								frog = Constants.i_right;
							}
							
						}
						direction = Direction.IDLE;
						updateGraphicsCoords();
					} catch(Exception e){
						e.printStackTrace();
					}				
					jumping = false;
				}
			}
		}.start();
	}
	
	protected void moveFrog(boolean dir) {
		if(dir && ((Constants.FROG_X - 1) % 50 == 0)) {
			this.column += 1;
		} else if(!dir & (Constants.FROG_X + 13) % 50 == 0) {
			this.column -= 1;
		}
	}

	public boolean getJumping() {
		return this.jumping;
	}
	
	public int getNLife() {
		return this.n_life;
	}

	public boolean isDead() {
		if(this.n_life == 0) {
			this.playing = false;
			return true;
		}
		return false;
	}

	public void startMoving() {
		this.moving = true;
	}
	
	public boolean isMoving() {
		return this.moving;
	}
	
	public boolean outOfBound() {
		if(this.column < 0 || this.column >= world.getColumn()) {
			return true;
		}
		return false;
	}
	
	public void shift() {
		if(getRowIndex() == 2 || getRowIndex() == 5) {
			setInColumn(getColumnIndex()-1);
		} else if(getRowIndex() == 1 || getRowIndex() == 3 || getRowIndex() == 4) {
			setInColumn(getColumnIndex()+1);
		}
		updateGraphicsCoords();
	}

	@Override
	protected boolean getDirection() {
		return false;
	}
}
