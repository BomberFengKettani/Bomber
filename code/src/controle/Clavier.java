package controle;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Model.Jeu;

public class Clavier implements KeyListener{
	private Jeu jeu;
	
	// Player ID Number
	private static final int Joueur1 = 0;
	private static final int Joueur2 = 1;
	
	public Clavier(Jeu jeu){
		this.jeu = jeu;
	}

	public void keyPressed(KeyEvent Touche) {
		int key = Touche.getKeyCode();
		
		switch (key){
			// Player 1
			case KeyEvent.VK_RIGHT:
				jeu.bougerJoueur(1, 0, Joueur2);
				break;
			case KeyEvent.VK_LEFT:
				jeu.bougerJoueur(-1, 0, Joueur2);
				break;
			case KeyEvent.VK_DOWN:
				jeu.bougerJoueur(0, 1, Joueur2);
				break;
			case KeyEvent.VK_UP:
				jeu.bougerJoueur(0, -1, Joueur2);
				break;
			case KeyEvent.VK_M:
				jeu.poserBombe("bombe", Joueur2);
				jeu.poserFlamme();
				break;
			// Player 2
			case KeyEvent.VK_D: 
				jeu.bougerJoueur(1, 0, Joueur1);
				break;
			case KeyEvent.VK_Q:
				jeu.bougerJoueur(-1, 0, Joueur1);
				break;
			case KeyEvent.VK_S:
				jeu.bougerJoueur(0, 1, Joueur1);
				break;
			case KeyEvent.VK_Z:
				jeu.bougerJoueur(0, -1, Joueur1);
				break;
			case KeyEvent.VK_F:
				jeu.poserBombe("bombe", Joueur1);
				break;
		}
	}

	public void keyTyped(KeyEvent Touche) {
	}

	public void keyReleased(KeyEvent Touche) {
	}
}
