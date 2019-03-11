package Frogger;
import javafx.scene.Scene;

public interface GameScene {
	public void update();
	public void draw();
	public void handleEvent(Scene scene);
}
