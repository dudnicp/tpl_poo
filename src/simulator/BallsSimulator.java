package simulator;
import balls.*;
import events.*;
import gui.*;

/**
* Classe de Simulation d'un Balls.
* @author Groupe 65
*/
public class BallsSimulator extends Simulator {
	private Balls balls;
	
	/**
	 * Constructeur par défaut de BallsSimulator sur son interface graphique.
	 * @param balls Ensemble(Balls) de Point se déplacant dans l'espace.
	 * @parentGUI Interface graphique utilisée.
	 */
	public BallsSimulator(Balls balls, GUISimulator parentGUI) {
		super(parentGUI);
		this.balls = balls;
		addEvent(new BallsMoveEvent(this));
		balls.draw(parentGUI);
	}
	
	/**
	 * Renvoie L'ensemble(Balls) des Points de la simulation.
	 * @return Renvoie l'ensemble(Balls) des Points de la simulation.
	 */
	public Balls getBalls() {
		return balls;
	}
	
	/**
	 * Reinitialise la simulation ainsi que la position de l'ensemble des Points (Balls).
	 */
	@Override
	public void restart() {
		super.restart();
		addEvent(new BallsMoveEvent(this));
		balls.draw(parentGUI);
	}
}
