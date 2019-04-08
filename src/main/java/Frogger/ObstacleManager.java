package Frogger;
import java.util.ArrayList;
import java.util.Iterator;

public class ObstacleManager {
	
	private ArrayList<ObstacleObject> cars;
	private ArrayList<ObstacleObject> water;
	private World world;
	private boolean spawnable;
	
	public ObstacleManager(World world) {
		this.world = world;
		this.cars = new ArrayList<ObstacleObject>();
		this.water = new ArrayList<ObstacleObject>();
		this.spawnable = true;
		populateObstacles();
	}
	
	public ArrayList<ObstacleObject> getCars() {
		return this.cars;
	}
	
	public ArrayList<ObstacleObject> getWater() {
		return this.water;
	}
	
	public void draw() {
		Iterator<ObstacleObject> iteratorCar = new ArrayList<ObstacleObject>(cars).iterator();
		while(iteratorCar.hasNext()) {
			ObstacleObject oo = iteratorCar.next();
			oo.draw();
		}
		Iterator<ObstacleObject> iteratorWater = new ArrayList<ObstacleObject>(water).iterator();
		while(iteratorWater.hasNext()) {
			ObstacleObject oo = iteratorWater.next();
			oo.draw();
		}
	}
	
	public void update() {
		
		Iterator<ObstacleObject> iteratorCar = new ArrayList<ObstacleObject>(cars).iterator();
		while(iteratorCar.hasNext()) {
			ObstacleObject oo = iteratorCar.next();
			if(oo.isRemovable()) {
				iteratorCar.remove();
			} else {
				oo.update();
			}
		}
		Iterator<ObstacleObject> iteratorWater = new ArrayList<ObstacleObject>(water).iterator();
		while(iteratorWater.hasNext()) {
			ObstacleObject oo = iteratorWater.next();
			if(oo.isRemovable()) {
				iteratorWater.remove();
			} else {
				oo.update();
			}
		}
	}
	
	private void populateObstacles() {
		new Thread() {
			public void run() {
				while(spawnable) {
					for(int i = 0; i < world.getRow()-1; i++) {
						if(i != 0 && i != 6) {
							for(int j = 0; j < world.getColumn(); j++) {
								if(i >= 1 && i <= 5) {
									if(i == 2 || i == 5) {
										if(i == 2) {
											if(world.turtleSpawnable(i, Size.SMALL)) {
												water.add(new ObstacleObject(i, 13, false, Size.SMALL, world));
											}
										} else if(i == 5) {
											if(world.turtleSpawnable(i, Size.MEDIUM)) {
												water.add(new ObstacleObject(i, 13, false, Size.MEDIUM, world));
											}
										}
									} else {
										if(i == 1) {
											if(world.logSpawnable(i, Size.MEDIUM)) {
												water.add(new ObstacleObject(i, 0, true, Size.MEDIUM, world));
											}
										} else if(i == 3) {
											if(world.logSpawnable(i, Size.LARGE)) {
												water.add(new ObstacleObject(i, 0, true, Size.LARGE, world));
											}
										} else if(i == 4) {
											if(world.logSpawnable(i, Size.SMALL)) {
												water.add(new ObstacleObject(i, 0, true, Size.SMALL, world));
											}
										}
									}
								} else if (i >= 7 && i <= 11) {
									if(i % 2 == 0) {
										if(world.carSpawnable(i, false)) {
											cars.add(new ObstacleObject(i, 13, false, Size.XSMALL, world));
										}
									} else {
										if(world.carSpawnable(i, true)) {
											cars.add(new ObstacleObject(i, 0, true, Size.XSMALL, world));
										}
									}
								}
							}
						}
					}
					try {
						Thread.sleep(20);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public boolean voidBelow(int row, int column) {
		for(Iterator<ObstacleObject> iterator = water.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			if(oo.size == Size.SMALL) {
				for(int i = 0; i < 2; i++) {
					if(oo.direction && oo.row == row && oo.column - i == column) {
						return false;
					} else if(oo.row == row && oo.column + i == column){
						return false;
					}
				}
			} else if (oo.size == Size.MEDIUM) {
				for(int i = 0; i < 3; i++) {
					if(oo.direction && oo.row == row && oo.column - i == column) {
						return false;
					} else if(oo.row == row && oo.column + i == column){
						return false;
					}
				}
			} else if (oo.size == Size.LARGE) {
				for(int i = 0; i < 4; i++) {
					if(oo.row == row && oo.column - i == column) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean collide(int row, int column) {
		for(Iterator<ObstacleObject> iterator = cars.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			if(oo.row == row && oo.column == column) {
				return true;
			}
		}
		return false;
	}

	public void removeAll() {
		this.spawnable = false;
		for(Iterator<ObstacleObject> iterator = cars.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			oo.forceRemove();
		}
		for(Iterator<ObstacleObject> iterator = water.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			oo.forceRemove();
		}
		cars.clear();
		water.clear();
	}
}
