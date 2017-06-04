package View;

import Model.Objets;

import java.awt.Color;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Fenetre {
	
	public Plateau plateau = new Plateau();
	
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
	
	public void setJeuObjets(ArrayList<Objets> objects){
		this.plateau.setObjects(objects);
		this.plateau.redraw();
	}
	
	public void update(){
		this.plateau.redraw();
	}
	
	public void setKeyListener(KeyListener keyboard){
	    this.plateau.addKeyListener(keyboard);
	}
}
