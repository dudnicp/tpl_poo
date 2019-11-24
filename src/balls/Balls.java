package balls;
import java.awt.Color;
import java.awt.Point;
import gui.*;

/**
 * Classe gérant un groupe de balles se déplacant tel une seule entité. 
 * @author Groupe 65
 */
public class Balls {
	private static final int defaultSpeed = 3;
		
	private  Point[] initPos;
    private Point[] currentPos;
    private int nBalls;
    private int currentXDir;
    private int currentYDir;
    private int speed;
    
    private int initMinX;
    private int initMaxX;
    private int initMinY;
    private int initMaxY;
    private int minX;
    private int maxX;
    private int minY;
    private int maxY;

    /**
     * Constructeur de Balls
     * @param balls Tableau de Points : coordonnées initiales des balles.
     * @param speed Vitesse initiale des balles (norme).
     */
    public Balls(Point[] balls, int speed) {
    	nBalls = balls.length;
    	initPos = new Point[nBalls];
    	currentPos = new Point[nBalls];
    	for (int i = 0; i < nBalls; i++) {
    		initPos[i] = new Point(balls[i]);
    		currentPos[i] = new Point(balls[i]);
    	}
    	
    	computeInitMinMax();
    	maxX = initMaxX;
    	maxY = initMaxY;
    	minX = initMinX;
    	minY = initMinY;
    	
    	this.speed = speed;
    	
    	currentXDir = 1;
    	currentYDir = 1;
    }
    
    /**
     * Constructeur par défaut, initialise la vitesse initiale des balles à defaultSpeed ( = 3 )
     * @param balls Tableau de Points : coordonnées initiales des balles.
     */
    public Balls(Point[] balls) {
    	this(balls, defaultSpeed);
    }
    
    /**
     * Constructeur par copie, initialise les balles 
     * @param other
     */
    public Balls(Balls other) {
    	nBalls = other.nBalls;
    	initPos = new Point[nBalls];
    	currentPos = new Point[nBalls];
    	for (int i = 0; i < nBalls; i++) {
    		initPos[i] = new Point(other.initPos[i]);
    		currentPos[i] = new Point(other.currentPos[i]);
    	}
    	initMaxX = other.initMaxX;
    	initMaxY = other.initMaxY;
    	initMinX = other.initMinX;
    	initMinY = other.initMinY;
    	maxX = initMaxX;
    	maxY = initMaxY;
    	minX = initMinX;
    	minY = initMinY;
    	currentXDir = other.currentXDir;
    	currentYDir = other.currentYDir;
    	speed = other.speed;
    }

    /**
     * Initialise les maxima du groupe de balles affin de les faires rebondir sur les bords de l'écran tel une seule entité.
     */
    private void computeInitMinMax() {
    	initMinX = initPos[0].x;
    	initMaxX = initPos[0].x;
    	initMinY = initPos[0].y;
    	initMaxY = initPos[0].y;
    	
    	for (int i = 0; i < nBalls; i++) {
    		if (initPos[i].x < initMinX) {
    			initMinX = initPos[i].x;
    		}
    		if (initPos[i].x > initMaxX) {
    			initMaxX = initPos[i].x;
    		}
    		if (initPos[i].y < initMinY) {
    			initMinY = initPos[i].y;
    		}	
    		if (initPos[i].y > initMaxY) {
    			initMaxY = initPos[i].y;
    		}
    	}
    }
    
    /* Getters */
    
    /**
     * Getter du nombre de balles du groupe.
     * @return Renvoie le nombre de balles du groupe
     */
    public int getNBalls() {
    	return nBalls;
    }

    /**
     * Getter de la position actuelle de la balle numero i.
     * @param i Le numéro de la balle.
     * @return Renvoie le point (Point) de coordonées la position actuelle de la balle i.
     */
    public Point getCurrentPos(int i) {
    	return currentPos[i];
    }
    
    /**
     * Getter de la position initiale de la balle numéro i.
     * @param i Le numéro de la balle.
     * @return Renvoie n Point de coordonnées la position initiale de la balle i.
     */
    public Point getInitPos(int i) {
    	return initPos[i];
    }
    
