package geometry;
import java.lang.Math;
import java.util.ArrayList;


/**
 * Classe de vecteurs implémentant les principales fonction géométriques applicables à un vecteur.
 * @author Groupe 65
 */
public class Vector2d {
    public double x;
    public double y;

    /**
     * Initialise par défaut les coordonnées d'un vecteur.
     */
    public Vector2d() {
        x = 0;
        y = 0;
    }

    /**
     * Construit un vecteur selon son abscisse et son ordonnée.
     * @param x Abscisse du veteur.
     * @param y Ordonnée du vecteur.
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Construit un vecteur selon les coordonnées d'un autre vecteur.
     * @param other L'autre vecteur.
     */
    public Vector2d(Vector2d other) {
        reset(other);
    }

    /**
     * Modifie les coordonnées du vecteur en lui passant en coordonnées les arguments de la fonction.
     * @param x Abscisse du vecteur.
     * @param y Ordonnée du vecteur.
     */
    public void reset(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Modifie les coordonnées du vecteur selon les coordonnées du vecteur passé en argument.
     * @param other Le vecteur dont les coordonnées sont reprises pour le vecteur courant.
     */
    public void reset(Vector2d other) {
        this.x = other.x;
        this.y = other.y;
    }

    /**
     * Calcule la distance entre le vecteur courant et le vecteur passé en argument.
     * @param other Le vecteur avec lequel on calcule la distance.
     */
    public double distanceTo(Vector2d other) {
        return Math.sqrt((other.x - this.x)*(other.x - this.x) + (other.y - this.y)*(other.y - this.y));
    }

    /**
     * Translate le vecteur courant selon un coefficient.
     * @param alpha Le coefficient de translation.
     */
    public void translate(double alpha) {
        this.x *= alpha;
        this.y *= alpha;
    }

    /**
     * Translate le vecteur courant selon un autre vecteur.
     * @param other Le vecteur selon lequel on déplace le vecteur courant.
     */
    public void translate(Vector2d other) {
        this.x += other.x;
        this.y += other.y;
    }

    /**
     * Translate le vecteur courant selon chaque coordonnée.
     * @param x Coefficient de translation en abscisse.
     * @param y Coefficent de translation en ordonnée.
     */
    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Calcule la norme du vecteur courant.
     */
    public double norm() {
        return Math.sqrt(x*x + y*y);
    }

    /** 
     * Pivote le vecteur courant.
     * @param center Le centre de rotation.
     * @param theta L'angle en radian de rotation.
     */
    public void rotate(Vector2d center, double theta) {
      double auxX = x - center.x;
      double auxY = y - center.y;

      double tempX = auxX;
      double tempY = auxY;

      auxX = (int) (Math.cos(theta) * tempX + Math.sin(theta) * tempY);
      auxY = (int) (- Math.sin(theta) * tempX + Math.cos(theta) * tempY);

      x = auxX + center.x;
      y = auxY + center.y;
    }
    
    /**
     * Normalise le vecteur courant.
     */
    public void normalise() {
    	double norm = this.norm();
    	x /= norm;
    	y /= norm;
    }
    
    /**
     * Soustrait au vecteur courant le vecteur passé en argument.
     * @param other Le vecteur auquel on soustrait le vecteur courant.
     */
    public void sub(Vector2d other ) {
    	x -= other.x;
    	y -= other.y;
    }
    
    /**
     * Limite la norme du vecteur courant.
     * @param limit La liste définie comme norme maximale du vecteur.
     */
    public void restrict(double limit) {
    	if (norm() > limit) {
			normalise();
			translate(limit);
		}
    }

    /**
     * Calcule l'angle entre deux vecteurs (en valeur absolue).
     * @param a Le premier vecteur.
     * @param b Le second vecteur.
     */
    public static double angle(Vector2d a, Vector2d b) {
      if((a.x == 0 && a.y == 0) || (b.x == 0 && b.y ==0)) {
          return 0;
      }
      return Math.acos(scalarProduct(a, b)/(a.norm()*b.norm()));
    }

    /**
     * Calcule le produit scalaire de deux vecteurs.
     * @param a Le premier vecteur.
     * @param b Le second vecteur.
     */
    public static double scalarProduct(Vector2d a, Vector2d b) {
        return a.x * b.x + a.y * b.y;
    }

    /**
     * Calcule le barycentre d'un ensemble de vecteurs. 
     * @param list La liste de vecteurs considérés.
     */
    public static Vector2d barycenter(ArrayList<Vector2d> list) {
        Vector2d retVector = new Vector2d();
        for(Vector2d v : list) {
            retVector.translate(v);
        }
        retVector.translate(1.0/list.size());
        return retVector;
    }
    
    /**
     * Somme deux vecteurs, créant ainsi un nouveau vecteur somme.
     * @param a Premier vecteur considéré.
     * @param b Second vecteur considéré.
     */
    public static Vector2d sum(Vector2d a, Vector2d b) {
    	return new Vector2d(a.x + b.x, a.y + b.y);
    }
    
    /**
     * Soustrait un vecteur à un autre, créant ainsi un nouveau vecteur différence.
     * @param a Vecteur premier terme de la différence.
     * @param b Vecteur second terme de la différence.
     */
    public static Vector2d sub(Vector2d a, Vector2d b) {
    	return new Vector2d(a.x - b.x, a.y - b.y);
    }
}
