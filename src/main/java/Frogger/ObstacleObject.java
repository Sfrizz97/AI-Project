package Frogger;

import javafx.scene.image.Image;

public class ObstacleObject extends GameObject {
	
	private boolean direction; //true for left -> right, false for right -> left
	private Size size = Size.XSMALL; //per default size : SMALL
	private boolean crossing = true;
	private Image obstacle;
	private int g_pos_y;
	private int g_pos_x;
	
	public ObstacleObject(int row, int column, boolean direction, Size size, World world) {
		super(row, column, world);
		this.direction = direction;
		this.size = size;
		this.g_pos_y = (row*50)+50;
		if(direction) {
			if(row >= 7 && row <= 11) {
				this.g_pos_x = (column*50)-50;
				this.obstacle = Constants.car_lr;
			} else if(row == 1 || row == 3 || row == 4) {
				if(this.size == Size.SMALL) {
					this.obstacle = Constants.log_small;
					this.g_pos_x = (column*50)-50*2;
				} else if(this.size == Size.MEDIUM) {
					this.obstacle = Constants.log_medium;
					this.g_pos_x = (column*50)-50*3;
				} else if(this.size == Size.LARGE) {
					this.obstacle = Constants.log_large;
					this.g_pos_x = (column*50)-50*4;
				}
			}
		} else {
			if(row >= 7 && row <= 11) {
				this.g_pos_x = (column*50)+50;
				this.obstacle = Constants.car_rl;
			} else if(row == 2 || row == 5) {
				if(this.size == Size.SMALL) {
					this.g_pos_x = (column*50)-50*2;
				} else if(this.size == Size.MEDIUM) {
					this.g_pos_x = (column*50)-50*3;
				}
				this.obstacle = Constants.turtle_rl;
			}
		}
		animation();
	}

	private synchronized void animation() {
		new Thread() {
			public void run() {
				int times = 0;
				switch(size) {
					case XSMALL:
						times = 1;
						break;
					case SMALL:
						times = 2;
						break;
					case MEDIUM:
						times = 3;
						break;
					case LARGE:
						times = 4;
						break;
					default:
						break;
				}
				while(crossing) {
					for(int i = 0; i < times; i++) {
						for(int movement = 0; movement < 50; movement++) {
							try {
								if(direction) {
									g_pos_x++;
								} else {
									g_pos_x--;
								}
								Thread.sleep(20);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						for(int j = 0; j < times; j++) {
							
						}
						if(direction) {
							if(getColumnIndex() + 1 >= world.getColumn()) {
								crossing = false;
							} else {
								setInColumn(getColumnIndex()+1);
							}
						} else {
							if(getColumnIndex() -1 < 0) {
								crossing = false;
							} else {
								setInColumn(getColumnIndex()-1);
							}
						}
						if(crossing) {
							updateCoords();
						}
					}
				}
				for(int movement = 0; movement < 50; movement++) {
					try {
						if(direction) {
							g_pos_x++;
						} else {
							g_pos_x--;
						}
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}.start();
	}

	private void updateCoords() {
		if(this.direction) {			
			this.g_pos_x = (getColumnIndex()*50)-50;
		} else {
			this.g_pos_x = (getColumnIndex()*50)+50;
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw() {
		if(this.row == 2 || this.row == 5) {
			int times = 0;
			if(this.size == Size.SMALL) {
				times = 2;
			} else if(this.size == Size.MEDIUM) {
				times = 3;
			}
			for(int i = 0; i < times; i++) {
				Constants.context.drawImage(obstacle, this.g_pos_x+(50*i), this.g_pos_y);
			}
		} else {
			Constants.context.drawImage(obstacle, this.g_pos_x, this.g_pos_y);
		}
	}


}
