package boids;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;
import geometry.*;
import gui.*;
import simulator.*;
/**
 * Classe de troupeau de boids  Boid indépendants.
 * @author Groupe 65
 */
public class Flock {
    protected ArrayList<Boid> members;

    /**
     * Construit un troupeau vide de Boid.
     */
    public Flock() {
    	members = new ArrayList<Boid>();
    }

    /**
     * Getter du nombre de boids dans le troupeau.
     * @return Renvoie le nombre de boids dans le troupeau.
     */
	public ArrayList<Boid> getMembers() {
		return members;
	}

	/**
	 * Getter permettant d'acceder à un boid dans le troupeau selon son indice.
	 * @param i L'indice du boid désiré dans le troupeau.
	 * @return Renvoie le boid d'indice i dans le troupeau.
	 */
	public Boid getBoid(int i) {
		return members.get(i);
	}

	/**
	 * Modifie la vitesse maximale de tous les membres du troupeau.
	 * @param maxSpeed La nouvelle vitesse maximale désirée.
	 */
    public void setMaxSpeed(double maxSpeed) {
		for (Boid boid : members) {
			boid.setMaxSpeed(maxSpeed);
		}
	}

    /**
     * Modifie la force maximale que peut exércer une règle d'accéleration sur les membre du troupeau.
     * @param maxForce La nouvelle force maximale désirée.
     */
	public void setMaxForce(double maxForce) {
		for (Boid boid : members) {
			boid.setMaxForce(maxForce);
		}
	}

	/**
	 * Modifie la distance limite à laquelle les membres du troupeau peuvent intéragir les un avec les autres.
	 * @param visionDistance La nouvelle distance limite désirée.
	 */
	public void setVisionDistance(double visionDistance) {
		for (Boid boid : members) {
			boid.setVisionDistance(visionDistance);
		}
	}

	/**
	 * Modifie la distance à partir de laquelle les memnbres du troupeau sont considérés tomme trop proches les uns des autres.
	 * @param privateDistance La nouvelle distance limite désirée.
	 */
	public void setPrivateDistance(double privateDistance) {
		for (Boid boid : members) {
			boid.setPrivateDistance(privateDistance);
		}
	}

	/**
	 * Modifie la taille des membres du troupeau.
	 * @param size La nouvelle taille désirée.
	 */
	public void setSize(double size) {
		for (Boid boid : members) {
			boid.setSize(size);
		}
	}

	/**
	 * Modifie la couleur des membres du troupeau.
	 * @param color La nouvelle couleur désirée.
	 */
	public void setColor(Color color) {
		for (Boid boid : members) {
			boid.setColor(color);
		}
	}

	/**
	 * Ajoute un boid au troupeau.
	 * @param boid Le boid à ajouter au troupeau.
	 */
    public void addBoid(Boid boid) {
		members.add(boid);
	}


    /**
     * Réinitialise les positions et les vitesses des membres du troupeau en leur donnant des valleurs aléatoires réspéctant les contraintes impossées par l'interface graphique et la vitesse maximale.
     * @param gui L'interface graphique dans laquelle évolue le troupeau.
     */
    public void reset(GUISimulator gui) {
    	resetAllPosition(gui);
    	resetAllSpeed();
    }

    /**
     * Réinitialise les positions des membres du troupeau en leur affectant des positions aléatoires dans les limites de l'interface graphique.
     * @param gui L'interface graphqiue dans laquelle évolue le troupeau.
     */
    public void resetAllPosition(GUISimulator gui) {
        Random gen = new Random();
        for (Boid boid : members) {
			double newX = gen.nextDouble() * gui.getPanelWidth();
			double newY = gen.nextDouble() * gui.getPanelHeight();
			boid.setPosition(newX, newY);
		}
    }

    /**
     * Réinitialise les vitesses des membres du troupeau en leur affectant un vecteur vitesse de direction aléatoire et de norme entre 0 et leur vitesse maximale.
     */
    public void resetAllSpeed() {
        Random gen = new Random();
        for (Boid boid : members) {
			double newSpeedX = gen.nextDouble() * boid.getMaxSpeed();
			double newSpeedY = gen.nextDouble() * boid.getMaxSpeed();
			if (gen.nextBoolean()) {
				newSpeedX *= -1;
			}
			if (gen.nextBoolean()) {
				newSpeedY *= -1;
			}
			boid.setSpeed(newSpeedX, newSpeedY);
		}
    }

    /**
     * Accelère la vitesse des membres du troupeau en fonction des forces subies par ceux-ci, les déplace selon leur vitesse et les bords de l'interface graphique de la simulation  BoidsSimulator..
     * @param simulation Simulation dans laquelle évolue le troupeau.
     */
    public void update(BoidsSimulator simulation) {
    	accelerateAll(simulation);
    	moveAll();
    	handleBorders(simulation.getParentGUI());
    }


    /**
     * Accelère tous les membres du troupeau selon les forces subies par ceux-ci.
     * @param simulation La simulation dans laquelle évolue le troupeau.
     */
    public void accelerateAll(BoidsSimulator simulation) {
    	for (Boid boid : members) {
			boid.accelerate(simulation);
		}
    }

    /**
     * Déplace tous les membres du troupeau selon leur vitesse.
     */
    public void moveAll() {
    	for (Boid boid : members) {
    		boid.move();
    	}
    }


    /**
     * Pour chaque boid du troupeau, si celui-ci dépasse un bord de l'interface graphique, le téléporte sur le bord opposé (comme si l'interface était ronde!).
     * @param gui Interface graphique dans laquelle évolue le troupeau.
     */
    public void handleBorders(GUISimulator gui) {
    	for (Boid boid : members) {
    		Vector2d position = boid.getPosition();
    		double size = boid.getSize();
    		if (position.x < - size) {
    			position.x = gui.getPanelWidth() + size;
    		}
    		if (position.x > gui.getPanelWidth() + size) {
    			position.x = - size;
    		}
    		if (position.y < - size) {
    			position.y = gui.getPanelHeight()	+ size;
    		}
    		if (position.y > gui.getPanelHeight() + size) {
    			position.y = - size;
    		}
    	}
    }


    /**
     * Déssinne les membres du troupeau dans une interface graphique.
     * @param gui L'interface grapjique dans laquelle afficher le troupeau.
     */
    public void draw(GUISimulator gui) {
    	for (Boid boid : members) {
			boid.draw(gui);
		}
    }
}
