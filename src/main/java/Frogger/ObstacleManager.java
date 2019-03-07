package Frogger;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class ObstacleManager {
	
	private ArrayList<ObstacleObject> cars;
	private ArrayList<ObstacleObject> water;
	private final int minCar = 7;
	private final int maxCar = 11;
	private final int minWater = 1;
	private final int maxWater = 5;
	
	public ObstacleManager() {
		cars = new ArrayList<ObstacleObject>();
		water = new ArrayList<ObstacleObject>();
		populateObstacles();
		drawObstacles();
	}

	public void draw(GraphicsContext context) {
		for(ObstacleObject oo : cars) {
			oo.draw();
		}
		for(ObstacleObject oo : water) {
			oo.draw();
		}
	}
	
	private void populateObstacles() {
		new Thread() {
			public void run() {
				while(true) {
					int randcars = (int)( Math.random() * (maxCar - minCar + 1)) + minCar;
					int randwater = (int)( Math.random() * (maxWater - minWater + 1)) + minWater;
					if(randcars % 2 == 0) {
						cars.add(new ObstacleObject(randcars, 13, false, Size.SMALL));
					} else {
						cars.add(new ObstacleObject(randcars, 0, true, Size.SMALL));
					}
					if(randwater == 1 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.LARGE));
					} else if(randwater == 2 ) {
						water.add(new ObstacleObject(randwater, 13, false, Size.MEDIUM));
					} else if(randwater == 3 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.XLARGE));
					} else if(randwater == 4 ) {
						water.add(new ObstacleObject(randwater, 0, true, Size.SMALL));
					} else if(randwater == 5 ) {
						water.add(new ObstacleObject(randwater, 13, false, Size.LARGE));
					}
					
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	private void drawObstacles() {
		// TODO Auto-generated method stub
		
	}
}
