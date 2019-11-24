package boids;
import java.awt.Color;
import java.util.ArrayList;

import geometry.*;
import gui.*;
import simulator.BoidsSimulator;

/**
* Classe d'un Boid, agent d'une simulation multi-agent.
* @author Groupe 65
*/
public abstract class Boid {

	/* Static Methods */
	/**
	 * extrait l'ensemble des positions(ArrayList<Boid>) d'un ensemble de Boid.
	 * @param boids Liste de Boid
	 * @return Renvoie la liste des Position(ArrayList<Boid>) de l'ensemble des boids
	 */
    public static ArrayList<Vector2d> getPositions(ArrayList<Boid> boids) {
    	ArrayList<Vector2d> retList = new ArrayList<Vector2d>();
    	for (Boid boid : boids) {
    		retList.add(boid.getPosition());
		}
    	return retList;
    }
    /**
	 * extrait l'ensemble des vitesses(ArrayList<Boid>) d'un ensemble de Boid.
	 * @param boids Liste de Boid
	 * @return Renvoie la liste des Vitesses(ArrayList<Boid>) de l'ensemble des boids
	 */
    public static ArrayList<Vector2d> getSpeeds(ArrayList<Boid> boids) {
       	ArrayList<Vector2d> retList = new ArrayList<Vector2d>();
    	for (Boid boid : boids) {
    		retList.add(boid.getSpeed());
		}
    	return retList;
    }


	protected Vector2d position;
	protected Vector2d speed;
	protected double maxSpeed;
    protected double maxForce;
    protected double visionDistance;
    protected double privateDistance;
    protected double size;
    protected Color color;

    /**
     * Constructeur d'un Boid de vitesse nulle, à la position(0,0).
     */
	public Boid() {
		position = new Vector2d();
		speed = new Vector2d();
	}

	/**
	 * Constructeur d'un Boid à vitesse nulle en (0,0) avec ses attributs.
	 * @param maxSpeed Vitesse maximale(en norme) d'un Boid
	 * @param maxForce Force maximale subit par un Boid.
	 * @param visionDistance distance à à partir de laquelle le Boid subira les forces de ses congénères.
	 * @param privateDistance distance à partir de laquelle le Boid va se repousser des autres.
	 * @param size Taille du Boid.
	 * @param color Couleur du Boid
	 */
	public Boid(double maxSpeed, double maxForce, double visionDistance, double privateDistance, double size, Color color) {
		this();
		setMaxSpeed(maxSpeed);
		setMaxForce(maxForce);
		setVisionDistance(visionDistance);
		setPrivateDistance(privateDistance);
		setSize(size);
		setColor(color);
	}

	/**
	 * Constructeur d'un Boid avec sa vitesse, sa position et ses attributs.
	 * @param position Position du Boid.
	 * @param speed Vitesse du Boid
	 * @param maxSpeed Vitesse maximale(en norme) d'un Boid
	 * @param maxForce Force maximale subit par un Boid.
	 * @param visionDistance distance à à partir de laquelle le Boid subira les forces de ses congénères.
	 * @param privateDistance distance à partir de laquelle le Boid va se repousser des autres.
	 * @param size Taille du Boid.
	 * @param color Couleur du Boid
	 */
	public Boid(Vector2d position, Vector2d speed, double maxSpeed, double maxForce, double visionDistance, double privateDistance, double size, Color color) {
		this(maxSpeed, maxForce, visionDistance, privateDistance, size, color);
		setPosition(position);
		setSpeed(speed);
	}


	/* Getters */
	/**
	 * Renvoie la Position du Boid(Vector2d)
	 * @return renvoie la Position(Vector2d) du Boid
	 */
	public Vector2d getPosition() {
		return position;
	}

	/**
	 * Renvoie le vecteur vitesse du Boid (vector2d).
	 * @return Renvoie le vecteur vitesse du Boid (vector2d).
	 */
	public Vector2d getSpeed() {
		return speed;
	}

	/**
	 * Renvoie la vitesse Maximale que peut avoir le Boid
	 * @return Renvoie la vitesse Maximale que peut avoir le Boid.
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/** Renvoie la force maximale que peut subir le Boid.
	 * @return Renvoie la force maximale que peut subir le Boid.
	 */
	public double getMaxForce() {
		return maxForce;
	}

	/**
	 * Renvoie la distance à partir de laquelle le Boid subit les force de ses congénères.
	 * @return Renvoie la distance(int) à partir de laquelle le Boid subit les forces des autres Boid présent.
	 */
	public double getVisionDistance() {
		return visionDistance;
	}

	/**
	 * Renvoie la distance à partir de laquelle le Boid repousse les autres.
	 * @return Renvoie la distance à partir de laquelle le Boid repousse les autres.
	 */
	public double getPrivateDistance() {
		return privateDistance;
	}

	/**
	 * Renvoie la taille du Boid
	 * @return Renvoie la taille du Boid.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Renvoie la couleur du Boid.
	 * @return Renvoie la couleur(Color) du Boid.
	 */
	public Color getColor() {
		return color;
	}


	/* Setters */
	/**
	 * Modifie la Position du Boid en fonction de coordonnées.
	 * @param x Abscisse du Boid.
	 * @param y Ordonée du Boid.
	 */
	public void setPosition(double x, double y) {
		position.reset(x, y);
	}

