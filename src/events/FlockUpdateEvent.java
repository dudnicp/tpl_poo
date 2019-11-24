package events;
import boids.*;
import gui.GUISimulator;
import simulator.*;
/**
 * Classe d'événements permettant de déplacer un groupe de boids
 * @author Groupe 65
 */
public class FlockUpdateEvent extends Event {
	private Flock flock;
	private BoidsSimulator simulator;

	public FlockUpdateEvent(Flock flock, BoidsSimulator simulator) {
		super(simulator.getCurrentDate() + defaultWaitTime);
		this.simulator = simulator;
		this.flock = flock;
	}

	/**
	 * Execute l'événement.
	 */
	@Override
	void execute() {
		GUISimulator gui = simulator.getParentGUI();
		flock.update(simulator);
		flock.draw(gui);
		simulator.addEvent(new FlockUpdateEvent(flock, simulator));
	}

}
