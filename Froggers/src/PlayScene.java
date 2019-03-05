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

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw(GraphicsContext context) {
		context.drawImage(Constants.topinfo, 0, 0);
		context.drawImage(Constants.world, 0, 50);
		player.draw(context);
		obManager.draw(context);
	}

	@Override
	public void handleEvent(Scene scene) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				switch(arg0.getCode()) {
					case UP:
						break;
					case DOWN:
						break;
					case LEFT:
						break;
					case RIGHT:
						break;
					default:
						break;
				}
			}
			
		});
	}

}
