package Frogger;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class GameManager {
	
	private SceneManager manager;
	protected GraphicsContext context;
	
	public GameManager(GraphicsContext context) {
		manager = new SceneManager();
		this.context = context;
	}
	
	public void runGame(Scene scene) {
		update();
		draw();
		handleEvent(scene);
	}
	
	private void update() {
		manager.update();
	}
	
	private void draw() {
		manager.draw(this.context);
	}
	
	private void handleEvent(Scene scene) {
		manager.handleEvent(scene);
	}

	
}
