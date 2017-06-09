package Model;

import java.util.ArrayList;

public abstract class Bonus extends Objets implements Runnable, Explosable, ExplosableObservateur, Destructible{
		
	protected ArrayList<DestructibleObservateur> destructibleObservateurs = new ArrayList<DestructibleObservateur>();
	private ArrayList<ExplosableObservateur> explosableObservateurs = new ArrayList<ExplosableObservateur>();
	
	public Bonus(int x, int y, int couleur, String type){
		super(x,y,couleur, type);
	}
	
	public void run(){
//		int compteur = 0;
//		while(compteur < dureeJoueur){
//			try {
//				Thread.sleep(10);
//				compteur += 1;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//		this.destructibleNotificationObservateur();
//		this.explosableNotificationObservateur();	
	}
	
	public void destructibleFixe(DestructibleObservateur po) {
		destructibleObservateurs.add(po);		
	}
	
	public void destructibleNotificationObservateur(){
		for (DestructibleObservateur o : destructibleObservateurs) {
			o.detruit(this, null);
		}
	}
	
	public void explosableFixe(ExplosableObservateur eo) {
		explosableObservateurs.add(eo);
	}

	public void explosableNotificationObservateur() {
		for (ExplosableObservateur o : explosableObservateurs) {
			o.explode(this);
		}
	}
	
	public boolean getBonus(Joueur joueur){ // Fonction qui permet de tester si un joueur a pris le bonus
		if(joueur.getPosX() == this.getPosX() && joueur.getPosY() == this.getPosY()){
			return true;
		}
		return false;
	}
	
	public abstract void explode(Explosable e);	
	
	public boolean isObstacle(){
		return false;
	}
}
