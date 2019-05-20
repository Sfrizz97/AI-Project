package Frogger;

import Frogger.World.Goal;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
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
		
		/*
		 * Nel costruttore della scena dlv passo come fatto solo
		 * i goal e il player
		 */
		for(Goal g: this.world.getGoals()) {
			try {
				this.facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			this.facts.addObjectInput(new PlayerFact(this.player.getRowIndex(),this.player.getColumnIndex()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ASPMapper.getInstance().registerClass(JumpUp.class);
			ASPMapper.getInstance().registerClass(JumpDown.class);
			ASPMapper.getInstance().registerClass(JumpLeft.class);
			ASPMapper.getInstance().registerClass(JumpRight.class);
			ASPMapper.getInstance().registerClass(NoJump.class);
		} catch(Exception e) {
			e.printStackTrace();
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
		//passo come fatti Goal, Player e Ostacoli
		this.facts.clearAll();
		
		//Passo come fatti i goal che non sono stati raggiunti
		for(Goal g: this.world.getGoals()) {
			try {
				if(!g.isStepped()) {					
					this.facts.addObjectInput(new GoalFact(g.getGoalRow(), g.getGoalColumn()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//Aggiungo il player come fatto
		try {
			this.facts.addObjectInput(new PlayerFact(this.player.getRowIndex(),this.player.getColumnIndex()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Aggiungo un fatto di ostacolo per ogni cella che occupa
		for(int i = 1; i < this.world.getRow(); i++) {
			for(int j = 0; j < this.world.getColumn(); j++) {
				if(this.world.getElement(i, j) instanceof ObstacleObject && this.world.getElement(i, j)!=null) {
					try {
						if(this.world.getElement(i,j).getDirection()) {
							facts.addObjectInput(new ObstacleFact(i,j,0));							
						} else {
							facts.addObjectInput(new ObstacleFact(i,j,1));
						}
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
			this.output = this.handler.startSync();
			AnswerSets answers = (AnswerSets) output;
			for(AnswerSet as: answers.getAnswersets()) {
				//System.out.println(as.toString());
				try {
					//System.out.println(as.getAtoms().toString());
					for(Object obj: as.getAtoms()) {
						if(obj instanceof JumpUp) {
							this.player.jump(Direction.UP);
						} else if (obj instanceof JumpDown) {
							this.player.jump(Direction.DOWN);
						} else if (obj instanceof JumpLeft) {
							this.player.jump(Direction.LEFT);
						} else if (obj instanceof JumpRight) {
							this.player.jump(Direction.RIGHT);
						} else if (obj instanceof NoJump) {
							continue;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
