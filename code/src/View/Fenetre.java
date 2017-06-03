package View;

import Model.Joueur;
import Model.Objets;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenetre {
	
	private Plateau plateau = new Plateau();
	
	public int FenVieHaut;
	
	public Fenetre(Joueur Joueur1, Joueur Joueur2){
		this.FenVieHaut = Joueur1.getVie();
		// window title
	    JFrame window = new JFrame("BomberMan");
	    // Exit and stop the program
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // window dimension
	    window.setBounds(230, 40, 1200, 728); // (posX, posY, largeurX, largeurY)
	    
	    window.getContentPane().setBackground(Color.gray);
	    window.getContentPane().add(this.plateau);
	    window.setVisible(true);
	}	
	
	public void setJeuObjets(ArrayList<Objets> objects){
		this.plateau.setObjects(objects);
		this.plateau.vieHaut = this.FenVieHaut;
		this.plateau.redraw();
	}
	
	public void setvieHaut(int vieHaut){
		this.FenVieHaut = vieHaut;
	}
	
	public void update(){
		this.plateau.redraw();
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.plateau.addKeyListener(keyboard);
	}
}
