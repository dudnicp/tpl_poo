package events;

import java.util.Comparator;

/**
 * Classe définissant un comparateur pour comparer des événements.
 * @author Groupe 65 
 */
public class EventComparator implements Comparator<Event> {

	/**
	 * Définit une comparaison entre événements : on les compare selon leur date d'exécution..
	 * @param event1 Le premier événement à comaprer.
	 * @param event2 Le deuxième événement à comparer.
	 * @return Renvoie -1 si la date d'exécution de even1 est inférieure à celle de event2, 1 si elle est supérieure, 0 si elles sont égales.
	 */
	@Override
	public int compare(Event event1, Event event2) {
		long date1 = event1.getDate();
		long date2 = event2.getDate();
		if (date1 < date2) {
			return -1;
		}
		else if (date1 == date2) {
			return 0;
		}
		else {
			return 1;
		}
	}
}
