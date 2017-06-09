package Model;

import java.util.ArrayList;

public abstract class BombeObjet extends Objets implements Runnable, Destructible, Explosable, ExplosableObservateur {

	public int duree = 0;
	
	public int portee = 0;
	private int porteeXPos;
	private int porteeYPos;
	private int porteeXNeg;
	private int porteeYNeg;
	
	boolean detonated = false;
	
	// Liste des �l�ments � d�truire
	protected ArrayList<DestructibleObservateur> destructibleObservateurs = new ArrayList<DestructibleObservateur>();
	// Liste des �l�ments � exploser
	private ArrayList<ExplosableObservateur> explosableObservateurs = new ArrayList<ExplosableObservateur>();
	
	public BombeObjet(float x, float y, int duree, int portee, int couleur){
		super(x,y, couleur, "BombeObjet");
		this.duree = duree;
		this.portee = portee;
		this.porteeXNeg = 0;
		this.porteeXPos = 0;
		this.porteeYNeg = 0;
		this.porteeYPos = 0;
	}

	public int getPortee(){
		return this.portee;
	}
	
	public int getporteeXPos(){
		return this.porteeXPos;
	}
	
	public int getporteeXNeg(){
		return this.porteeXNeg;
	}
	
	public int getporteeYPos(){
		return this.porteeYPos;
	}
	
	public int getporteeYNeg(){
		return this.porteeYNeg;
	}
	
	public void setPortee(int portee){
		this.portee = portee;
	}
	
	public void setporteeXPos(int porteeXPos){
		this.porteeXPos = porteeXPos;
	}
	
	public void setporteeXNeg(int porteeXNeg){
		this.porteeXNeg = porteeXNeg;
	}
	
	public void setporteeYPos(int porteeYPos){
		this.porteeYPos = porteeYPos;
	}
	
	public void setporteeYNeg(int porteeYNeg){
		this.porteeYNeg = porteeYNeg;
	}
		
	public void run() {
		int compteur = 0;
		while(compteur < this.duree/10.0){
			try {
				Thread.sleep(10);
				compteur += 1;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.destructibleNotificationObservateur();
		this.explosableNotificationObservateur();
		Son.jouerSon("/Sons/Explosion.wav");
	}
	
	// Ajouter les �l�ments � d�truire
	public void destructibleFixe(DestructibleObservateur po) {
		destructibleObservateurs.add(po);		
	}

	// Destruction des objets
	public void destructibleNotificationObservateur() {
		for (DestructibleObservateur o : destructibleObservateurs) {
			o.detruit(this, null);
		}	
	}

	// Ajouter les �l�ments � exploser
	public void explosableFixe(ExplosableObservateur eo) {
		explosableObservateurs.add(eo);
	}

	// Exploser les �l�ments
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
