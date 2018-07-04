package fr.leroideskiwis.game.threads;

import fr.leroideskiwis.game.Game;
import fr.leroideskiwis.game.events.OnErrorOccured;
import fr.leroideskiwis.game.events.OnGameUpdate;

public class GameLoop implements Runnable{
	
	private Game game;
	private int delay;
	
	public GameLoop(Game g, int delay){
		
		this.game = g;

		
	}
	
	@Override
	public void run(){
		try{
		while(game.isRunning()){
			
			game.callEvent(new OnGameUpdate());
			
			
			
			Thread.sleep(1000/game.getTicks());
		}
		
		}catch(Exception e){
			game.callEvent(new OnErrorOccured(e));
		}
	}
	
}
