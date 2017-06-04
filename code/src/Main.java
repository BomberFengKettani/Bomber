import Model.Jeu;
import Model.Joueur;
import Model.Son;
import Model.Menu;
import View.Fenetre;
import controle.Clavier;

// David
public class Main{
	public static void main(String[] args) {
		
		Joueur Joueur1 = new Joueur(1,15,2,"Joueur1",3,3,0); // (x,y,couleur,"joueur",maxbomb,vie,numJoueur)
		Joueur Joueur2 = new Joueur(19,1,6,"Joueur2",3,3,1);
		
		Fenetre fenetre = new Fenetre();
		Jeu jeu = new Jeu(fenetre, Joueur1, Joueur2);
		Clavier clavier = new Clavier(jeu);
		fenetre.setKeyListener(clavier);
		while(true){
			if(jeu.isEnd() == true){
				jeu.annonceVictoire();
			}
		}
		
		//Son.jouerSon("/Sons/theme.wav");
	
	}
}