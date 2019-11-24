package cells;
import java.util.Random;
import java.awt.Point;
/**
* Classe étendu de CellTab gérant l'actualisation d'un automate cellulaire de type Shelling
* @author Groupe 65
*/
public class SchellingTab extends CellTab{
	private int ceil;
	private Point emptyHouses[];
	private int nEmptyHouses;
	
	// Le constructeur
	/**
	 * Constructeur de l'automate Cellulaire de type Shelling
	 * @param height Hauteur du tableau de cellule.
	 * @param width Largeur du tableau de cellule.
	 * @param nStates Nombre d'état possible accessible par une cellule du tableau.
	 * @param ceil Seuil de tolérance aux cellules différentes.
	 * @param nEmptyHouses Nombre de cellule représentant une place vacante.(status = 0)
	 * @param cellSize Dimension(int) des cellules carrées.
	 */
	public SchellingTab(int height, int width, int nStates, int ceil, int nEmptyHouses, int cellSize) {
		super(height, width, nStates, cellSize);
		this.ceil = ceil;
		this.nEmptyHouses = nEmptyHouses;
		this.emptyHouses = new Point[nEmptyHouses];
		reInit();
	}
	
	// Les Getters et Setters
	/**
	 * Renvoie le Seuil de tolérance de l'automate cellulaire.
	 * @return Renvoie le seuil de tolérance de l'automate cellulaire.
	 */
	public int getCeil() {
		return ceil;
	}
	
	/**
	 * Renvoie les positions des cellules vacantes(status = 0).
	 * @return Renvoie la liste des positions (Point[]) des cellules vacantes (status = 0).
	 */
	public Point[] getEmptyHouses() {
		return emptyHouses;
	}
	
	/**
	 * Renvoie le nombre(int) de cellules vacantes(status = 0)
	 * @return Renvoie le nombre(int) de cellules vacantes (status = 0).
	 */
	public int getNEmptyHouses() {
		return nEmptyHouses;
	}
	/**
	 * Modifie la valeur du seuil de tolérance de l'automate cellulaire.
	 * @param ceil Valeur du seuil de tolérance de l'automate cellulaire.
	 */
	public void setCeil(int ceil) {
		this.ceil = ceil;
	}	
	
	/**
	 * Modifie la position(Point[]) des cellules vacantes (status = 0) de l'automate cellulaire.
	 * @param emptyHouses Position(Point[]) des cellules vacantes (status = 0) de l'automate cellulaire.
	 */
	public void setEmptyHouses(Point[] emptyHouses) {
		for (int indice = 0; indice < nEmptyHouses;indice++) {
			this.emptyHouses[indice] = new Point(emptyHouses[indice]);
		}
	}

	// Fonctions  utiles
	/**
	 * Copie le contenue d'un tableau dans un autre.
	 * @param list1 Tableau d'élèment qui vont être copiés.
	 * @param list2 Tableau copie de list1.
	 */
	public void copy(int[][] list1, int[][] list2){
		for(int i = 0; i < list1.length; i++) {
			for(int j = 0; j < list1[0].length; j++) {
				list2[i][j] = list1[i][j];
			}
		}
	}
	
	/**
	 * Actualise l'état actuel de l'automate cellulaire selon les règles du Shelling.
	 */
	@Override
	public void update() {
		int[][] temp;
		temp = new int[height][width];
		copy(cells, temp);
		
		Random gen = new Random();
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (cells[i][j] != 0) { // maison non vide
					if (rule(i, j) == 1) { //elle déménage
						
						int r = gen.nextInt(nEmptyHouses);
						int x = emptyHouses[r].x;
						int y = emptyHouses[r].y;
						
						temp[x][y] = cells[i][j]; // on met la nouvelle addresse
						temp[i][j] = 0; // on vide l ancienne
						emptyHouses[r].x = i;
						emptyHouses[r].y = j;// on ajoute l'ancienne a la liste des vacantes
					}
				}
			}
		}
		copy(temp, cells);
	}
	
	/**
	 * Renvoie le nombre de voisin suivant la règle de Shelling autour de la cellule considérée.
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @param status Etat actuel de la cellule considérée.
	 * @return Renvoie le nouvel état de la cellule considérée suivant les Règles du Shelling.
	 */
	@Override
	public int neighbourState(int i, int j, int status) {
		int sum = 0;
		for (int a = -1; a < 2; a++) {
			for (int b = -1; b < 2; b++) {
				int val = cells[(height + i + a) % height][(width + j + b) % width];
				sum += (val != status) ? 1: 0;
			}
		}
		return sum;
	}
	
	/**
	 * Renvoie le nouvel état de la cellule considérée suivant les règles du Shelling.
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @return Renvoie le nouvel état(int) de la cellule considérée suivant les règles du Shelling.
	 */
	@Override
	public int rule(int i, int j) {
		return (neighbourState(i, j, cells[i][j]) >= ceil) ? 1 : 0;
	}
	
	/**
	 * Réinitialise l'état de l'ensemble des cellules de l'automate cellulaire.
	 */
	@Override
	public void reInit() {
		Random gen = new Random();
		int currentNEmptyHouses = 0;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if( currentNEmptyHouses < nEmptyHouses) {
					int r =  gen.nextInt(nStates);
					cells[i][j] = r;
					if( r == 0) {
						currentNEmptyHouses ++;
					}
				}
				else {
					int r =  1 + gen.nextInt(nStates - 1);
					cells[i][j] = r;
				}
			}
		}
		
		int index = 0;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				if(cells[i][j] == 0) {
					emptyHouses[index] = new Point(i,j);
					index ++;
				}
			}
		}
	}
}
