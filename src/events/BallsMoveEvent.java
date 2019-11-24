package events;
import simulator.*;
/**
 * Classe d'événements permettant de déplacer un groupe de balles  Balls.
 * @author Groupe 65
 */
public class BallsMoveEvent extends Event {
	private BallsSimulator simulator;

	/**
	 * Constructeur d'un événement de déplacement d'un groupe de balles dans un simulateur.
	 * @param simulator Le simulateur  dans lequel évoluent les balles à déplacer.
	 */
	public BallsMoveEvent(BallsSimulator simulator) {
		super(simulator.getEventManager().getCurrentDate() + defaultWaitTime);
		this.simulator = simulator;
	}

	/**
	 * Execute l'événement.
	 */
	@Override
	void execute() {
		simulator.getBalls().update(simulator.getParentGUI());
		simulator.addEvent(new BallsMoveEvent(simulator));
	}

}
