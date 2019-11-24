package geometry;
import gui.*;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 * Classe de polygones définissant les principales fonctions géométriques permettant de manipuler des polygones
 * @author Groupe 65
 */
public class Polygon implements GraphicalElement{
    private int[] xPoints;
    private int[] yPoints;
    private Vector2d center;
    private int nPoints;
    private Color color;


    /**
     * Construit un polygone à partir d'un tableau de vecteurs  Vector2d et d'une couleur.
     * @param points Tableau des coordonées des sommets du polygone.
     * @param color Couleur dans laquelle sera affiché le polygone.
     */
    public Polygon(Vector2d[] points, Color color) {
        this.color = color;
        this.nPoints = points.length;
        xPoints = new int[nPoints];
        yPoints = new int[nPoints];
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] = (int) points[i].x;
            yPoints[i] = (int) points[i].y;
        }
        this.center = new Vector2d(xPoints[0], yPoints[0]);
    }

    /**
     * Getter du tableau des abscisses des sommets du polygone.
     * @return Renvoie le tableau des abscisses des sommets du polygone.
     */
    public int[] getxPoints() {
		return xPoints;
	}

    /**
     * Getter du tableau des ordonnées des sommets du polygone.
     * @return Renvoie le tableau des ordonnées des sommets du polygon.
     */
	public int[] getyPoints() {
		return yPoints;
	}

	/**
	 * Getter du centre de rotation du polygone.
	 * @return Renvoie un vecteur de coordonées le centre de rotation du polygone.
	 */
	public Vector2d getCenter() {
		return center;
	}

	/**
	 * Getter du nombre de points du polygone.
	 * @return Renvoie le nombre de points du polygone.
	 */
	public int getnPoints() {
		return nPoints;
	}

	/**
	 * Getter de la couleur du polygone.
	 * @return Renvoie la couleur dans laquelle on affiche le polygone.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Modifie le centre de rotation du polygone.
	 * @param center Le nouveau centre de rotation du polygone.
	 */
	public void setCenter(Vector2d center) {
		this.center.reset(center);;
	}

	/**
	 * Modifie le centre de rotation du polygone.
	 * @param x L'abscisse désirée du nouveau centre de rotation.
	 * @param y L'ordonnée désirée du nouveau centre de rotation du polygone.
	 */
	public void setCenter(double x, double y) {
		this.center.reset(x, y);
	}


	/**
	 * Modifie la couleur du polygone.
	 * @param color La couleur désirée du polygone.
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Affiche le polygonne dans un contexte.
	 * @param g2d Le contexte dans lequel afficher le polygone.
	 */
	@Override
    public void paint(Graphics2D g2d) {
        Color contextColor = g2d.getColor();
        g2d.setColor(color);
        g2d.fillPolygon(xPoints, yPoints, nPoints);
        g2d.setColor(contextColor);
    }

	/**
	 * Translate les sommets du polygone ainsi que son centre de rotation.
	 * @param v Vecteur selon lequel déplacer le polygone.
	 */
    public void translate(Vector2d v) {
        center.translate(v);
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] += v.x;
            yPoints[i] += v.y;
        }
    }

    /**
     * Translate les sommets du polygonne ainsi que son centre de rotation.
     * @param x Déplacement désiré selon l'axe des abscisses.
     * @param y Déplacement désiré selon l'axe des ordonnées.
     */
    public void translate(int x, int y) {
        center.translate(x,y);
        for (int i = 0; i < nPoints; i++) {
            xPoints[i] += x;
            yPoints[i] += y;
        }
    }
    /**
     * Tourne le polygone autour de son centre de rotation.
     * @param theta L'angle de la rotation désirée.
     */
    public void rotate(double theta) {
        for (int i = 0; i < nPoints; i++) {
            Vector2d v = new Vector2d(xPoints[i], yPoints[i]);
            v.rotate(center, theta);
            xPoints[i] = (int) v.x;
            yPoints[i] = (int) v.y;
        }
    }
}
