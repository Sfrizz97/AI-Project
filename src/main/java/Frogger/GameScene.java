package Frogger;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public interface GameScene {
	public void update();
	public void draw(GraphicsContext context);
	public void handleEvent(Scene scene);
}
