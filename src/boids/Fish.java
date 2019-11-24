package boids;

import java.awt.Color;
import java.util.ArrayList;

import geometry.Polygon;
import geometry.Vector2d;
import gui.GUISimulator;
import simulator.BoidsSimulator;

/**
 * Classe de poissons définissant leur comportement et les forces qu'ils subissent
 * @author Groupe 65
 */
public class Fish extends Boid {
    public static final double defaultCohesionFactor = 1.0;
    public static final double defaultSeparationFactor = 1.5;
    public static final double defaultAlignmentFactor = 1.0;
    public static final double defaultFearFactor = 4.0;

    public static final double defaultMaxSpeed = 2;
    public static final double defaultMaxForce = 0.03;
    public static final double defaultVisionDistance = 100;
    public static final double defaultPrivateDistance = 25;
    public static final double defaultSize = 16;
    public static final Color defaultColor = Color.YELLOW;

    private double cohesionFactor;
    private double separationFactor;
    private double alignmentFactor;
    private double fearFactor;

    /**
     * Construit un poisson à la position (0,0) de vitesse nulle, avec les valeurs par défauts de ses attributs.
     */
	public Fish() {
		super(defaultMaxSpeed, defaultMaxForce, defaultVisionDistance, defaultPrivateDistance, defaultSize, defaultColor);
		setCohesionFactor(defaultCohesionFactor);
		setSeparationFactor(defaultSeparationFactor);
		setAlignmentFactor(defaultAlignmentFactor);
		setFearFactor(defaultFearFactor);
	}


	/**
	 * Construit un poisson avec une position et une vitesse initiale désirée, les autres attributs étant initialisés à leurs valeurs par défaut.
	 * @param position La position initialse désirée.
	 * @param speed La vitesse initiale désirée.
	 */
	public Fish(Vector2d position, Vector2d speed) {
		super(position, speed, defaultMaxSpeed, defaultMaxForce, defaultVisionDistance, defaultPrivateDistance, defaultSize, defaultColor);
		setCohesionFactor(defaultCohesionFactor);
		setSeparationFactor(defaultSeparationFactor);
		setAlignmentFactor(defaultAlignmentFactor);
		setFearFactor(defaultFearFactor);
	}


	/* Getters */

	/**
	 * Getter du coefficient de la force de cohésion agissant sur le poisson.
	 * @return Renvoie le coefficient de la force de cohésion agissant sur le poisson.
	 */
	public double getCohesionFactor() {
		return cohesionFactor;
	}

	/**
	 * Getter du coefficient de la force de cohésion agissant sur le poisson.
	 * @return Renvoie le coefficient de la force de cohésion agissant sur le poisson.
	 */
	public double getSeparationFactor() {
		return separationFactor;
	}

	/**
	 * Getter du coefficient de la force d'aligment agissant sur le poisson.
	 * @return Renvoie le coefficient de la force d'aligment agissant sur le poisson.
	 */
	public double getAlignmentFactor() {
		return alignmentFactor;
	}

	/**
	 * Getter de la force qu'exércent les requins prédateurs  Shark sur le poisson.
	 * @return Renvie la force qu'exércent les requins prédateurs sur le poisson.
	 */
	public double getFearFactor() {
		return fearFactor;
	}


	/* Setters */

	/**
	 * Modifie le coefficient de la force de cohésion.
	 * @param cohesionFactor Le nouveau coefficient désiré.
	 */
	public void setCohesionFactor(double cohesionFactor) {
		this.cohesionFactor = cohesionFactor;
	}

	/**
	 * Modifie le coefficient de la force de répulsion.
	 * @param separationFactor Le nouveau coefficient désiré.
	 */
	public void setSeparationFactor(double separationFactor) {
		this.separationFactor = separationFactor;
	}

	/**
	 * Modifie le coefficient de la force d'aligmenent.
	 * @param alignmentFactor Le nouveau coefficient désiré.
	 */
	public void setAlignmentFactor(double alignmentFactor) {
		this.alignmentFactor = alignmentFactor;
	}

	/**
	 * Modifie le coefficient de a force de peur.
	 * @param fearFactor Le nouveau coefficient désiré.
	 */
	public void setFearFactor(double fearFactor) {
		this.fearFactor = fearFactor;
	}


	/* Fonctions uties */


