package Frogger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class MainClass extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
		final Scene scene = new Scene(root, 700, 700);
		Canvas canvas = new Canvas(scene.getWidth(),scene.getHeight());
		Constants.context = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		primaryStage.setTitle("Frogger");
		primaryStage.show();
		primaryStage.setScene(scene);
		final GameManager game = new GameManager();
		
		new AnimationTimer() {

			public void handle(long now) {
				game.runGame(scene);
			}
			
		}.start();
	}
}
