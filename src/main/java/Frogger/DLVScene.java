package Frogger;

import Frogger.World.Goal;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import javafx.scene.Scene;

public class DLVScene extends PlayScene {
	
	private static String encodingResource="encodings/temp";
	private Handler handler;
	private InputProgram facts;
	private InputProgram encoding;
	private Output output;
	
	public DLVScene(SceneManager sceneManager) {
		super(sceneManager);
		this.handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2"));
		this.facts = new ASPInputProgram();
		
		for(Goal g: this.world.getGoals()) {
			try {
				this.facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < this.world.getRow(); i++) {
			for(int j = 0; j < this.world.getColumn(); j++) {
				if(this.world.getElement(i, j) instanceof PlayerObject && this.world.getElement(i, j)!=null) {
					try {
						this.facts.addObjectInput(new PlayerFact(i,j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else if(this.world.getElement(i, j) instanceof ObstacleObject && this.world.getElement(i, j)!=null) {
					try {
						this.facts.addObjectInput(new ObstacleFact(i, j));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		this.handler.addProgram(facts);
		this.encoding = new ASPInputProgram();
		this.encoding.addFilesPath(encodingResource);
		this.handler.addProgram(encoding);
	}
	
	@Override
	public void update() {
		super.update();
		//ad ogni ciclo di update vengono aggiornati i fatti
		this.facts.clearAll();
		for(Goal g: this.world.getGoals()) {
			try {
				if(!g.isStepped()) {					
					this.facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for(int i = 1; i < this.world.getRow(); i++) {
			for(int j = 0; j < this.world.getColumn(); j++) {
				if(this.world.getElement(i, j) instanceof PlayerObject && this.world.getElement(i, j)!=null) {
					try {
						this.facts.addObjectInput(new PlayerFact(i,j));
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
		this.handler.removeAll();
		this.handler.addProgram(facts);
		this.handler.addProgram(encoding);
	}
	
	@Override
	public void handleEvent(Scene scene) {
		if(!this.player.getJumping()) {
			System.out.println(encoding.getPrograms());
			this.output = this.handler.startSync();
			AnswerSets answers = (AnswerSets) output;
			for(AnswerSet as: answers.getAnswersets()) {
				try {
					for(Object obj: as.getAtoms()) {
						System.out.println("ciao");
						if(obj instanceof JumpUp) {
							JumpUp ju = (JumpUp) obj;
							this.player.jump(ju.getDir());
						} else if (obj instanceof JumpDown) {
							JumpDown jd = (JumpDown) obj;
							this.player.jump(jd.getDir());
						} else if (obj instanceof JumpLeft) {
							JumpLeft jl = (JumpLeft) obj;
							this.player.jump(jl.getDir());
						} else if (obj instanceof JumpRight) {
							JumpRight jr = (JumpRight) obj;
							this.player.jump(jr.getDir());
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
