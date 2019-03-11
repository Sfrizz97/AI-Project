package Frogger;

import javafx.scene.image.Image;

public class ObstacleObject extends GameObject {
	
	private boolean direction; //true for left -> right, false for right -> left
	private Size size = Size.SMALL; //per default size : SMALL
	private boolean crossing = true;
	private Image car;
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
				this.car = Constants.car_lr;
			}
		} else {
			if(row >= 7 && row <= 11) {
				this.g_pos_x = (column*50)+50;
				this.car = Constants.car_rl;
			}
		}
		animation();
	}

	private synchronized void animation() {
		new Thread() {
			public void run() {
				while(crossing) {
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
					if(direction) {
						if(getColumnIndex() + 1 >= world.getColumn()) {
							crossing = false;
						} else {
							setInColumn(getColumnIndex()+1);
						}
						System.out.println(getColumnIndex());
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
		Constants.context.drawImage(car, this.g_pos_x, this.g_pos_y);
	}


}
