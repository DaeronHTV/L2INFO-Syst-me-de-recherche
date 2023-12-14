package inf354;
import java.util.*;
import java.io.*;

/**
 * Classe effectuant la recherche des fichiers en fonction des termes rentrés par
 *l'utilisateur
 */

/** Chose à faire pour améliorer le code
*	-> Tout réunir dans un seul code afin d'éviter d'avoir un tableau en attribut pour les mots de l'utilisateur
*	-> Supprimer l'attribut utilisateur
*	-> Supprimer LongueurMax
**/
public class RechercheNaifTrecEval /*implements Recherche*/ {
	//Attribut(s) de la classe
	private int LongueurMax = 1000;
	private String[] recherche;
	private int nbmotrech =0 ;
	private String utilisateur;
	private double[] classement;	//classement des docs par rapport a leur poids
	private DictionnaireHash DicoDesDocs = new DictionnaireHash(300000);
	private DictionnaireHash DicoDesMots = new DictionnaireHash(300000);
	private MatriceIndexNaif MatriceDesIndex = new MatriceIndexNaif(300000);
	//FileWriter fw;
	//AccesSequentielM1Naif a;

	//Constructeur(s)
	public RechercheNaifTrecEval(){
		this.recherche = new String[LongueurMax];
		try{	//création des deux dictionnaires et de la matrice d'occurence a partir des fichiers txt
		System.out.println("Dictionnaire des doc...");
		DicoDesDocs.lectureDicoHashDepuisTXT("inf354/txtindex/dicoDoc");
		System.out.println("Dictionnaire des doc terminé");
		System.out.println("Dictionnaire des mots...");
		DicoDesMots.lectureDicoHashDepuisTXT("inf354/txtindex/dicoMots");
		System.out.println("Dictionnaire des mots terminé");
		System.out.println("MatriceIndex...");
		MatriceDesIndex.lectureMatriceDepuisTXT("inf354/txtindex/matIndex");
		System.out.println("MatriceIndex terminée");
		}catch(IOException io) {
            System.out.println("IO error");
            System.exit(1);
    	}
	}
	
	

		//Méthode(s)

		public void viderRecherche(){
			String[] vide = new String[LongueurMax];
			recherche = vide;
		}



		public void IntegrerElts(String requete) throws IOException{
			//renmplir le tableau Recherche[] avec le contenu de "requete"
			try{
			    viderRecherche();
			    System.out.println("Requete actuelle :"+"inf354/requetes1/"+requete);
			    InputStream flux = new FileInputStream("inf354/requetes1/"+requete);
			    InputStreamReader lecture = new InputStreamReader(flux);
			    BufferedReader buff = new BufferedReader(lecture);
			    String letexte ="";
			    while ((utilisateur = buff.readLine()) != null){
				  letexte += utilisateur;
			    }
			    //System.out.println("MACRON DEMISSION");
		
			    nbmotrech = 0;
			    this.utilisateur = letexte;
			    int i = 0;
			    String Mot = ""; 
			    while(i < this.utilisateur.length()){ //Parcours de la chaîne de caractère
				    Mot = "";
				    while ((i != this.utilisateur.length())&&(!estUnSeparateur(utilisateur.charAt(i)))){
					    Mot += utilisateur.charAt(i);
					    i++;
			            }
			    Mot = affinage2(Mot);
			    if (!estUnMotCourant(Mot)){
			        recherche[nbmotrech]=Mot;
			    //System.out.println("recherche["+nbmotrech+"] : "+recherche[nbmotrech]);
			    nbmotrech++;
			    }
			    i++;
		            }
		        }catch(IOException io) {
                              System.out.println("IO error");
                              System.exit(1);
                         }
	        }
	
		//Calcul du poids de chaque terme de la requête dans les documents avec le facteur binaire
		//puis avec le facteur logarithmique
		public void poids(){
			//facteur binaire
			int[] pondGlob = new int[recherche.length];
			int k = 0;
			int k2 = 0;
			int m;
			while(recherche[k2] != null){
				m = 0;
				k = 0;
			while(k < 87190){
				if (MatriceDesIndex.val(k,DicoDesMots.indiceMot(recherche[k2]))>0){ 
					m++;
					//System.out.println("je veux gagner");
				}
				k++;
			}
			pondGlob[k2]=m;
			//System.out.println(pondGlob[k2]);
			k2++;
			}
		
			//facteur logarithmique
			classement = new double[87190];
			int i =0;
			while(i < 87190){
				double sc = 0;
				double idf;
				for(int j=0; recherche[j] != null; j++){
					if (MatriceDesIndex.val(i,DicoDesMots.indiceMot(recherche[j]))>0){
						if (pondGlob[j]!=0){idf = 1+Math.log10(87190/pondGlob[j]);
						}else{
						idf = 0;
						}
						sc+=(1+Math.log10(MatriceDesIndex.val(i,DicoDesMots.indiceMot(recherche[j])))*idf);
					}
				}
			classement[i] =sc ;
			i++;
			}
		}
	

	
	
	
		public void affichageClassement(int n){
			int i = 0;
			int majo;
			while (i != n){
				majo = posMajorantTableau(classement);
				classement[majo]=0;
				i++;
			}
		
		}
	
