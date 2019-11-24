package events;
import cells.*;
import simulator.*;

/**
 * Classe d'événements permettant de déplacer un tableau de cellules  CellTab.
 * @author Groupe 65 */
public class CellsUpdateEvent extends Event {
	private CellsSimulator simulator;
	
	/**
	 * Construit un évènement d'actualisation de cellules dans un simulateur.
	 * @param simulator Le simulateur dans lequel évolue le tableau de cellules.
	 */
	public CellsUpdateEvent(CellsSimulator simulator) {
		super(simulator.getEventManager().getCurrentDate() + defaultWaitTime);
		this.simulator = simulator;
	}

	/**
	 * Execute l'événement.
	 */
	@Override
	void execute() {
		CellTab tab = simulator.getTab();
		tab.update();
		tab.draw(simulator.getParentGUI());
		simulator.addEvent(new CellsUpdateEvent(simulator));
	}
}