    /**
     * Renvoie le vecteur de la force de cohésion qu'exérce un groupe de boid sur le poisson.
     * @param neighbours Le groupe de boid qui exérce la force.
     * @return Renvoie le vecteur de la force de cohésion qu'exérce un groupe de boid sur le poisson.
     */
    private Vector2d cohesionRule(ArrayList<Boid> neighbours) {
    	if (neighbours.size() != 0) {
    		Vector2d barycenter = Vector2d.barycenter(Boid.getPositions(neighbours));
    		Vector2d desiredDirection = Vector2d.sub(barycenter, position);
    		desiredDirection.normalise();
    		desiredDirection.translate(maxSpeed);
    		Vector2d cohesionForce = Vector2d.sub(desiredDirection, speed);
    		cohesionForce.restrict(maxForce);
    		cohesionForce.translate(cohesionFactor);
    		return cohesionForce;
		}
    	else {
			return new Vector2d();
		}
    }


    /**
     * Renvoie le vecteur de la force de répulsion qu'exérce un groupe de boid sur le poisson.
     * @param closeNeighbours Le groupe de boid qui exérce la force.
     * @return Renvoie le vecteur de la force de répulsion qu'exérce un groupe de boid sur le poisson.
     */
    private Vector2d separationRule(ArrayList<Boid> closeNeighbours) {
		Vector2d separetionForce = new Vector2d();
		for (Boid other : closeNeighbours) {
			double d = distanceTo(other);
			Vector2d deltaPos = Vector2d.sub(position, other.getPosition());
			deltaPos.normalise();
			deltaPos.translate(1.0/d);
			separetionForce.translate(deltaPos);
		}
		if (separetionForce.x != 0 || separetionForce.y != 0) {
			separetionForce.normalise();
			separetionForce.translate(maxSpeed);
			separetionForce.sub(speed);
			separetionForce.restrict(maxForce);
			separetionForce.translate(separationFactor);
		}
		return separetionForce;
    }


/**
 * Renvoie le vecteur de la force de répulsion qu'exérce un groupe de boid sur le poisson.
 * @param neighbours Le groupe de boid qui exérce la force.
 * @return Renvoie le vecteur de la force de répulsion qu'exérce un groupe de boid sur le poisson.
 */
    Vector2d alignmentRule(ArrayList<Boid> neighbours) {
    	if (neighbours.size() != 0) {
        	Vector2d averageSpeed = Vector2d.barycenter(Boid.getSpeeds(neighbours));
        	averageSpeed.normalise();
        	averageSpeed.translate(maxSpeed);
        	Vector2d alignmentForce = Vector2d.sub(averageSpeed, speed);
        	alignmentForce.restrict(maxForce);
        	alignmentForce.translate(alignmentFactor);
        	return alignmentForce;
		}
    	else {
    		return new Vector2d();
		}
    }

    /**
     * Renvoie le vecteur de la force de peur qu'exérce un groupe de boid sur le poisson.
     * @param sharks Le groupe de boid qui exérce la force.
     * @return Renvoie le vecteur de la force de peur qu'exérce un groupe de boid sur le poisson
     */
    private Vector2d fearRule(Flock sharks) {
		Vector2d fearForce = new Vector2d();
		for (Boid shark : sharks.getMembers()) {
			double d = distanceTo(shark);
			if (d < visionDistance) {
				Vector2d deltaPos = Vector2d.sub(position, shark.getPosition());
				deltaPos.normalise();
				deltaPos.translate(1.0/d);
				fearForce.translate(deltaPos);
			}
		}
		if (fearForce.x != 0 || fearForce.y != 0) {
			fearForce.normalise();
			fearForce.translate(maxSpeed);
			fearForce.sub(speed);
			fearForce.restrict(maxForce);
			fearForce.translate(fearFactor);
		}
		return fearForce;
    }

    /**
     * Modifie la vitesse du poisson en lui appliquant les forces exercées par les agents de la simulation.
     * @param simulation La simulation dans laquelle évolue le poisson
     */
    @Override
    public void accelerate(BoidsSimulator simulation) {
		ArrayList<Boid> neighbours = getNeighbours(simulation.getFishes());
		ArrayList<Boid> closeNeighbours = getCloseNeighbours(neighbours);
		Vector2d acceleration = new Vector2d();
		acceleration.translate(cohesionRule(neighbours));
		acceleration.translate(separationRule(closeNeighbours));
		acceleration.translate(alignmentRule(neighbours));
		acceleration.translate(fearRule(simulation.getSharks()));
		Vector2d newSpeed = Vector2d.sum(speed, acceleration);
		setSpeed(newSpeed);
    }

    /**
     * Dessine le poisson dans l'interface graphique.
     * @param gui l'interface graphique dans laquelle afficher le poisson.
     */
    @Override
    public void draw(GUISimulator gui) {
        Vector2d baseVect = new Vector2d(0,1);
        Vector2d[] triangle = new Vector2d[3];
        triangle[0] = new Vector2d(0, size/2);
        triangle[1] = new Vector2d(-size/4, -size/2);
        triangle[2] = new Vector2d(size/4, -size/2);

        Polygon shape = new Polygon(triangle, color);
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
