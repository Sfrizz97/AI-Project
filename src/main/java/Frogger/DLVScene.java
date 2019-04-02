package Frogger;

import Frogger.World.Goal;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import javafx.scene.Scene;

public class DLVScene extends PlayScene {
	
	private static String encodingResource="encodings/frogger";
	private static Handler handler;
	private InputProgram facts;
	private InputProgram encoding;
	//private Output output;
	
	public DLVScene(SceneManager sceneManager) {
		super(sceneManager);
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));
		facts = new ASPInputProgram();
		
		for(Goal g: this.world.getGoals()) {
			try {
				facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < this.world.getRow(); i++) {
			for(int j = 0; j < this.world.getColumn(); j++) {
				if(this.world.getElement(i, j) instanceof PlayerObject && this.world.getElement(i, j)!=null) {
					try {
						facts.addObjectInput(new PlayerFact(i,j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if(this.world.getElement(i, j) instanceof ObstacleObject && this.world.getElement(i, j)!=null) {
					try {
						facts.addObjectInput(new ObstacleFact(i, j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		handler.addProgram(facts);
		encoding = new ASPInputProgram();
		encoding.addFilesPath(encodingResource);
		handler.addProgram(encoding);
	}
	
	@Override
	public void update() {
		super.update();
		//ad ogni ciclo di update vengono aggiornati i fatti
		for(Goal g: this.world.getGoals()) {
			try {
				if(!g.isStepped()) {					
					facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < this.world.getRow(); i++) {
			for(int j = 0; j < this.world.getColumn(); j++) {
				if(this.world.getElement(i, j) instanceof PlayerObject && this.world.getElement(i, j)!=null) {
					try {
						facts.addObjectInput(new PlayerFact(i,j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if(this.world.getElement(i, j) instanceof ObstacleObject && this.world.getElement(i, j)!=null) {
					try {
						facts.addObjectInput(new ObstacleFact(i, j));
						System.out.println("added obstacle fact at: " + i + " " + j);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	@Override
	public void handleEvent(Scene scene) {
		if(!this.player.getJumping()) {
			
		}
	}
	
}
