package Model;

import Model.Jeu;
import java.util.ArrayList;

public class Joueur extends Objets implements DestructibleObservateur, ExplosableObservateur, Destructible{
	
	int countBomb = 0;
	int maxBomb = 0;
	int bombePortee = 3;
	int vie;
	int numJoueur;
	
	private ArrayList<DestructibleObservateur> observateurs = new ArrayList<DestructibleObservateur>();
	
	public Joueur(float x, float y, int couleur, String joueur, int maxBomb, int vie, int numJoueur){
		super(x,y,couleur,joueur);
		this.maxBomb = maxBomb;
		this.countBomb = maxBomb;
		this.vie = vie;
		this.numJoueur = numJoueur;
	}
	
	public void bouger(float X, float Y){
		super.posX = this.posX + X;
		this.posY = this.posY + Y;
	}

	public BombeObjet poserBombe(String type){
		if(this.maxBomb > 0){
			this.maxBomb = this.maxBomb - 1;
			BombeObjet bombe = null;
			
			if(type.equals("nuke")){
				bombe = new Nuke(this.posX, this.posY, 5000, this.bombePortee); // 5 000 ms
			}else if(type.equals("bomb")){
				int duree = 5000;
				bombe = new Bombe(this.posX, this.posY, duree, this.bombePortee); // 5 000 ms
			}
			
			bombe.destructibleFixe(this);
			Thread thread = new Thread(bombe);
			thread.start();
			return bombe;
		}
		return null;
	}
	
	public void destructibleFixe(DestructibleObservateur po) {
		observateurs.add(po);		
	}

	public void destructibleNotificationObservateur() {
		for (DestructibleObservateur o : observateurs) {
			o.detruit(this, null);
		}	
	}

	public void detruit(Destructible ps, ArrayList<Objets> butin) {
		if(this.maxBomb < this.countBomb){
			this.maxBomb += 1;	
		}
	}

	public void explode(Explosable e) {
		
		BombeObjet bombe = (BombeObjet) e;
		
		// test if we are in range to be explode
		boolean distanceXPos = this.getPosX() - bombe.getPosX() <= bombe.getporteeXPos();
		boolean distanceXNeg = bombe.getPosX() - this.getPosX() <= bombe.getporteeXNeg();
		boolean distanceYPos = this.getPosY() - bombe.getPosY() <= bombe.getporteeYPos();
		boolean distanceYNeg = bombe.getPosY() - this.getPosY() <= bombe.getporteeYNeg();
		
		// Lost life when we get explode
		if((distanceXPos && this.getPosY()==bombe.getPosY()) ||
			(distanceYPos && this.getPosX()==bombe.getPosX())||
			(distanceXNeg && this.getPosY()==bombe.getPosY())||
			(distanceYNeg && this.getPosX()==bombe.getPosX())){
			this.vie -= 1;
//			if(this.vie == 0){
//				destructibleNotificationObservateur();
//			}
		}
	}

	public int getVie(){
		return this.vie;
	}
	
	public int getnumJoueur(){
		return this.numJoueur;
	}
	
	public void setBombePortee(int bombePortee){
		this.bombePortee = bombePortee;
	}
	
	public int getBombePortee(){
		return this.bombePortee;
	}
	
	public boolean isObstacle() {
		return false;
	}
}
