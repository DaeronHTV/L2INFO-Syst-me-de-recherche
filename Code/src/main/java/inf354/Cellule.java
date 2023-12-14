/**
 * Classe représentant une cellule dans une liste chaînée.
 * La cellule contient un élément entier et une cellule suivante.
 */
 
 package inf354;
 
public class Cellule {
		public int occurence;
		public int terme;
		public Cellule succ;
		
		/**
		 * Construit une cellule.
		 * @param e valeur de l'élément.
		 * @param suc Cellule suivante.
		 */
		public Cellule(int occu, int term, Cellule suc) {
			this.occurence = occu;
			this.terme = term;
			this.succ=suc;
		}
		
		
		/**
		 * Construit une cellule avec une valeur d'élément et aucune cellule suivante.
		 * @param e valeur entière de l'élément.
		 */
		public Cellule(int occu, int term) {
			this.occurence = occu;
			this.terme = term;
		}
		
		/**
		 * Construit une Cellule avec une valeur pour le terme à -1, inutilisable pour la matriceIndex en l'état
		 */
		public Cellule() {
			this.occurence = 0;
			this.terme = -1;
		}
		
	}