		//Affiche l'indice du majorant dans le classement
		public int posMajorantTableau(double tabi[]){
			int i = 0;
			double maj = 0;
			int posmajo =0;
			while (i != tabi.length){
				if (maj < tabi[i]){
					maj = tabi[i];
					posmajo = i;
				}
				i++;
			}
			return posmajo;
		}
	
	

		//Test(s) supplémentaire(s)
		//renvoie vrai si la mot courant fait partie de la liste
		//renvoie faux sinon
		public static boolean estUnMotCourant(String s){
			//Retourne vrai ssi le mot s est un mot courant
			int i = 0;
			String[] MotCourant = {"au","aux","avec","ce","ces","dans","de","des","du","elle","en","et","eux","il","je",
			"la","le","leur","lui","ma","mais","me","même","mes","moi","mon","ne","nos","notre","nous","on","ou","par","pas","pour"
			,"qu","que","qui","sa","se","ses","son","sur","ta","te","tes","toi","ton","tu","un","une","votre","vous","c","d","j","l"
			,"a","m","n","s","t","y","été","étée","étées","étés","étant","suis","es","est","sommes","êtes","sont","serai","seras","sera"
			,"serons","serez","seront","serais","serait","serions","seriez","seraient","étais","était","étions","étiez","étaient","fus",
			"fut","fumes","futes","furent","sois","soit","soyons","soyez","soient","fusse","fusses","fût","fussions","fussiez","fussent",
			"ayant","eu","eue","eues","eus","ai","as","avons","avez","ont","aurai","auras","aura","aurons","aurez","auront","aurais","aurait"
			,"aurions","auriez","auraient","avais","avait","avions","aviez","avaient","eut","eumes","eutes","eurent","aie","aies","ait","ayons"
			,"ayez","aient","eusse","eusses","eût","eussions","eussiez","eussent","ceci","celà","cet","cette","ici","ils","les","leurs","quel",
			"quels","quelle","quelles","sans","soi","abord","afin","ah","ai","aie","ainsi","allaient","allo","allons","apres","assez","attendu","au","aucun","aucune","aujourd","auquel","aura","auront","aussi","autre","autres","aux","auxquelles","auxquels","avaient","avais","avait","avant","avec","avoir","ayant","bah","beaucoup","bien","bigre","boum","bravo","brrr","ca","car","ce","ceci","cela","celle","ci","celles","celui","cent","cependant","certain","certaine","certaines","certains","certes","ces","cet","cette","ceux","chacun","chaque","cher","chere","cheres","chers","chez","chiche","chut","ci","cinq","cinquantaine","cinquante","cinquantième","cinquième","clac","clic","combien","comme","comment","compris","concernant","contre","couic","crac","da","dans","de","debout","dedans","dehors","dela","depuis","derriere","des","desormais","desquelles","desquels","dessous","dessus","deux","deuxieme","deuxiemement","devant","devers","devra","different","differente","differentes","differents","dire","divers","diverse","diverses","dix","dixieme","doit","doivent","donc","dont","douze","douzieme","dring","du","duquel","durant","e","effet","eh","elle","elles","en","encore","entre","envers","environ","es","est","et","etant","etaient","etais","etait","etc","ete","etre","eu","euh","eux","excepte","façon","fais","faisaient","faisant","fait","feront","fi","flac","floc","font","g","gens","h","ha","he","hein","helas","hem","hep","hi","ho","hola","hop","hormis","hors","hou","houp","hue","hui","huit","huitieme","hum","hurrah","i","il","ils","importe","je","jusqu","jusque","la","laquelle","las","le","lequel","les","lesquelles","lesquels","leur","leurs","longtemps","lorsque","lui","ma","maint","mais","malgré","me","meme","memes","merci","mes","mien","mienne","miennes","miens","mille","mince","moi","moins","mon","moyennant","na","ne","neanmoins","neuf","neuvieme","ni","nombreuses","nombreux","non","nos","notre","notres","nous","nul","oh","ohe","ole","olle","on","ont","onze","onzieme","ore","ou","ouf","ouias","oust","ouste","outre","paf","pan","par","parmi","partant","particulier","particuliere","particulierement","pas","passe","pendant","personne","peu","peut","peuvent","peux","pff","pfft","pfut","pif","plein","plouf","plus","plusieurs","plutot","pouah","pour","pourquoi","premier","premiere","premierement","pres","proche","psitt","puisque","qu","quand","quant","quanta","quant","quarante","quatorze","quatre","quatrieme","quatriemement","que","quel","quelconque","quelle","quelles","quelque","quelques","quelqu","quels","qui","quiconque","quinze","quoi","quoique","revoici","revoila","rien","sa","sacrebleu","sans","sapristi","sauf","se","seize","selon","sept","septieme","sera","seront","ses","si","sien","sienne","siennes","siens","sinon","six","sixieme","soi","soit","soixante","son","sont","sous","stop","suis","suivant","sur","surtout","ta","tac","tant","te","tel","telle","tellement","telles","tels","tenant","tes","tic","tien","tienne","tiennes","tiens","toc","toi","ton","touchant","toujours","tous","tout","toute","toutes","treize","trente","tres","trois","troisieme","troisiemement","trop","tsoin","tsouin","tu","un","une","unes","uns","va","vais","vas","ve","vers","via","vif","vifs","vingt","vivat","vive","vives","vlan","voici","voila","vont","vos","votre","votres","vous","vu","zut","trouve","trouvez","trouvée","trouvées","trouves","trouver","bonjour","salut","bonsoir","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","dresses","dressees","dresse","dressee","prises","rapports","rapport","pose","posees","poses","posee","poser","appliquer","pris","prise","documents","traiter","traitent","traites","traite","traitee","traitees","articles","article","reclament","reclamer","reclame","reclames","reclamez","mentionnant","savoir","pourraient","pourrait","abordent","aborder","aborde","abordee","abordes","abordees","presenter","presentent","tenir","abordant","traitant","lors","fournir","informations","informations","moins","indiquer","indique","indiquee","indiques","indequees","indiquant","plupart","reclamer","reclament","reclamant","reclames","reclamees","reclame","reclamee","depuis","san","colle","collee","collees","colles","juste","affirme","affirmee","affirmes","affirmees","vendredi","affirme","deja","faire","aller","petit","petites","petite","petits","donner","donne","donee","donnes","donnees","aupres","informations","information","rendent","renvoie","tg","ensuite","prendre"};
		
			while ((i != MotCourant.length) && (!s.toLowerCase().equals(MotCourant[i].toLowerCase()))){
				i++;
			}
			return (i != MotCourant.length);
		}
	
