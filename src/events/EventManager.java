package events;

import java.util.PriorityQueue;

/**
 * Classe définissant un géstionaire d'événements.
 * @author Groupe 65
 */
public class EventManager {
	private static final int defaultInitialCapacity = 10;
	private static final EventComparator defaultEventComparator = new EventComparator();
	private long currentDate;
	private PriorityQueue<Event> events;
	
	/**
	 * Constructeur par défaut du géstionnaire.
	 */
	public EventManager() {
		currentDate = 0;
		events = new PriorityQueue<Event>(defaultInitialCapacity, defaultEventComparator);
	}
	
	/**
	 * Getter de la date actuelle du géstionnaire.
	 * @return Renvoie la date actuelle du géstionnaire.
	 */
	public long getCurrentDate() {
		return currentDate;
	}
	
	/**
	 * Ajoute un événement dans le géstionnaire.
	 * @param event événement à ajouter.
	 */
	public void addEvent(Event event) {
		events.offer(event);
	}
	
	/**
	 * Permet de savoir si tous les événements du géstionnaire on étés traités.
	 * @return Renvoie 1 si il reste des événements à exécuter, 0 sinon.
	 */
	public boolean isFinished() {
		return (events.size() == 0);
	}
	
	/**
	 * Incrémente la date du géstionnaire et exécute tous les événements dont la date d'éxécution est inférieure ou égale à la date actuelle.
	 */
	public void next() {
		currentDate ++;
		Event event = events.peek();
		while (event != null && event.getDate() <= currentDate) {
			event = events.poll();
			event.execute();
			event = events.peek();
		}
	}
	
	/**
	 * Réinitialise le géstionnaire : remet la date à 0 et vide la liste des événements à exécuter.
	 */
	public void restart() {
		currentDate = 0;
		events.clear();
	}
	
}
