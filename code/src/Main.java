import Model.Jeu;
import Model.Son;
import Model.Menu;
import View.Fenetre;
import controle.Clavier;

// David
public class Main{
	public static void main(String[] args) {
	
		Fenetre fenetre = new Fenetre();
		//Son.jouerSon("/Sons/Powerup.wav");
		Jeu jeu = new Jeu(fenetre);
		Clavier clavier = new Clavier(jeu);
		fenetre.setKeyListener(clavier);
		
	}
}