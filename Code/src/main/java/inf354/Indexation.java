package inf354;

import java.io.*;

public class Indexation {
	//public File Stop = new File("/ext/nguyenar/Bureau/version1projet");
	public File Stop;
	public BufferedReader lecture;
	public String [] StopList = new String[500];

	public void Indexation() throws FileNotFoundException, IOException{
		this.Stop = new File("inf354/stoplist.txt");
		this.lecture = new BufferedReader(new FileReader(this.Stop));
		String Ligne = lecture.readLine();
		int i = 0;
		String Mot = "";
		while (i != Ligne.length()){
			if (Ligne.charAt(i) != ' '){
				Mot = Mot + Ligne.charAt(i);
				i++;
			}
			else{
				this.StopList[i] = Mot;
				Mot = "";
				i++;
			}
		}
	}

	public static void main(String [] arg) throws FileNotFoundException, IOException {
		// donnees
		Indexation I1 = new Indexation();
		DictionnaireHash dmot;
		DictionnaireHash ddoc;
		MatriceIndexNaif mind;
		File f = new File(arg[0]);
		String list [] = f.list();
		AccesSequentielM1Naif a;
		// realisation
		if(list==null) {
			System.out.println("corpus vide !");
		}
		else{
			ddoc=new DictionnaireHash(list.length);
			dmot=new DictionnaireHash(300000);
			mind=new MatriceIndexNaif(300000);
			for(int i=0;i<list.length;i++) {
					ddoc.ajouterMot(list[i]);
					System.out.println(list[i]+" "+i);
					a=new AccesSequentielM1Naif(arg[0]+"/"+list[i]);
					a.demarrer();
					while(!a.finDeSequence()) {
							dmot.ajouterMot(a.elementCourant());						
							mind.incremente(i,dmot.indiceMot(a.elementCourant()));
						a.avancer();
					}	
					a.fermer();
			}
			mind.sauver("inf354/txtindex/matIndex");
			dmot.sauverDicoHash("inf354/txtindex/dicoMots");
			ddoc.sauverDicoHash("inf354/txtindex/dicoDoc");
		}
	}
}


