package fr.leroideskiwis.game.events;

import fr.leroideskiwis.game.Event;

public class OnErrorOccured implements Event{
	
	private Exception e;
	
	public OnErrorOccured(Exception e){
		
		this.e = e;
		
	}
	
	public Exception getException(){
		return e;
	}
	
}
