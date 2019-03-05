import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public interface GameScene {
	void update();
	void draw(GraphicsContext context);
	void handleEvent(Scene scene);
}
