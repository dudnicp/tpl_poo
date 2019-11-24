package cells;

import java.awt.Color;
import java.util.Random;
import gui.*;

/**
* Classe de modélisation d'un automate Cellulaire comprenant ses propres règles.
* @author Groupe 65
*/
public abstract class CellTab {
	public static final int defaultStateNumber = 2;
	
	protected Color[] colors;
	protected int height;
	protected int width;
	protected int[][] cells;
	protected int nStates;
	protected int cellSize;
	
	
	/**
	 * Constructeur d'un tableau de Cellule
	 * @param height La hauteur en nombre de cellule.
	 * @param width La largeur en nombre de cellule.
	 * @param nStates Le nombre d'état possible d'une cellule.
	 * @param cellSize Dimension d'une cellule.
	 */
	public CellTab(int height, int width, int nStates, int cellSize) {
		this.nStates = nStates;
		this.height = height;
		this.width = width;
		this.cellSize = cellSize;
		
        colors = new Color[nStates];
        for (int i = 0; i < nStates; i++) {
			colors[i] = new Color(i * 255/(nStates - 1), i * 255/(nStates - 1), i * 255/(nStates - 1));
		}
		
		cells = new int[height][width];
		reInit();
	}
		
	/**
	 * Getter de la liste des couleurs selon chaque état possible d'une cellule.
	 * @return Tableau de couleurs(Color) des cellules. Une couleur par état possible.
	 */
	public Color[] getColors() {
		return colors;
	}
	
	/**
	 * Getter de la hauteur du tableau.
	 * @return Renvoie la hauteur du tableau.
	 */
	public int getHauteur() {
		return height;
	}
	
	/**
	 * Getter de la Largeur du Tableau.
	 * @return Renvoie la Largeur du Tableau.
	 */
	public int getLargeur() {
		return width;
	}
	
	/**
	 * Getter du nombre d'états possible d'une cellule.
	 * @retun Renvoie le nombre d'état accessible par chaque cellule du tableau.
	 */
	public int getnStates() {
		return nStates;
	}

	/**
	 * Setter du Contenu du tableau de cellules
	 * @param cells Tableau d'entiers représentant l'ensemble des cellules
	 */
	public void setCells(int[][] cells) {
		for (int i = 0; i < height; i++) {
			for(int j = 0; j< width; j++) {
				this.cells[i][j] = cells[i][j];
			}
		}
	}	
	
	/**
	 * Actualise les états de chacune des cellules du tableau.
	 */
	public void update() {
		int[][] newState = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				newState[i][j] = rule(i,j);
			}
		}
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = newState[i][j];
			}
		}
	}
	
	// Fonctions Utiles	
	/**
	 * définie la règle de modification de l'état d'une cellule
	 * @param i Indice de la ligne de la cellule actualisée.
	 * @param j Indice de la colonne de la cellule actualisée.
	 * @return Renvoie le nouvel état(int) de la cellule considérée.
	 */
	abstract public int rule(int i,int j);
	/**
	 * Explore le voisinnage de la cellule considéré et Renvoie un entier selon la règle utilisée.
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @param status Etat utilisé par la règle d'actualisation de la cellule
	 * @return Renvoit le nombre de voisin autour de la cellule, suivant la règle utilisée.
	 */
	abstract public int neighbourState(int i, int j, int status);
	

	// Fonctions d'affichage
	/**
	 * Reinitialise l'affichage et l'état du tableau de cellule.
	 */
	public void reInit() {
		Random gen = new Random();
		cells = new int[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				cells[i][j] = gen.nextInt(nStates);
			}
		}
	}
	
	/**
	 * Dessine sur l'interface graphique l'ensemble des cellules du tableau de cellule.
	 * @param	gui Interface graphique sur laquelle va s'afficher le tableau de cellule.
	 */
	public void draw(GUISimulator gui) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				int status = cells[i][j];
				Color color = colors[status];
				if (status != 0) {					
					gui.addGraphicalElement(new Rectangle(i*cellSize, j*cellSize, color, color, cellSize));
				}
			}
		}
	}
}