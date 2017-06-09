package View;
import Model.Objets;

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
	private BufferedImage bombermanJeu;
	
	private BufferedImage flammeBleu;
	private BufferedImage flammeJaune;
	private BufferedImage flammeRouge;
	private BufferedImage flammeVerte;
	
	private BufferedImage bombeRouge;
	
	private BufferedImage gameOver;
	
	private BufferedImage troll;
	
	public int vieHaut;
	public int vieBas;
	
	public int porteeHaut;
	public int porteeBas;
	
	public int dureeBombeHaut;
	public int dureeBombeBas;
	
	public int NbBombeHaut;
	public int NbBombeBas;
	
	public boolean lancer;
	public boolean isEnd;
	public boolean lostJoueur1;
	public boolean lostJoueur2;
	
	public Plateau(){
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void paintNbBombeHaut(Graphics g, int NbBombeHaut){
		String v = String.valueOf(NbBombeHaut);
		g.drawString(v, 26*40, 5*40);
	}
	
	public void paintNbBombeBas(Graphics g, int NbBombeBas){
		String v = String.valueOf(NbBombeBas);
		g.drawString(v, 26*40, 12*40);
	}
	
	public void paintVieHaut(Graphics g, int vieHaut){
		String v = String.valueOf(vieHaut);
		g.drawString(v,26*40,2*40);
	}
	
	public void paintVieBas(Graphics g, int vieBas){
		String v = String.valueOf(vieBas);
		g.drawString(v,26*40,9*40);
	}
	
	public void paintdureeBombeHaut(Graphics g, int dureeBombeHaut){
		String v = String.valueOf(dureeBombeHaut);
		g.drawString(v, 26*40, 4*40);
	}
	
	public void paintdureeBombeBas(Graphics g, int dureeBombeBas){
		String v = String.valueOf(dureeBombeBas);
		g.drawString(v, 26*40, 11*40);
	}
	
	public void paintPorteeBas(Graphics g, int porteeBas){
		String v = String.valueOf(porteeBas);
		g.drawString(v, 26*40, 10*40);
	}
	
	public void paintPorteeHaut(Graphics g, int porteeHaut){
		String v = String.valueOf(porteeHaut);
		g.drawString(v, 26*40, 3*40);
	}
	
	public void paintVainqueur(Graphics g){
		try{
			gameOver = ImageIO.read(getClass().getResourceAsStream("/Images/gameOver.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		g.drawImage(gameOver, 3*40, 5*40, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 40));
		if(this.lostJoueur1 == true){
			g.drawString("JoueurBas a perdu ...", 13*40, 10*40);
		}else if(this.lostJoueur2 == true){
			g.drawString("JoueurHaut a perdu ...", 13*40, 10*40);
		}
	}
	
	public void paintMenu(Graphics g){
		try{
			troll = ImageIO.read(getClass().getResourceAsStream("/Images/menu.png"));
		} catch(IOException e){
			e.printStackTrace();
		}
		g.drawImage(troll, 8*40, 0, 21*40, 17*40, null);
	}
	
	public void paint(Graphics g) {
		if(this.lancer == false){
			for(int i = 0; i <40;i++){
				for(int j = 0; j<17; j++){
					int x = i;
					int y = j;
					// Background color
					g.setColor(Color.BLACK);
					g.fillRect(x*40, y*40, 40, 40);
				}
			}this.paintMenu(g);
			g.setColor(Color.GREEN);
			g.setFont(new Font("default", Font.BOLD, 40));
			g.drawString("Appuyer sur Entr�e pour jouer", 12*40, 15*40);
			
		}else if(this.isEnd == true){
			for(int i = 0; i <40;i++){
				for(int j = 0; j<17; j++){
					int x = i;
					int y = j;
					// Background color
					g.setColor(Color.BLACK);
					g.fillRect(x*40, y*40, 40, 40);
				}
			}this.paintVainqueur(g);
		}
		else{
			// Right side
			for(int i = 21; i<40; i++){
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
			g.drawString("JoueurBas", 22*40, 8*40);
			
			// Haut
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString("Vie : ", 24*40, 2*40);
			g.drawString("   Port�e : ", 23*40, 3*40);
			g.drawString("DureeBombe : ", 22*40, 4*40);
			g.drawString("   Nb Bombes : ", 22*40, 5*40);
			//Bas
			g.drawString("Vie : ", 24*40, 9*40);
			g.drawString("   Port�e : ", 23*40, 10*40);
			g.drawString("DureeBombe : ", 22*40, 11*40);
			g.drawString("   Nb Bombes : ", 22*40, 12*40);
	
			this.paintVieHaut(g, this.vieHaut);
			this.paintVieBas(g, this.vieBas);
			this.paintdureeBombeBas(g, this.dureeBombeBas);
			this.paintdureeBombeHaut(g, this.dureeBombeHaut);
			this.paintPorteeBas(g, this.porteeBas);
			this.paintPorteeHaut(g, this.porteeHaut);
			this.paintNbBombeBas(g, NbBombeBas);
			this.paintNbBombeHaut(g, NbBombeHaut);
			
			// BomberMan Logo en bas � droite
			try{
				bombermanJeu = ImageIO.read(getClass().getResourceAsStream("/Images/bombermanJeu.png"));
			} catch(IOException e){
				e.printStackTrace();
			}
			g.drawImage(bombermanJeu, 33*40, 13*40, 150, 150, null);
			
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
			
			try{
				flammeRouge = ImageIO.read(getClass().getResourceAsStream("/Images/flammeRouge.png"));
				flammeVerte = ImageIO.read(getClass().getResourceAsStream("/Images/flammeVerte.png"));
				flammeBleu = ImageIO.read(getClass().getResourceAsStream("/Images/flammeBleu.png"));
				flammeJaune = ImageIO.read(getClass().getResourceAsStream("/Images/flammeJaune.png"));
			} catch(IOException e){
				e.printStackTrace();
			}
			
			try{
				bombeRouge = ImageIO.read(getClass().getResourceAsStream("/Images/bombeRouge.png"));
			} catch(IOException e){
				e.printStackTrace();
			}
			
			// paint object
			for(Objets objet : this.objet){
				
				int x = (int)objet.getPosX();
				int y = (int)objet.getPosY();
				int color = objet.getColor();
				
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
					g.drawImage(flammeRouge, x*40, y*40, 40, 40, null);
				}else if(color == 8){
					g.drawImage(Bombe, x*40, y*40, 40, 40, null);
				}else if(color == 9){
					g.drawImage(flammeBleu, x*40, y*40, 40, 40, null);
				}else if(color == 10){
					g.drawImage(flammeVerte, x*40, y*40, 40, 40, null);
				}else if(color == 11){
					g.drawImage(flammeJaune, x*40, y*40, 40, 40, null);
				}else if(color == 12){
					g.drawImage(bombeRouge, x*40, y*40, 40, 40, null);	
				}
			}
		}
	}
	
	public void setObjets(ArrayList<Objets> objet){
		this.objet = objet;
	}
	
	public void redraw(){
		this.repaint();
	}
}

