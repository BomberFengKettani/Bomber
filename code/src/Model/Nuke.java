package Model;

import java.util.ArrayList;

public class Nuke extends BombeObjet implements Destructible{

	public Nuke(float x, float y, int duree, int portee) {
		super(x, y, duree, portee, 5);
	}
		
	public void run() {
		int compteur = 0;
		while(!this.detonated  && compteur < super.duree/10.0){
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
	
	public void explode(Explosable e) {
		
		BombeObjet bombe = (BombeObjet) e;
		
		boolean distanceX = Math.abs(this.getPosX() - bombe.getPosX()) <= bombe.getPortee();
		boolean distanceY = Math.abs(this.getPosY() - bombe.getPosY()) <= bombe.getPortee();
		
		if(distanceX && distanceY){
			this.detonated = true;
			this.destructibleNotificationObservateur();		
		}
	}
	
	public void destructibleNotificationObservateur() {
		ArrayList<Objets> butin = new ArrayList<Objets>();
		int portee = this.getPortee();
		float x = this.getPosX();
		float y = this.getPosY();
		for(float i = x-portee; i <= x+portee; i++){
			for(float j = y-portee; j <= y+portee; j++){
				Explosion explosion = new Explosion(i,j,500); // 500 ms
				Thread thread = new Thread(explosion);
				thread.start();
				for(DestructibleObservateur observer : destructibleObservateurs){
					explosion.destructibleFixe(observer);
				}
				butin.add(explosion);
			}
		}
		
		for (DestructibleObservateur o : this.destructibleObservateurs) {
			o.detruit(this, butin);
		}	
	}
}