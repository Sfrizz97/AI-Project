package Frogger;

import javafx.scene.image.Image;

public class ObstacleObject extends GameObject {
	
	protected boolean direction; //true for left -> right, false for right -> left
	protected Size size = Size.XSMALL; //per default size : SMALL
	protected boolean crossing = true;
	protected Image obstacle;
	protected int g_pos_y;
	protected int g_pos_x;
	protected int times;
	protected boolean removable;
	
	public ObstacleObject(int row, int column, boolean direction, Size size, World world) {
		super(row, column, world);
		this.direction = direction;
		this.size = size;
		this.g_pos_y = (row*50)+50;
		if(direction) {
			if(row >= 7 && row <= 11) {
				this.obstacle = Constants.car_lr;
				this.g_pos_x = (column*50);
			} else if(row == 1 || row == 3 || row == 4) {
				if(this.size == Size.SMALL) {
					this.g_pos_x = (column*50)-50*1;
					this.obstacle = Constants.log_small;
				} else if(this.size == Size.MEDIUM) {
					this.g_pos_x = (column*50)-50*2;
					this.obstacle = Constants.log_medium;
				} else if(this.size == Size.LARGE) {
					this.g_pos_x = (column*50)-50*3;
					this.obstacle = Constants.log_large;
				}
			}
		} else {
			if(row >= 7 && row <= 11) {
				this.obstacle = Constants.car_rl;
			} else if(row == 2 || row == 5) {
				this.obstacle = Constants.turtle_rl;
			}
			this.g_pos_x = (column*50);
		}
		switch(size) {
			case XSMALL:
				this.times = 1;
				break;
			case SMALL:
				this.times = 2;
				break;
			case MEDIUM:
				this.times = 3;
				break;
			case LARGE:
				this.times = 4;
				break;
			default:
				break;
		}
		this.removable = false;
	}
	
	public boolean isRemovable() {
		return this.removable;
	}

	@Override
	public void update() {
		GameObject temp = this;
		int new_c_index = this.getColumnIndex();
		for(int i = 0; i < times ; i++) {
			if(direction) {
				temp.setInColumn(new_c_index - times + 1 + i);
			} else {
				temp.setInColumn(new_c_index + times - 1 - i);
			}
			this.world.setElement(temp);
		}
		if(direction) {
			world.clearCell(temp.getRowIndex(), new_c_index - times);
		} else {
			world.clearCell(temp.getRowIndex(), new_c_index + times);
		}
	}

	@Override
	public void draw() {
		if(this.row == 2 || this.row == 5) {
			for(int i = 0; i < times; i++) {
				Constants.context.drawImage(obstacle, this.g_pos_x+(50*i), this.g_pos_y);
			}
		} else {
			Constants.context.drawImage(obstacle, this.g_pos_x, this.g_pos_y);
		}
	}
	
	public void forceRemove() {
		this.removable = true;
	}
	
	public void shift() {
		if(direction) {	
			if(getColumnIndex() - times + 1 >= world.getColumn()) {
				this.removable = true;
			} else {
				setInColumn(getColumnIndex() + 1);
				g_pos_x += 50;
				
			}
		} else {
			if(getColumnIndex() + times - 1 < 0) {
				this.removable = true;
			} else {
				setInColumn(getColumnIndex() - 1);
				g_pos_x -= 50;
			}
		}
	}
	
	@Override
	public boolean getDirection() {
		return this.direction;
	}
	
}
