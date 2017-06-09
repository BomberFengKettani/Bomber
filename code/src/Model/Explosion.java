package Model;

import java.util.ArrayList;

public class Explosion extends Objets implements Runnable, Destructible {

	private int duree;
	
	private ArrayList<DestructibleObservateur> observateurs = new ArrayList<DestructibleObservateur>();
	
	public Explosion(float X, float Y, int duree) {
		super(X, Y, 3, "Explosion");
		this.duree = duree;
	}
	
	public void destructibleFixe(DestructibleObservateur po) {
		observateurs.add(po);
	}

	public void destructibleNotificationObservateur() {
		for (DestructibleObservateur o : observateurs) {
			o.detruit(this, null);
		}
	}

	public boolean isObstacle() {
		return false;
	}

	public void run() {
		while(true){
			try {
				Thread.sleep(this.duree);
				this.destructibleNotificationObservateur();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
