package Model;

import java.util.ArrayList;

public abstract class BombeObjet extends Objets implements Runnable, Destructible, Explosable, ExplosableObservateur {

	protected int duree = 0;
	private int portee = 0;
	boolean detonated = false;
	
	protected ArrayList<DestructibleObservateur> destructibleObservateurs = new ArrayList<DestructibleObservateur>();
	private ArrayList<ExplosableObservateur> explosableObservateurs = new ArrayList<ExplosableObservateur>();
	
	public BombeObjet(float x, float y, int duree, int portee, int couleur){
		super(x,y, couleur, "BombeObjet");
		this.duree = duree;
		this.portee = portee;
	}

	public int getPortee(){
		return this.portee;
	}
	
	public void setPortee(int portee){
		this.portee = portee;
	}
		
	public void run() {
		int compteur = 0;
		while(!this.detonated  && compteur < this.duree/10.0){
			try {
				Thread.sleep(10);
				compteur += 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.destructibleNotificationObservateur();
		this.explosableNotificationObservateur();
	}
	
	public void destructibleFixe(DestructibleObservateur po) {
		destructibleObservateurs.add(po);		
	}

	public void destructibleNotificationObservateur() {
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

	public abstract void explode(Explosable e);
	
	public boolean isObstacle() {
		return true;
	}
	
}
