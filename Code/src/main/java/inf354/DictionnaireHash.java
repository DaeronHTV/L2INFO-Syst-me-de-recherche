package inf354;

import java.io.*;
import java.lang.Math;

public class DictionnaireHash /*implements Dictionnaire*/{
	int longueurMax;
	CelluleDico[] dicohash;
	int nbMot = 0;
	String[] dicosimple;

	public DictionnaireHash(int nlong){
		this.longueurMax = nlong;
		this.dicohash = new CelluleDico[longueurMax];
		dicosimple = new String[longueurMax];
	}
	
	public void sauverDicoHash(String nomDeFichier) throws FileNotFoundException{
		try{
    	    File f = new File(nomDeFichier);
    	    FileWriter fw = new FileWriter(f);
    	    int i=0;
    	    while (i!=nbMot){
    		    fw.write(motIndice(i)+" "+i+" ");
    		    i++;
    	    }
    	    fw.close();
        }
        catch(IOException io) {}    		    		
    }
	 
	public void lectureDicoHashDepuisTXT(String nomDeFichier) throws FileNotFoundException{
		try{
    	    InputStream flux = new FileInputStream(nomDeFichier); 
		    InputStreamReader lecture = new InputStreamReader(flux);
		    BufferedReader buff = new BufferedReader(lecture);
		    String letexte;
		    letexte = buff.readLine();
		    int c = 0;
		    String mactu;
		    int nindex;
		    while (c != letexte.length()){
			    mactu = LireMot(letexte,c);
			    c+= mactu.length()+1;
			    nindex = LireNombre(letexte,c);
			    c+= String.valueOf(nindex).length()+1;
			    ajouterMotSurEtCertain(mactu,nindex);
			    //System.out.println(motIndice(nindex)+" "+indiceMot(mactu));
		    }
		}catch(IOException io) {
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
    
    public String LireMot(String texte, int depart){
    	int i = depart;
    	String lemot = "";
    	while ((i!=texte.length())&&(texte.charAt(i)!= ' ')){
    		lemot += texte.charAt(i);
    		i++;
    	}
    	return lemot;
   	}
    
    public int indiceMot(String s){	//	renvoie -1 si le mot n'est pas trouvé
    	String l = s.toLowerCase();
    	if(contient(l)){
    		CelluleDico cc = this.dicohash[Math.abs(l.hashCode() % longueurMax)];
    		if((cc!=null) && (cc.Mot==l)){
				return cc.index;
    		}
			else{
				while ((cc.succ != null) && ((cc.Mot!=l))){
					cc = cc.succ;
				}	
			}
    		return cc.index;
    	}
        return -1;
    }

    public String motIndice(int index){
    	return dicosimple[index];
    }

    public boolean contient(String l){
    	//String l = s.toLowerCase();
    	if (dicohash[Math.abs(l.hashCode() % longueurMax)] != null){
    		CelluleDico cc = this.dicohash[Math.abs(l.hashCode() % longueurMax)];
    		while ((cc != null) && ((!cc.Mot.equals(l)))){
    			cc = cc.succ;
    		}
    		return (cc != null);
    	}
        return false;
    }
    
    public void ajouterMot(String s) {
		String m = s.toLowerCase();
		if(!contient(m)) {
			if (dicohash[Math.abs(m.hashCode()) % longueurMax]==null){
				dicohash[Math.abs(m.hashCode()) % longueurMax]=(new CelluleDico(m,nbMot));
				dicosimple[nbMot]=m;
				nbMot++;
			}
			else{
				CelluleDico cc = dicohash[Math.abs(m.hashCode()) % longueurMax];
				CelluleDico cp = new CelluleDico();
				while (cc != null){
					cp = cc;
					cc = cc.succ;
				}
				cp.succ = new CelluleDico(m,nbMot);
				dicosimple[nbMot]=m;
				nbMot++;
			}
		}
	}
   
   	public void ajouterMotSurEtCertain(String s,int n) {
		String m = s.toLowerCase();
		if (dicohash[Math.abs(m.hashCode()) % longueurMax]==null){
			dicohash[Math.abs(m.hashCode()) % longueurMax]=(new CelluleDico(m,n));
			dicosimple[n]=m;
			nbMot++;
		}else{
			CelluleDico cc = dicohash[Math.abs(m.hashCode()) % longueurMax];
			CelluleDico cp = cc;
			while (cc != null){
				cp = cc;
				cc = cc.succ;
			}
			cp.succ = new CelluleDico(m,n);
			dicosimple[n]=m;
			nbMot++;	
		}
	} 
    
    public static void main(String[] args) throws FileNotFoundException {
		DictionnaireHash ouidico = new DictionnaireHash(200000);
		//ouidico.lectureDicoHashDepuisTXT("inf354/dataDico.txt");	//erreur si il n'y a pas deja un dataDico.txt
		ouidico.ajouterMot("soldat1ermot");
		ouidico.ajouterMot("toucan2ememot");
		ouidico.ajouterMot("jeveuxqueceamarche");	//remplacer les "inf354/dataDico.txt" par "dataDico.txt" si compil dans inf354
		ouidico.ajouterMot("le4emotmagique");
		ouidico.ajouterMot("lalalalalala");
		ouidico.ajouterMot("lalalalalala");
		/*System.out.println(ouidico.indiceMot(ouidico.motIndice(4)));
		System.out.println(ouidico.indiceMot("lalalalalala"));
		System.out.println(ouidico.motIndice(4));
		*/
		//ouidico.sauverDicoHash("inf354/dataDico.txt");
		/*int i = 0;
		while (i != ouidico.nbMot){
		System.out.println(ouidico.motIndice(i));
		i++;}*/
	}
}
