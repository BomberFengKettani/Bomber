package View;

import Model.Objets;
import Model.Son;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;

import javax.swing.JPanel;

public class Plateau extends JPanel {
		
	private ArrayList<Objets> objet;
	private BufferedImage terrain;
	private BufferedImage BlockNonFranchissable;
	private BufferedImage BlockFranchissable;
	private BufferedImage joueur1;
	private BufferedImage joueur2;
	private BufferedImage Bombe;
	private BufferedImage explosion;
	private BufferedImage Bear;
	
	public Plateau(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paint(Graphics g) {
		// Right side
		for(int i = 21; i<30; i++){
			for(int j = 0; j<17; j++){
				int x = i;
				int y = j;
				// Background color
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(x*40, y*40, 40, 40);
			}
		}
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("JoueurHaut", 22*40, 1*40);
		g.drawString("JoueurBas", 22*40, 7*40);
		
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString("Vie : ", 22*40, 2*40);
		g.drawString("Port�e : ", 22*40, 3*40);
		g.drawString("DureeBombe : ", 22*40, 4*40);
		
		g.drawString("Vie : ", 22*40, 8*40);
		g.drawString("Port�e : ", 22*40, 9*40);
		g.drawString("DureeBombe : ", 22*40, 10*40);

		try{
			Bear = ImageIO.read(getClass().getResourceAsStream("/Images/bombermanJeu.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
		g.drawImage(Bear, 23*40, 13*40, 150, 150, null);
		
		try{
			terrain = ImageIO.read(getClass().getResourceAsStream("/Images/terrain.jpg"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
		// paint the background
		for(int i = 0; i<21; i++){
			for(int j = 0; j<17; j++){
				int x = i;
				int y = j;
				// Background color
				g.setColor(Color.GREEN);
				g.drawImage(terrain, x*40, y*40, 40, 40, null);
			}
		}
		
		try{
			BlockNonFranchissable = ImageIO.read(getClass().getResourceAsStream("/Images/briqueNonFranchissable.png"));
			BlockFranchissable = ImageIO.read(getClass().getResourceAsStream("/Images/briqueFranchissable.jpeg"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try{
			joueur1 = ImageIO.read(getClass().getResourceAsStream("/Images/joueur.png"));
			joueur2 = ImageIO.read(getClass().getResourceAsStream("/Images/joueur1.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		try{
			Bombe = ImageIO.read(getClass().getResourceAsStream("/Images/bombe.png"));
			explosion = ImageIO.read(getClass().getResourceAsStream("/Images/explosion.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// paint object
		for(Objets object : this.objet){
			
			int x = (int)object.getPosX();
			int y = (int)object.getPosY();
			int color = object.getColor();
			
			if(color == 0){
				g.drawImage(BlockNonFranchissable, x*40, y*40, 40, 40, null);
				// object case contour color
				g.setColor(Color.BLACK);
				g.drawRect(x*40, y*40, 40, 40);
			}else if(color == 1){
				g.setColor(Color.GRAY);
				g.fillRect(x*40, y*40, 40, 40);
			}else if(color == 2){
				g.drawImage(joueur1, x*40, y*40, 40, 40, null);
			}else if(color == 3){
				g.setColor(Color.GREEN);
				g.drawImage(explosion, x*40, y*40, 40, 40, null);
			}else if(color == 4){
				g.setColor(Color.RED);
				g.fillRect(x*40, y*40, 40, 40);
			}else if(color == 5){
				g.drawImage(BlockFranchissable, x*40, y*40, 40, 40, null);
				// object case contour color
				g.setColor(Color.BLACK);
				g.drawRect(x*40, y*40, 40, 40);
			}else if(color == 6){
				g.drawImage(joueur2, x*40, y*40, 40, 40, null);
			}else if(color == 7){
				g.setColor(Color.WHITE);
				g.fillRect(x*40, y*40, 40, 40);
			}else if(color == 8){
				g.setColor(Color.MAGENTA);
				g.drawImage(Bombe, x*40, y*40, 40, 40, null);
			}
 
		}
	}
	
	public void setObjects(ArrayList<Objets> objet){
		this.objet = objet;
	}
	
	public void redraw(){
		this.repaint();
	}
}

