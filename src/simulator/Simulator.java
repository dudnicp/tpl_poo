package simulator;

import events.*;
import gui.*;

/**
 * Classe mère de simulateurs définissant les comportements par défauts des simulateurs.
 * @author Groupe 65
 */
public class Simulator implements Simulable{
	protected GUISimulator parentGUI;
	protected EventManager eventManager;

	/**
	 * Construit un simulateur qui simulera les événements ayant lieu au sein de l'interface graphique.
	 * @param parentGUI Interface graphique désirée.
	 */
	public Simulator(GUISimulator parentGUI) {
		this.parentGUI = parentGUI;
		eventManager = new EventManager();
	}

	/**
	 * Getter du gestionaires d'événements de la simulation  EventManager.
	 * @return Renvoie le gestionnaire d'événements de la simulation.
	 */
	public EventManager getEventManager() {
		return eventManager;
	}

	/**
	 * Getter de l'interface graphique à simuler.
	 * @return Renvoie l'interface graphique simulée par le simulateur.
	 */
	public GUISimulator getParentGUI() {
		return parentGUI;
	}

	/**
	 * Getter de la date actuelle de la simulation.
	 * @return Renvoie la date actuelle de la simulation.
	 */
	public long getCurrentDate() {
		return eventManager.getCurrentDate();
	}

	/**
	 * Ajoute un nouvel événement à simuler dans la simulation.
	 * @param event
	 */
	public void addEvent(Event event) {
		eventManager.addEvent(event);
	}

	/**
	 * Passe à l'étape suivante de la simulation : incrémente la date actuelle et exécute les événements en attente.
	 */
	@Override
	public void next() {
		parentGUI.reset();
		eventManager.next();
	}

	/**
	 * Réinitialise la simulation : réinitialise le gestionnaires d'événements et efface les éléments graphiques de l'interface graphique
	 */
	@Override
	public void restart() {
		parentGUI.reset();
		eventManager.restart();
	}
}
