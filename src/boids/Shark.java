package boids;

import java.awt.Color;
import java.util.ArrayList;

import geometry.*;
import gui.*;
import simulator.BoidsSimulator;
/**
* Classe de requins étendu de Boid dans laquelle on défini ses règles de déplacement et son comportement.
* @author Groupe 65
*/
public class Shark extends Boid {
	
    public static final double defaultMaxSpeed = 1;
    public static final double defaultMaxForce = 0.03;
    public static final double defaultVisionDistance = 200;
    public static final double defaultPrivateDistance = 100;
    public static final double defaultSize = 100;
    public static final Color defaultColor = Color.BLUE;
    
    public static final double defaultSeparationFactor = 2.0;
    public static final double defaultHungerFactor = 1.5;
    
    private double separationFactor;
    private double hungerFactor;
    
    /**
     * Constructeur par défaut d'un requin avec les valeurs par défaut de ses attributs.
     */
    public Shark() {
		super(defaultMaxSpeed, defaultMaxForce, defaultVisionDistance, defaultPrivateDistance, defaultSize, defaultColor);
		setSeparationFactor(defaultSeparationFactor);
		setHungerFactor(defaultHungerFactor);
	}

    /**
     * Construit un requin avec sa position et son vecteur vitesse
     * @param position Position initiale du requin.
     * @param speed vitesse initiale du requin.
     */
	public Shark(Vector2d position, Vector2d speed) {
		super(position, speed, defaultMaxSpeed, defaultMaxForce, defaultVisionDistance, defaultPrivateDistance, defaultSize, defaultColor);
		setSeparationFactor(defaultSeparationFactor);
		setHungerFactor(defaultHungerFactor);
	}

	/**
	 * Renvoie le coefficient de repulsion du requin.
	 * @return Renvoie le coefficient de repulsion du requin.
	 */
	public double getSeparationFactor() {
		return separationFactor;
	}

	/**
	 * Renvoie le coefficient d'attraction vers les proies de requin.
	 * @return Renvoie le coefficient d'attraction vers les proies du requin.
	 */
	public double getHungerFactor() {
		return hungerFactor;
	}
	
	/**
	 * Modifie le coefficient de repulsion du requin.
	 * @param separationFactor Valeur du coefficient de repulsion du requin.
	 */
	public void setSeparationFactor(double separationFactor) {
		this.separationFactor = separationFactor;
	}

	/**
	 * Modifie le coefficient d'attraction du requin vers ses proies.
	 * @param hungerFactor Coefficient d'attraction du requin vers ses proies.
	 */
	public void setHungerFactor(double hungerFactor) {
		this.hungerFactor = hungerFactor;
	}
	
	/**
	 * Renvoie le vecteur force de répulsion subie par le requin.
	 * @param flock Le groupe de boid qui exérce la force.
	 * @return Renvoie le vecteur force de Repulsion subie par le requin. 
	 */
    private Vector2d separationRule(Flock flock) {
    	ArrayList<Boid> closNeighbours = getCloseNeighbours(flock);
		Vector2d separetionForce = new Vector2d();
		for (Boid other : closNeighbours) {
			double d = distanceTo(other);
			Vector2d deltaPos = Vector2d.sub(position, other.getPosition());
			deltaPos.normalise();
			deltaPos.translate(1.0/d);
			separetionForce.translate(deltaPos);
		}
		if (separetionForce.x != 0 || separetionForce.y != 0) {
			separetionForce.normalise();
			separetionForce.translate(maxSpeed);
			separetionForce.sub(getSpeed());
			separetionForce.restrict(maxForce);
			separetionForce.translate(separationFactor);
		}
		return separetionForce;
    }
    
    /**
     * Renvoie le vecteur force d'attraction subie par le requin.
     * @param fishes Groupe de boids pouvant exércer une force au requin.
     * @return Renvoie le vecteur(Vector2d) force d'attraction subie par le requin.
     */
    private Vector2d hungerRule(Flock fishes) { 
    	ArrayList<Boid> closeFishes = getNeighbours(fishes);
    	if (closeFishes.size() != 0) {
    		Vector2d barycenter = Vector2d.barycenter(Boid.getPositions(closeFishes));
    		Vector2d desiredDirection = Vector2d.sub(barycenter, position);
    		desiredDirection.normalise();
    		desiredDirection.translate(maxSpeed);
    		Vector2d cohesionForce = Vector2d.sub(desiredDirection, speed);
    		cohesionForce.restrict(maxForce);
    		cohesionForce.translate(hungerFactor);
    		return cohesionForce;
		}
    	else {
			return new Vector2d();
		}
    }

    /**
     * Modifie la Vitesse du requin en lui appliquant les forces exercées par les agents de la simulation.
     * @param simulation Simulation dans laquelle évolue le requin.
     */
	@Override
	public void accelerate(BoidsSimulator simulation) {
		Vector2d acceleration = new Vector2d();
		acceleration.translate(separationRule(simulation.getSharks()));
		acceleration.translate(hungerRule(simulation.getFishes()));
		Vector2d newSpeed = Vector2d.sum(speed, acceleration);
		setSpeed(newSpeed);
	}
	
	/**
	 * Affiche le requin sur l'interface graphique utilisée.
	 * @param gui Interface graphique utilisée.
	 */
	@Override
	public void draw(GUISimulator gui) {
        Vector2d baseVect = new Vector2d(0,1);
        Vector2d[] points = new Vector2d[5];
        points[0] = new Vector2d(0, -size/2);
        points[1] = new Vector2d(-size/3, 0);
        points[2] = new Vector2d(-size/6, size/2);
        points[3] = new Vector2d(size/6, size/2);
        points[4] = new Vector2d(size/3, 0);

        Polygon shape = new Polygon(points, color);
        shape.setCenter(0,0);
        double theta = Vector2d.angle(baseVect, speed);
        if (speed.x < 0) {
        	theta *= -1;
        }
        shape.rotate(theta);
        shape.translate(position);
        gui.addGraphicalElement(shape);
	}
}
