 package inf354;
 
public class CelluleDico {
	public String Mot;
	public int index;
	public CelluleDico succ;
		
	/**
	* Construit une cellule.
	* @param e valeur de l'élément.
	* @param suc Cellule suivante.
	*/
	public CelluleDico(String s, int term, CelluleDico suc) {
		Mot = s;
		index = term;
		succ=suc;
	}
		
	/**
	* Construit une cellule avec une valeur d'élément et aucune cellule suivante.
	* @param e valeur entière de l'élément.
	*/
	public CelluleDico(String s, int term) {
		Mot = s;
		index = term;
	}
		
	/**
	* Construit une Cellule avec une valeur pour le index à -1, inutilisable pour la matriceIndex en l'état
	*/
	public CelluleDico() {
		Mot = "";
		index = -1;
	}	
}
