package Model;

import java.util.ArrayList;

public class Bonus extends Objets implements Runnable, Destructible{
	
	public boolean detonated;
	
	protected ArrayList<DestructibleObservateur> destructibleObservateurs = new ArrayList<DestructibleObservateur>();
	
	public Bonus(int x, int y, int couleur, String type){
		super(x,y,couleur, type);
	}
	
	public void run(){
		while(!this.detonated){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.destructibleNotificationObservateur();
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
	
	public boolean getBonus(Joueur joueur){
		if(joueur.getPosX() == this.getPosX() && joueur.getPosY() == this.getPosY()){
			return true;
		}
		return false;
	}
	
	public boolean isObstacle(){
		return false;
	}
}
