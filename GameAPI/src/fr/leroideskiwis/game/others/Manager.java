package fr.leroideskiwis.game.others;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

import fr.leroideskiwis.game.Event;
import fr.leroideskiwis.game.EventHandler;
import fr.leroideskiwis.game.EventListener;
import fr.leroideskiwis.game.exceptions.GameApiException;

public class Manager {

	public static void callEvent(Event e, List<EventListener> listeners) throws Exception {

		for (EventListener listener : listeners) {

			for (Method m : listener.getClass().getDeclaredMethods()) {

				if (m.isAnnotationPresent(EventHandler.class)) {

					if (m.getParameters().length == 1) {

						Parameter param = m.getParameters()[0];

						if (Event.class.isAssignableFrom(param.getType())) {

							if(param.getType().equals(e.getClass())) {
								m.invoke(listener, e);

							}

						}


					}

				}

			}

		}

	}

}
