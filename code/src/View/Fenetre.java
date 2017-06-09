package View;

import Model.Objets;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenetre {
	
	public Plateau plateau = new Plateau();
	
	public boolean lancer = false;
	
	public Fenetre(){

		// window title
	    JFrame window = new JFrame("BomberMan");
	    // Exit and stop the program
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    // window dimension
	    window.setBounds(230, 40, 1500, 728); // (posX, posY, largeurX, largeurY)
	    
	    window.getContentPane().setBackground(Color.gray);
	    window.getContentPane().add(this.plateau);
	    window.setVisible(true);
	}	
	
	public void setJeuObjets(ArrayList<Objets> objets){
		this.plateau.setObjets(objets);
		this.plateau.redraw();
	}
	
	public void update(){
		this.plateau.redraw();
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.plateau.addKeyListener(keyboard);
	}
	
	public void debutPartie(){ // Fonction qui permet de lancer la partie en fixant lancer à true
		this.lancer = true;
		this.plateau.lancer = true;
	}
}
