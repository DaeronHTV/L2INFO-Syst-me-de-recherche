package inf354;
import java.io.*;
	
public class AccesSequentielM1Naif  {

	// attributs
	public String nomFic;
	public String motCourant;
	public File f;
	public FileReader fr;
	public int i=0;
	
	// constructeur
	public AccesSequentielM1Naif(String s) {
		this.nomFic=s;
		this.motCourant=null;
	}
	
	// separateur 
	private char [] separateur = {'«','»','’',(char)0,'\n','-',',','?',';','.','/','"',':','!','_','\'',' ','(',')','%','&','*'};
	
		public boolean estSeparateur(char c) {
			int i=0;
			while(i!=separateur.length && c!=separateur[i]) {
				i++;
			}
			return i!=separateur.length;
		}
	
		public void setSeparateur() {
			for(int i=0;i<separateur.length;i++) {
				separateur[i]=' ';
			}
			separateur[0]='\n';
			separateur[1]=(char)0;
		
		}	
	
	
	
		// methodes 
		public void demarrer() throws FileNotFoundException, IOException {
			this.fr=new FileReader(new File(nomFic));
			i=0;
			motCourant ="";
			avancer();
		}
	
	
		public void fermer(){
			try{
			    fr.close();
			}catch (IOException e){
			 System.out.println("Erreur lors de la fermeture "+e);
			}
		}
	
	
	
		public void avancer() throws FileNotFoundException, IOException {
			String mot="";
			while(i!=-1 && estSeparateur((char)i)) {
				i=fr.read();
			}
			while(i!=-1 && !estSeparateur((char)i)) {
				mot+=(char)i;
				i=fr.read();
			}
			motCourant=mot.toLowerCase();;
		}
	
	
		public boolean finDeSequence() {
			return i==-1;
		}
	
	
		public String elementCourant() {
			affinage();
			return motCourant;
		}

	
		public void affinage() {
			int i=0;
			String aff="";
			while(i!=motCourant.length()) {
				int c=(int)motCourant.charAt(i);
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
			motCourant=aff;
		}			
	
}
