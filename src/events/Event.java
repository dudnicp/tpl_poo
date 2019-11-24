package events;

/**
 * Classe abstraite d'évènements.
 * @author Groupe 65 
 */
public abstract class Event {
	protected static final int defaultWaitTime = 1;
	
	protected long date;
	
	/**
	 * Construit un événement à une date donnée.
	 * @param date date à laquelle executer l'évènement
	 */
	public Event(long date) {
		this.date = date;
	}
	
	/**
	 * Renvoie la date d'exécution de l'événement
	 * @return
	 */
	public long getDate() {
		return date;
	}
	
	/**
	 * Execute l'évènement.
	 */
	abstract void execute();
}
