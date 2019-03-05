import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class MainClass extends Application{
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
		Scene scene = new Scene(root, 700, 700);
		Canvas canvas = new Canvas(scene.getWidth(),scene.getHeight());
		GraphicsContext context = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		primaryStage.setTitle("Frogger");
		primaryStage.show();
		primaryStage.setScene(scene);
		GameManager game = new GameManager(context);
		
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				game.runGame(scene);
			}
			
		}.start();
	}
}
