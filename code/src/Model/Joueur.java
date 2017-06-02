package Model;

import java.util.ArrayList;

public class Joueur extends Objets implements DestructibleObservateur, ExplosableObservateur, Destructible{
	
	int countBomb = 0;
	int maxBomb = 0;
	int bombPortee = 3;
	int vie = 0;
	int numJoueur;
	
	private ArrayList<DestructibleObservateur> observateurs = new ArrayList<DestructibleObservateur>();
	
	public Joueur(float x, float y, int couleur, int maxBomb, int vie, int numJoueur){
		super(x,y,couleur,"Joueur");
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
				bombe = new Nuke(this.posX, this.posY, 5000, this.bombPortee); // 5 000 ms
			}else if(type.equals("bomb")){
				bombe = new Bombe(this.posX, this.posY, 5000, this.bombPortee); // 5 000 ms
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
		boolean distanceX = Math.abs(this.getPosX() - bombe.getPosX()) <= bombe.getPortee();
		boolean distanceY = Math.abs(this.getPosY() - bombe.getPosY()) <= bombe.getPortee();
		
		// Lost life when we get explode
		if(distanceX && distanceY){
			this.vie = this.vie - 1;
			if(this.vie == 0){
				destructibleNotificationObservateur();
			}
		}
	}

	public int getVie(int numJoueur){
		return this.vie;
	}
	
	public boolean isObstacle() {
		return false;
	}
}
