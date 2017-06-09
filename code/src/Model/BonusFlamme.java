package Model;

public class BonusFlamme extends Bonus{

	int bonusPortee;
	
	public BonusFlamme(int x, int y, int couleur, int bonusPortee){
		super(x,y,couleur,"BonusFlamme");
		this.bonusPortee = bonusPortee;
	}
	
	public void run() {
//		System.out.println(dureeJoueur);
//		int compteur = 0;
//		while(compteur < dureeJoueur){
//			try {
//				Thread.sleep(10);
//				compteur += 1;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
	}
	
	public void explode(Explosable e) {
	}
	
	public void getBonusFlamme(Joueur joueur){ // Permet d'appliquer les effets du bonus
		joueur.setBombePortee(joueur.getBombePortee() + this.bonusPortee);
	}
	
	public boolean isObstacle(){
		return false;
	}
	
}
