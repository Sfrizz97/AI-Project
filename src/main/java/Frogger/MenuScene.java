package Frogger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class MenuScene implements GameScene {
	
	protected SceneManager manager;
	
	public MenuScene(SceneManager manager) {
		this.manager = manager;
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void draw() {
		Constants.context.drawImage(Constants.background , 0, 0);
		Constants.context.drawImage(Constants.play, 156, 429);
		Constants.context.drawImage(Constants.dlvbutton, 156, 546);
	}

	public void handleEvent(Scene scene) {
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {

			public void handle(MouseEvent arg0) {
				if(arg0.isPrimaryButtonDown()) {
					if(arg0.getX() >= 156 && arg0.getX() <= (156+Constants.BUTTON_WIDTH) 
							&& arg0.getY() >= 429 && arg0.getY() <= (429+Constants.BUTTON_HEIGHT)) {
						manager.switchToPlay();
					} else if (arg0.getX() >= 156 && arg0.getX() <= (156+Constants.BUTTON_WIDTH) 
							&& arg0.getY() >= 546 && arg0.getY() <= (546+Constants.BUTTON_HEIGHT)) {
						System.out.println("clicking dlv");
					}
				}
			}
		});
	}

}
