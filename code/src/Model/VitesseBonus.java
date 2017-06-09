//Kenza KETTANI
// Voici comme convenu le code qui a posé problème.

//package Model;
//
//import java.awt.event.*;
//import javax.swing.*;
//import javax.swing.Timer;
//import java.awt.*;
//import javax.imageio.*;
//import java.io.*;
//import java.awt.image.*;
//import javax.swing.JPanel;
//import javax.swing.JFrame;
//
//// J'avais aussi créé le package javax.swing et j'avais instancié le Jframe, mais je me suis 
//// rendue compte que ce n'était pas la peine.

//// Speed up et Speed down
//  public class VitesseBonus extends JPanel implements ActionListener{
//	private Timer timer;
//	private BufferedImage image;
//	private int x=0;    // L'abscisse initiale
//	private int y=0;    // L'ordonnée initiale
//	private int dx,dy;  // Déplacement infintésimal pour régler la vitesse
//	    
//	    
//	    
//public void actionPerformed(ActionEvent E){
//	deplacement();
//	repaint();  
//	    }
//// J'ai considéré ensuite le code suivant comme une initialisation des vitesses pour les 2 joueurs:	 
//	    private class TAdapter extends KeyAdapter 
//	    {
//	 
////Joueur 1	       
//	        public void keyReleased(KeyEvent E) {
//	            int key = E.getKeyCode();
//	 
//	            if (key == KeyEvent.VK_LEFT) {
//	            	    dx = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_RIGHT) {
//	            	    dx = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_UP) {
//	            	    dy = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_DOWN) {
//	            	    dy = 0;
//	            }
//	        }
//	        
////Joueur 2
//	        public void keyReleased(KeyEvent E) {
//	            int key = E.getKeyCode();
//	 
//	            if (key == KeyEvent.VK_D) {
//	            	    dx = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_Q) {
//	            	    dx = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_S) {
//	            	    dy = 0;
//	            }
//	 
//	            if (key == KeyEvent.VK_Z) {
//	            	    dy = 0;
//	            }
//	        } 
//	        
//	        
//// Speed up
//// Joueur 1
//	        public void keyPressed(KeyEvent E) {
//	            int key = E.getKeyCode();
//	 
//	            if (key == KeyEvent.VK_LEFT) {
//	            	    dx = 1;
//	            }
//	 
//	            if (key == KeyEvent.VK_RIGHT) {
//	            	    dx = 1;
//	            }
//	 
//	            if (key == KeyEvent.VK_UP) {
//	            	    dy = 1;
//	            }
//	 
//	            if (key == KeyEvent.VK_DOWN) {
//	            	    dy = 1;
//	            }
//// Joueur 2	            
//	            if (key == KeyEvent.VK_D) {
//        	            dx = 1;
//                }
//
//                if (key == KeyEvent.VK_Q) {
//        	            dx = 1;
//                }
//
//                if (key == KeyEvent.VK_S) {
//        	            dy = 1;
//                }
//
//                if (key == KeyEvent.VK_Z) {
//        	            dy = 1;
//                }
//	        }
//	    
//
//// Le code a posé problème dans le run et je pense que c'est à cause la classe Clavier.    
////Speed down
//	    
//public void keyPressed(KeyEvent E) {
//	int key = E.getKeyCode();
////Joueur 1
//	if (key == KeyEvent.VK_LEFT) {
//		dx = -1;
//	 }
//
//	if (key == KeyEvent.VK_RIGHT) {
//		dx = -1;
//	        }
//
//	        if (key == KeyEvent.VK_UP) {
//	        	    dy = -1;
//	        }
//
//	        if (key == KeyEvent.VK_DOWN) {
//	        	    dy = -1;
//	        }
////Joueur 2
//	        if (key == KeyEvent.VK_D) {
//	    		dx = -1;
//	    	 }
//
//	    	if (key == KeyEvent.VK_Q) {
//	    		dx = -1;
//	    	        }
//
//	    	        if (key == KeyEvent.VK_S) {
//	    	        	    dy = -1;
//	    	        }
//
//	    	        if (key == KeyEvent.VK_Z) {
//	    	        	    dy = -1;
//	    	        }
//}
//	    
//	    
//
//	    
//	 public void deplacement() {   // Fonction déplacement: position + vitesse (positive 
                                    //ou négative selon le bonus)
//	        x += dx;
//	        y += dy;
//	    }   
//}
//}