	/**
	 * Modifie la Position du Boid en fonction d'un vecteur position(Vector2d).
	 * @param position Vecteur(Vector2d) position du Boid.
	 */
	public void setPosition(Vector2d position) {
		this.position.reset(position);;
	}

	/**
	 * Modifie le vecteur vitesse du Boid avec un vecteur(vecto2d)
	 * @param speed Vitesse (vect2d) du Boid.
	 */
	public void setSpeed(Vector2d speed) {
		this.speed.reset(speed);;
		this.speed.restrict(maxSpeed);
	}

	/**
	 * Modifie les composantes de la vitesse du vecteur(vector2d).
	 * @param x Composante horizontale de la vitesse.
	 * @param y Composante verticale de la vitesse.
	 */
	public void setSpeed(double x, double y) {
		speed.reset(x,y);
		speed.restrict(maxSpeed);
	}

	/**
	 * Modifie la vitesse maximale accessible par le Boid.
	 * @param maxSpeed Valeur maximale de la norme du vecteur vitesse du boid.
	 */
    public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

    /**
     * Modifie la force maximale subie par le Boid.
     * @param maxForce Valeur de la force maximale subie par le Boid.
     */
	public void setMaxForce(double maxForce) {
		this.maxForce = maxForce;
	}

	/**
	 * Modifie la distance à partir de laquelle le Boid subit les force des autres.
	 * @param visionDistance Distance à partir de laquelle le Boid subit la force d'autre Boid.
	 */
	public void setVisionDistance(double visionDistance) {
		this.visionDistance = visionDistance;
	}

	/**
	 * Modifie la distance à partir de laquelle le Boid repousse les autres.
	 * @param privateDistance Distance à partir de laquelle le Boid repousse les autres.
	 */
	public void setPrivateDistance(double privateDistance) {
		this.privateDistance = privateDistance;
	}

	/**
	 * Modifie la taille du Boid.
	 * @param size.
	 */
	public void setSize(double size) {
		this.size = size;
	}

	/**
	 * Modifie la couleur du Boid.
	 * @param color Couleur(Color) du Boid.
	 */
	public void setColor(Color color) {
		this.color = color;
	}


	/* Fonctions Utiles */
	/**
	 * Renvoie l'ensemble (ArryList<Boid>) des Boids dans le voisinnage du Boid considéré.
	 * @param flock Ensemble(Flock) de Boid.
	 * @return Renvoie l'ensemble(ArrayList<Boid>) des Boids dans le voisinnage du Boid considéré
	 */
	public ArrayList<Boid> getNeighbours(Flock flock) {
    	ArrayList<Boid> neighbours = new ArrayList<Boid>();
    	for (Boid other : flock.getMembers()) {
			if (distanceTo(other) < visionDistance) {
				if (other != this) {
					neighbours.add(other);
				}
			}
		}
    	return neighbours;
    }

	/**
	 * Renvoie l'ensemble(ArrayList<Boid>) des Boids qui seront repoussés par le Boid.
	 * @param flock Ensemble(Flock) des Boids considérés.
	 * @return Ensemble(ArrayList<Boid>) des Boids qui seront repoussés par le Boid.
	 */
    public ArrayList<Boid> getCloseNeighbours(Flock flock) {
    	ArrayList<Boid> closeNeighbours = new ArrayList<Boid>();
    	for (Boid other : flock.getMembers()) {
			if (distanceTo(other) < privateDistance) {
				if (other != this) {
					closeNeighbours.add(other);
				}
			}
		}
    	return closeNeighbours;
    }

    /**
	 * Renvoie l'ensemble(ArrayList<Boid>) des Boids qui seront repoussés par le Boid.
	 * @param neighbours Ensemble(ArrayList<Boid>) des Boids considérés.
	 * @return Ensemble(ArrayList<Boid>) des Boids qui seront repoussés par le Boid.
	 */
    public ArrayList<Boid> getCloseNeighbours(ArrayList<Boid> neighbours) {
    	ArrayList<Boid> closeNeighbours = new ArrayList<Boid>();
    	for (Boid other : neighbours) {
			if (distanceTo(other) < privateDistance) {
				if (other != this) {
					closeNeighbours.add(other);
				}
				closeNeighbours.add(other);
			}
		}
    	return closeNeighbours;
    }

    /**
     * Déplace le Boid après une itération de temps.
     */
	public void move() {
		position.translate(speed);
	}

	/**
	 * Renvoie la distance entre le Boid considéré et un autre Boid.
	 * @param other Boid auquel on calcule la distance par rapport au Boid considéré.
	 * @return Renvoie la distance entre le Boid considéré et un autre Boid.
	 */
	public double distanceTo(Boid other) {
		return position.distanceTo(other.position);
	}

	/**
	 * Modifie la vitesse du Boid selon les forces subies.
	 * @param simulation Simulation( BoidsSimulator) dans laquel évolue le Boid.
	 */
	public abstract void accelerate(BoidsSimulator simulation);

	/**
	 * Représente Graphiquement le Boid sur l'interface graphique utilisée.
	 * @param gui Interface graphique utilisée.
	 */
	abstract public void draw(GUISimulator gui);
}
