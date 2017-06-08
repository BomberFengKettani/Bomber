package Model;

public class BonusFlamme extends Bonus{

	int bonusPortee;
	int duree = 5000;
	
	public BonusFlamme(int x, int y, int couleur, int bonusPortee){
		super(x,y,couleur,"BonusFlamme");
		this.bonusPortee = bonusPortee;
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
//		this.destructibleNotificationObservateur();
//		this.explosableNotificationObservateur();
//		Son.jouerSon("/Sons/Explosion.wav");
	}
	
	public void getBonusFlamme(Joueur joueur){
		joueur.setBombePortee(joueur.getBombePortee() + this.bonusPortee);
	}
	
	public boolean isObstacle(){
		return false;
	}
	
}