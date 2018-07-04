package fr.leroideskiwis.game;

import java.util.ArrayList;
import java.util.List;

import fr.leroideskiwis.game.events.OnGameFinished;
import fr.leroideskiwis.game.events.OnGameStarted;
import fr.leroideskiwis.game.exceptions.GameApiException;
import fr.leroideskiwis.game.others.Manager;
import fr.leroideskiwis.game.threads.GameLoop;

public class Game {
	
	private boolean running;

	private int ticks = 20;
	
	private List<EventListener> listeners = new ArrayList<>();
	
	public boolean isRunning(){
		return running;
	}

	/**
	 * Par default le jeu s'update tout les 20 ticks
	 *
	 */

	public void setTicks(int ticks){

		if(!running) this.ticks = ticks;

	}

	public void addEventListener(EventListener eventListener){
		
		listeners.add(eventListener);
		
	}
	
	public void callEvent(Event e){
		
		if(!isRunning()) return;
		
		try {
			Manager.callEvent(e, listeners);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	public void start() throws GameApiException{
		if(isRunning()) return;
				
		running = true;
		callEvent(new OnGameStarted());
		new GameLoop(this, ticks).run();

		
	}
	
	public void stop(){
		if(!isRunning()) return;
		callEvent(new OnGameFinished());
		running = false;

	}

	public int getTicks() {
		return ticks;
	}
}