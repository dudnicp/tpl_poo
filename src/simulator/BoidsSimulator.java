package simulator;


import boids.*;
import events.FlockUpdateEvent;
import gui.*;

/**
* Classe étendu de Simulator dans laquelle On simule un essaim(Flocks) de type Fish ou/et Shark.
* @author Groupe 65
*/
public class BoidsSimulator extends Simulator {
	private Flock fishes;
	private Flock sharks;
	
	/**
	 * Constructeur par défaut de la simulation avec ses Flocks.
	 * @param parentGUI Interface graphique utilisée.
	 * @param fishes Ensemble de Boid(Fish) évoluant dans la simulation.
	 * @param sharks Ensemble de Boid(Shark) évoluant dans la simulation.
	 */
	public BoidsSimulator(GUISimulator parentGUI, Flock fishes, Flock sharks) {
		super(parentGUI);
		this.fishes = fishes;
		this.sharks = sharks;
		this.fishes.reset(parentGUI);
		this.sharks.reset(parentGUI);
		this.fishes.draw(parentGUI);
		this.sharks.draw(parentGUI);
		eventManager.addEvent(new FlockUpdateEvent(this.fishes, this));
		eventManager.addEvent(new FlockUpdateEvent(this.sharks, this));
	}
	
	/**
	 * Renvoie l'ensemble de Fish(Flock) évoluant dans la simulation 
	 * @return Renvoie l'ensemble de Fish(Flock) évoluant dans la simulation.
	 */
	public Flock getFishes() {
		return fishes;
	}
	
	/**
	 * Renvoie l'ensemble de Shark(Flock) évoluant dans la simulation.
	 * @retrun Renvoie l'ensemble de Shark(Flock) évoluant dans la simulation.
	 */
	public Flock getSharks() {
		return sharks;
	}

	/**
	 * Modifie l'ensemble de Fish évoluant dans la simulation.
	 * @param fishes Ensemble(Flock) de Fish évoluant dans la simulation.
	 */
	public void setFishes(Flock fishes) {
		this.fishes = fishes;
	}
	
	/**
	 * Modifie l'ensemble de Shark évoluant dans la simulation.
	 * @param sharks Ensemble(Flock) de Shark évoluant dans la simulation.
	 */
	public void setSharks(Flock sharks) {
		this.sharks = sharks;
	}

	/**
	 * affiche et simule l'itération temporelle suivante de notre simulation
	 */
	@Override
	public void next() {
		parentGUI.reset();
		eventManager.next();
	}
	
	/**
	 * Reinitialise la simulation et la position des ensemble de Boids présents.
	 */
	@Override
	public void restart() {
		super.restart();
		fishes.reset(parentGUI);
		sharks.reset(parentGUI);
		fishes.draw(parentGUI);
		sharks.draw(parentGUI);
		eventManager.addEvent(new FlockUpdateEvent(fishes, this));
		eventManager.addEvent(new FlockUpdateEvent(sharks, this));
	}
}
