package Frogger;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class SceneManager {
	
	private GameScene scene;
	
	public SceneManager() {
		this.scene = new MenuScene(this);
	}
	
	public void update() {
		scene.update();
	}
	
	public void draw(GraphicsContext context) {
		scene.draw();
	}
	
	public void handleEvent(Scene _scene) {
		scene.handleEvent(_scene);
	}

	public void switchToPlay() {
		this.scene = new PlayScene(this);
	}

	public void switchToMenu() {
		this.scene = new MenuScene(this);
	}
	
	public GameScene getGameScene() {
		return this.scene;
	}
}
