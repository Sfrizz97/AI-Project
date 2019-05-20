package Frogger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class PlayScene implements GameScene {
	
	protected SceneManager manager;
	protected World world;
	protected PlayerObject player;
	protected ObstacleManager obManager;
	private int death_count;
	
	public PlayScene(SceneManager sceneManager) {
		this.manager = sceneManager;
		this.world = new World();
		this.player = new PlayerObject(12, 7, this.world);
		this.obManager = new ObstacleManager(this.world, this.player);
		this.death_count = 0;
	}

	public void update() {
		if((player.checkWin() || player.isDead()) && !player.getJumping()) {
			if(player.checkWin()) {
				System.out.println("Vittoria");
			}
			if(player.isDead()) {
				System.out.println("Sconfitta");
			}
			obManager.removeAll();
			manager.switchToMenu();
		}
		player.update();
		obManager.update();
		if(obManager.voidBelow(player.row, player.column) && player.row<6) {
			System.out.println("fallen at: " + player.row + " " + player.column);
		}
		if(obManager.collide(player.row, player.column)) {
			System.out.println("investita");
		}
		if((player.jumpInWater() && obManager.voidBelow(player.row, player.column)) || obManager.collide(player.row, player.column) || player.outOfBound()) {
			player.removeLife();
			this.death_count++;
			System.out.println("Death count: " + this.death_count);
		}
		if(!obManager.voidBelow(player.row, player.column)) {
			player.startMoving();
		}
//		world.print();
	}

	public void draw() {
		world.draw();
		obManager.draw();
		player.draw();
//		Constants.context.getCanvas().drawText("Death: " + death_count, 300, 0);
	}

	public void handleEvent(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void handle(KeyEvent arg0) {
				if(player.getJumping() == false) {
					switch(arg0.getCode()) {
					case UP:
						player.jump(Direction.UP);
						break;
					case DOWN:
						player.jump(Direction.DOWN);
						break;
					case LEFT:
						player.jump(Direction.LEFT);
						break;
					case RIGHT:
						player.jump(Direction.RIGHT);
						break;
					default:
						break;
					}
				}
			}
			
		});
	}

}