    /**
     * Getter de l'abscisse maximale du groupe de balles.
     * @return Renvoie l'abscise maximale du groupe de balles.
     */
    public int getMaxX() {
    	return maxX;
    }

    /**
     * Getter de l'ordonnée maximale du groupe de balles.
     * @return Renvoie l'ordonnée maximale du groupe de balles.
     */
    public int getMaxY() {
    	return maxY;
    }

    /**
     * Getter de l'abscisse minimale du groupe de balles.
     * @return Renvoie l'abscise minimale du groupe de balles.
     */
    public int getMinX() {
    	return minX;
    }

    /**
     * Getter de l'ordonnée minimale du groupe de balles.
     * @return Renvoie l'ordonnée minimale du groupe de balles.
     */
    public int getMinY() {
    	return minY;
    }
    
    /**
     * Modifie la direction de déplacement des balles selon l'axe des abscisses.
     * @param currentXDir La nouvelle diréction désirée.
     */
    public void setCurrentXDir(int currentXDir) {
		this.currentXDir = currentXDir;
	}
    
    /**
     * Modifie la direction de déplacement des balles selon l'axe des ordonnées.
     * @param currentYDir La nouvelle diréction désirée.
     */
    public void setCurrentYDir(int currentYDir) {
		this.currentYDir = currentYDir;
	}
    
    /**
     * Modifie la norme de la vitesse du groupe de balles.
     * @param speed La nouvelle norme désirée.
     */
    public void setSpeed(int speed) {
		this.speed = speed;
	}
    
    /**
     * Actualise les positions des balles et les dessine dans une interface graphique.
     * @param gui L'interfacte graphique dans laquelle dessiner les balles.
     */
    public void update(GUISimulator gui) {
		move();
		handleBorders(gui);
		draw(gui);
	}
    
    /**
     * Fait rebondir les balles sur les bords d'une interface graphique.
     * @param gui L'interface dans laquelle faire rebondir les balles.
     */
    public void handleBorders(GUISimulator gui) {
    	int borderX = gui.getPanelWidth();
    	int borderY = gui.getPanelHeight();
    	if (minX < 0 || maxX > borderX) {
    		currentXDir *= -1;
		}
    	if (minY < 0 || maxY > borderY) {
			currentYDir *= -1;
		}
    }
    
    /**
     * Déplace les balles d'une distance égale à la vitesse de celles ci selon l'axe des abscisses et des ordonnées.
     */
    public void move() {
    	translate(currentXDir * speed, currentYDir * speed);
	}
    
    /**
     * Déplace les balles selon l'axe des abscisses et des ordonnées.
     * @param dx Déplacement désiré selon l'axe des abscisses.
     * @param dy Déplacement désiré selon l'axe desordonnées.
     */
    public void translate(int dx, int dy) {
    	for (Point point : currentPos) {
			point.translate(dx, dy);
		}
    	minX += dx;
    	maxX += dx;
    	minY += dy;
    	maxY+= dy;
    }
    
    /**
     * Déplace les balles jusqu,à leur position initiale définie au début de la simulation.
     */
    public void reInit() {
    	for (int i = 0; i < nBalls; i++) {
    		currentPos[i].setLocation(initPos[i]);
    	}
    	minX = initMinX;
    	minY = initMinY;
    	maxX = initMaxX;
    	maxY = initMaxY;
    	
    	currentXDir = 1;
    	currentYDir = 1;
    }
      
    /**
     * Déssine les balles dans une interface graphique.
     * @param gui L'interface graphique dans laquelle dessiner les balles.
     */
    public void draw(GUISimulator gui) {
    	for (Point point : currentPos) {
    		gui.addGraphicalElement(new Oval(point.x, point.y, Color.RED, Color.RED, 30));
    	}
    }
    
    /**
     * @return Renvoie une chaine de caractères représentant les positions actuelles des balles.
     */
    @Override
    public String toString() {
    	String retString = new String("[");
    	for (int i = 0; i < nBalls; i++) {
    		retString += currentPos[i].toString();
    		if (i != nBalls - 1) {
    			retString += ",";
    		}	
    	}
    	retString += "]";
    	return retString;
    }
}
