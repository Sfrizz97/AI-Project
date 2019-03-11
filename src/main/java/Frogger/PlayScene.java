package Frogger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;

public class PlayScene implements GameScene {
	
	protected SceneManager manager;
	private World world;
	private PlayerObject player;
	private ObstacleManager obManager;
	
	public PlayScene(SceneManager sceneManager) {
		this.manager = sceneManager;
		this.world = new World();
		this.player = new PlayerObject(12, 7, this.world);
		this.obManager = new ObstacleManager(this.world);		
	}

	public void update() {
		player.update();
		if(player.checkWin()) {
			manager.switchToMenu();
		}
	}

	public void draw() {
		world.draw();
		obManager.draw();
		player.draw();
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
