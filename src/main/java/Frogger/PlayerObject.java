package Frogger;
import Frogger.World.Goal;
import javafx.scene.image.Image;

public class PlayerObject extends GameObject {
	
	private Direction direction;
	private Image frog;
	private boolean jumping;
	//private boolean playing = false;
	private int n_life;
	private PlayerObject old_player;
	
	public PlayerObject(int row, int column, World world) {
		super(row, column, world);
		this.direction = Direction.IDLE;
		this.frog = Constants.i_up;
		this.jumping = false;
		//this.playing = true;
		this.n_life = 3;
		this.old_player = null;
		this.world.setElement(this);
		updateCoords();
		animation();
	}
	
	@Override
	public void update() {
		if(world.checkPosition(this)) {
			setInRow(world.getRow()-1);
			setInColumn(world.getColumn()/2);
			updateCoords();
		}
		world.setElement(this);
//		if(this.old_player != null) {
//			System.out.println("old p coords " + this.old_player.getRowIndex() + " " + this.old_player.getColumnIndex());
//			System.out.println("p coords " + this.getRowIndex() + " " + this.getColumnIndex());
//			world.setElement(this);
//			world.clearCell(this.old_player.getRowIndex(), this.old_player.getColumnIndex());
//			this.old_player = null;
//		}
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
		updateCoords();
	}
	public boolean checkWin() {
		for(Goal g: world.getGoals()) {
			if(!g.isStepped()) {
				return false;
			}
		}
		//this.playing = false;
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
					if(jumping) {
						this.old_player = this;
					}
					
				} 
				else {
					this.old_player = this;
					this.jumping = true;
				}
			}
		} else if(dir == Direction.DOWN) {
			if(getRowIndex() + 1 <= world.getRow() - 1) {
				this.old_player = this;
				this.jumping = true;
			}
		} else if(dir == Direction.LEFT) {
			if(getColumnIndex() - 1 >= 0) {
				this.old_player = this;
				this.jumping = true;
			}
		} else if(dir == Direction.RIGHT) {
			if(getColumnIndex() + 1 <= world.getColumn() - 1) {
				this.old_player = this;
				this.jumping = true;
			}
		}
		this.direction = dir;
	}
	
	private synchronized void animation() {
		new Thread() {
			public void run() {
				while(n_life > 0) {
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
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							if(direction == Direction.UP) {
								frog = Constants.i_up;
								setInRow(getRowIndex()-1);
								world.clearPlayer(getRowIndex() + 1, getColumnIndex());
							} else if(direction == Direction.DOWN) {
								frog = Constants.i_down;
								setInRow(getRowIndex()+1);
								world.clearPlayer(getRowIndex() - 1, getColumnIndex());
							} else if(direction == Direction.LEFT) {
								frog = Constants.i_left;
								setInColumn(getColumnIndex()-1);
								world.clearPlayer(getRowIndex(), getColumnIndex() + 1);
							} else if(direction == Direction.RIGHT) {
								frog = Constants.i_right;
								setInColumn(getColumnIndex()+1);
								world.clearPlayer(getRowIndex(), getColumnIndex() - 1);
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
	
	public int getNLife() {
		return this.n_life;
	}
	
	private void updateCoords() {
		Constants.FROG_Y = (this.row*50)+50;
		Constants.FROG_X = this.column*50;
	}

	public boolean isDead() {
		if(this.n_life == 0) {
			return true;
		}
		return false;
	}
}
