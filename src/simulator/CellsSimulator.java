package simulator;
import cells.*;
import events.*;
import gui.*;

/**
* Classe de simulateur simulant l'évolution d'un automate Cellulaire.
* @author Groupe 65
*/
public class CellsSimulator extends Simulator {
	private CellTab tab;
	
	/**
	 * Constructeur par défaut de la simulation de l'automate cellulaire sur l'interface graphique chosie.
	 * @param tab Tableau de Cellule(CellTab) évoluant dans la simulation.
	 * @param parentGUI Interface Graphique choisie.
	 */
    public CellsSimulator(CellTab tab, GUISimulator parentGUI) {
    	super(parentGUI);
    	this.tab = tab;    
        addEvent(new CellsUpdateEvent(this));        
        tab.draw(parentGUI);
    }
    
    /**
     * renvoie l'automate cellulaire évoluant dans la simulation.
     * @return Renvoie l'automate cellulaire évoluant dans la simulation.
     */
    public CellTab getTab() {
		return tab;
	}
    
    /**
     * Réinitialise la simulation ainsi que l'état des cellules de l'automate cellulaire.
     */
    @Override
    public void restart() {
        super.restart();
        tab.reInit();
        addEvent(new CellsUpdateEvent(this));
        tab.draw(parentGUI);
    }
}
