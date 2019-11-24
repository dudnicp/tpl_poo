package cells;
/**
* Classe étendu de CellTab gérant l'actualisation d'un automate cellulaire selon les règles de l'immigration
* @author Groupe 65
*/
public class ImmigrationTab extends CellTab {
	// Constructeur
	/**
	 * Constructeur d'un automate cellulaire de type Immigration
	 * @param height Hauteur du tableau de cellule
	 * @param width Largeur du tableau de cellule
	 * @param nStates Nombre d'état possible accessible par une cellule.
	 * @param cellSize Dimension d'une cellule carré
	 */
	public ImmigrationTab(int height, int width, int nStates, int cellSize) {
		super(height, width, nStates, cellSize);
	}
	
	/**
	 * Renvoie le nombre de voisin qui ont un état(int) différent de la cellule considérée.
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @param status Etat actuel de la cellule considérée.
	 * @return Renvoie le nombre de voisin qui ont un état(int) différent de la cellule considérée.
	 */
	@Override
	public int neighbourState(int i, int j, int status) {
		int sum = 0;
		for(int a = -1; a < 2; a++) {
			for(int b = -1; b < 2; b++) {
				int val = cells[(height + i + a) % height][(width + j + b) % width];
				sum += (val == (status + 1) % nStates) ? 1 : 0;
			}
		}
		return sum;
	}
	
	/**
	 * Définition de la règle d'actualisation de type Immigration
	 * @param i Indice de la ligne de la cellule considérée.
	 * @param j Indice de la colonne de la cellule considérée.
	 * @return actualise l'état de la cellule considérée selon la règle de l'Immigration
	 */
	@Override
	public int rule(int i, int j) {
		int status = cells[i][j];
		int K = neighbourState(i, j, status);
		if( K > 2) {
			status = (status + 1) % nStates;
		}
		return status;
	}
}

