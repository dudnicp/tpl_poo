package cells;
/**
 * Classe étendu de CellTab gérant l'actualisation d'un tableau de Cellule de type Conway
 * @author Groupe 65
 */
public class ConwayTab extends CellTab{
	
	// Constructeur
	/**
	 * Constructeur d'un tableau de Cellule de type: Jeu de la Conway
	 * @param height Hauteur du tableau de cellule
	 * @param width Largeur du tableau de cellule
	 * @param cellSize Dimensions(int) d'une cellule carré
	 */
	public ConwayTab(int height, int width, int cellSize) {
		super(height, width, defaultStateNumber, cellSize);
	}
	
	// FOnctions Utiles
	/**
	 * Renvoie le nombre cellule avec l'état "vivant" (status = 1) autour de la cellule considérée
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @param status Etat actuelle de la cellule considérée.
	 * @return Renvoie le nombre(int) de voisin vivant autour de la cellule
	 */
	@Override
	public int neighbourState(int i, int j, int status) {
	int sum = - cells[i][j];
	for (int a = -1; a <= 1; a++) {
		for (int b = -1; b <= 1; b++) {
			sum += cells[(height+i+a) % height][(width+j+b) % width];
		}
	}
	return sum;
}	

	/**
	 * Définissions de la règle de Conway pour l'état d'une cellule
	 * @param i Indice de la ligne de la cellulle considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @return Renvoie le nouvel état de la cellule considérée selon les règles du jeu de la vie.
	 */
	@Override
	public int rule(int i, int j) {
		int status = 0;
		int k = neighbourState(i, j, 1);
		if (cells[i][j] == 1) { // cellule vivante
			if (k == 2 || k == 3){ // cellule reste vivante
				status = 1;
			}
		}
		else {  // la cellule est morte
			if (k == 3) { // naissance
				status = 1;
			}
		}
		return status;
	}

}
