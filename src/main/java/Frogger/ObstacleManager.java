package Frogger;
import java.util.ArrayList;
import java.util.Iterator;

public class ObstacleManager {
	
	private ArrayList<ObstacleObject> cars;
	private ArrayList<ObstacleObject> water;
	private final int minCar = 7;
	private final int maxCar = 11;
	private final int minWater = 1;
	private final int maxWater = 5;
	private World world;
	
	public ObstacleManager(World world) {
		this.world = world;
		cars = new ArrayList<ObstacleObject>();
		water = new ArrayList<ObstacleObject>();
		populateObstacles();
	}

	public void draw() {
		for(ObstacleObject oo : cars) {
			oo.draw();
		}
		for(ObstacleObject oo : water) {
			oo.draw();
		}
	}
	
	public void update() {
		for(Iterator<ObstacleObject> iterator = cars.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			if(oo.isRemovable()) {
				iterator.remove();
			} else {
				oo.update();
			}
		}
		for(Iterator<ObstacleObject> iterator = water.iterator(); iterator.hasNext();) {
			ObstacleObject oo = iterator.next();
			if(oo.isRemovable()) {
				iterator.remove();
			} else {
				oo.update();
			}
		}
	}
	
	private void populateObstacles() {
		new Thread() {
			public void run() {
				while(true) {
					int randcars = (int)( Math.random() * (maxCar - minCar + 1)) + minCar;
					int randwater = (int)( Math.random() * (maxWater - minWater + 1)) + minWater;
					if(randcars % 2 == 0) {
						cars.add(new ObstacleObject(randcars, 13, false, Size.XSMALL, world));
					} else {
						cars.add(new ObstacleObject(randcars, 0, true, Size.XSMALL, world));
					}
					if(randwater == 1 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.MEDIUM, world));
					} else if(randwater == 2 ) {
						water.add(new ObstacleObject(randwater, 13, false, Size.SMALL, world));
					} else if(randwater == 3 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.LARGE, world));
					} else if(randwater == 4 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.SMALL, world));
					} else if(randwater == 5 ) {
						water.add(new ObstacleObject(randwater, 13, false, Size.MEDIUM, world));
					}
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
