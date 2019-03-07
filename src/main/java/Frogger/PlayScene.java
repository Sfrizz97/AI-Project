package Frogger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

public class PlayScene implements GameScene {
	
	protected SceneManager manager;
	private PlayerObject player;
	private ObstacleManager obManager;
	
	public PlayScene(SceneManager sceneManager) {
		this.manager = sceneManager;
		this.player = new PlayerObject(12, 7);
		this.obManager = new ObstacleManager();		
	}

	public void update() {
		player.update();
	}

	public void draw() {
		Constants.context.drawImage(Constants.topinfo, 0, 0);
		Constants.context.drawImage(Constants.world, 0, 50);
		player.draw();
		//obManager.draw(context);
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