	 	//Retourne vrai ssi le caractère c est un séparateur
		 public static boolean estUnSeparateur(char c){ 
    		 	int i =0;
    	 		String[] Separateur = {".",";",":","'",","," ","(",")","[","]","-"};
    			 while ((i != Separateur.length) && (c != Separateur[i].charAt(0))){
    				 i++;
    			 }
    	 		return (i != Separateur.length); 
    		}


	
		public void ecrirepourtreceval(String fichierrequete) throws IOException {
			int i = 0;
			int majo;
    			FileWriter fw = new FileWriter(new File("trec_eval_1"),true);
			//System.out.println(fichierrequete);
			IntegrerElts(fichierrequete);
			poids();
			while (i != 800){
				majo = posMajorantTableau(classement);
				if (!(fichierrequete.charAt(1)==('0'))){
				//System.out.println(fichierrequete.charAt(0));
				fw.write(""+fichierrequete.charAt(1));}
				fw.write(""+
				fichierrequete.charAt(2)+
				fichierrequete.charAt(3)+
				"\t"+
				"Q0"+
				"\t"+
				DicoDesDocs.motIndice(majo).toUpperCase()+
				"\t"+
				i+
				"\t"+
				classement[majo]+
				"\t"+
				"ver8_Bool_Log_StopList"+
				'\n');
				//fw.close();
				classement[majo]=-1;
				i++;
			}
			fw.close();
		
		}
	
		//supprime les accents si les caractères en possédent et les renvoie sans accents
		public String affinage2(String s) {
			int i=0;
			String aff="";
			while(i!=s.length()) {
				int c=(int)s.charAt(i);
				if(c<91) {
					c+=32;
				}
				switch(c) {
					case 224:
					case 225:
					case 226:
					case 227:
					case 228:
						aff+='a';
						break;
					case 236:
					case 237:
					case 238:
					case 239:
						aff+='i';
						break;
					case 249:
					case 250:
					case 251:
					case 252:
						aff+='u';
						break;
					case 232:
					case 233:
					case 234:
					case 235:
						aff+='e';
						break;
					case 242:
					case 243:
					case 244:
					case 245:
					case 246:
						aff+='o';
						break;
					default:
						aff+=(char)c;
				}
				i++;
			}
			return aff;
		}
	
	
	
	
	
		public static void main(String[] arg) throws FileNotFoundException {
		 	RechercheNaifTrecEval laRech = new RechercheNaifTrecEval();
		 	File f = new File(arg[0]);
		 	String list [] = f.list();
		 	if(list==null) {
				System.out.println("corpus vide !");
		 	}
		 	try{
    		 	File f2 = new File("trec_eval_1");
    			 FileWriter fw = new FileWriter(f2);
		 	int i = 0;
		 	while (i != list.length){
		 		laRech.ecrirepourtreceval(list[i]);
		 		i++;
			 }
		 	fw.close();
		 
			 }
    		catch(IOException io) {}
		}
		
}
