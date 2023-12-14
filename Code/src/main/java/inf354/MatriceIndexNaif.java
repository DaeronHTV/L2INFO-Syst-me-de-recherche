package inf354;

import java.io.*;

public class MatriceIndexNaif /*implements MatriceIndex*/ {
	//attributs
	public int longueurMax;
	public Cellule[] mt;
	
	// constructeur
	public MatriceIndexNaif(int nlong){	//nlarg inutile
		this.longueurMax = nlong;
		this.mt = new Cellule[longueurMax];
	}
		
    /**
     * Sauvegarde de la matrice d'occurence dans le fichier nomDeFichier. Le format est libre,
     * mais doit privilégier la vitesse de chargement et la compacité (taille du fichier).
     *
     * @param nomDeFichier
     */
	public void sauver(String nomDeFichier) throws FileNotFoundException {	//il faut aussi sauver le dico dans un autre 												//fichier : pas encore fait
    	try {
			File f = new File(nomDeFichier);
    		FileWriter fw = new FileWriter(f);
    		Cellule cc;
    		int i=0;
    		while (i!=longueurMax){
    			if (this.mt[i] != null){
    				cc = this.mt[i];
    				while (cc != null){
    					fw.write(""/*+i+" "*/+cc.terme+" "+cc.occurence+" ");
    					cc = cc.succ;	//Ne pas rajouter de "/n", merci bisous
    				}
    				fw.write("/");
    			}
    			i++;
    		}
    		fw.close();
    	}
    	catch(IOException io) {}    		    		
    }
    
    /*
    * Permet de remplir une matrice avec les éléments d'un txt
    * la matrice utilisant lectureMatriceDepuisTXT doit avoir des longueurMax et LargeurMax sup ou égales à la matrice lue
    */
	public void lectureMatriceDepuisTXT(String nomDeFichier) throws FileNotFoundException{
		try {
    		InputStream flux = new FileInputStream(nomDeFichier); 
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String letexte;
			letexte = buff.readLine();
			int c=0;
			int idoc=0;
			int numdoc;
			int numterm;
			int valeur;
			Cellule cc;
			while (c != letexte.length()){
				/*
				numdoc = LireNombre(letexte,c); //il faut que c avance
				c+= String.valueOf(numdoc).length()+1;
				*/
				numterm = LireNombre(letexte,c);
				//System.out.println("numterm :"+letexte.charAt(c));
				c+= String.valueOf(numterm).length()+1;
				valeur = LireNombre(letexte,c);
				//System.out.println("valeur :"+letexte.charAt(c));
				//System.out.println(""+idoc+" "+numterm+" "+valeur);
				c+= String.valueOf(valeur).length()+1;
				affecte(idoc,numterm,valeur);
				//System.out.println(""+idoc+" "+numterm+" "+valeur);
				if (letexte.charAt(c)=='/'){
					idoc++;
					c++;
				}
				
			}
		} 
		catch(IOException io) {
            System.out.println("IO error");
            System.exit(1);
    	}	
    }
    
	public int LireNombre(String texte, int depart){
    	int i = depart;
    	int somme = 0;
    	while ((i!=texte.length())&&(texte.charAt(i)!= ' ')){
    		somme = somme * 10 + (int)(texte.charAt(i))-48;
    		i++;
    	}
		return somme;
    }

    /**
     * retourne le nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @return       le nombre d'occurences du terme dans le document
     */
	public int val(int ndoc, int nterm){
    	Cellule cc = this.mt[ndoc];
    	while ((cc != null) && ((cc.terme !=nterm))){
    		cc = cc.succ;
    	}
    	if (cc != null){
    		return cc.occurence;
    	}
		return 0;
    }

    /**
     * Ajoute 1 au nombre d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     */
    public void incremente(int ndoc, int nterm){
		if (this.mt[ndoc] != null){
			Cellule cc = this.mt[ndoc];
			Cellule cp = cc;
			if (cc.terme==nterm){
				this.mt[ndoc]=new Cellule(cc.occurence+1,nterm,cc.succ);
			}
			else{
				while ((cc != null)&&(cc.terme !=nterm)){
					cp = cc;
					cc = cc.succ;
				}
				if (cc != null){
					cp.succ = new Cellule(cc.occurence+1,nterm,cc.succ);
				}
				else{
					cp.succ = new Cellule(1, nterm);
				}
			}	
    	}
		else{	//si la séquence est null
    		this.mt[ndoc]=new Cellule(1,nterm);
    	}
	}

    /**
     * affecte à val le numéro d'occurences du terme numéro nterm dans le document numéro ndoc.
     * @param  ndoc  le numéro du document
     * @param  nterm le numéro du terme
     * @param val    la nouvelle valeur du nombre d'occurence
     */
    public void affecte(int ndoc, int nterm, int val){
		if (this.mt[ndoc] != null){
			Cellule cc = this.mt[ndoc];
			Cellule cp = cc;	
			if ((cc.terme==nterm)){
				this.mt[ndoc]=new Cellule(val,nterm,cc.succ);
			}else{
    			while ((cc != null)&&(cc.terme !=nterm)){
    				cp = cc;
    				cc = cc.succ;
    			}
    			if (cc != null){
    				cp.succ = new Cellule(val,nterm,cc.succ);
    			}else{
    				cp.succ = new Cellule(val, nterm);
    			}
			}
    	}else{	//si la séquence est null
    		this.mt[ndoc]=new Cellule(val,nterm);
		}		
	}

	public static void main(String[] args) throws FileNotFoundException {
		MatriceIndexNaif ouimt = new MatriceIndexNaif(10);
		ouimt.lectureMatriceDepuisTXT("data.txt");
		/*
		ouimt.incremente(0,0);
		ouimt.incremente(0,2);
		ouimt.incremente(0,1);
		ouimt.incremente(2,0);
		ouimt.incremente(0,1);
		ouimt.affecte(0,0,4);
		ouimt.incremente(0,0);
		ouimt.affecte(1,1,12);
		ouimt.affecte(2,2,4);
		ouimt.incremente(2,2);
		*/
		ouimt.incremente(1,1);
		ouimt.sauver("data.txt");
		System.out.println(ouimt.val(0,0));
		System.out.println(ouimt.val(0,2));
		System.out.println(ouimt.val(0,1));
		System.out.println(ouimt.val(2,2));
	}
}



