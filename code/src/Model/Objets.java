package Model;

public abstract class Objets {
	
	public float posX;
	public float posY;
	public int couleur;
	public String type;
	
	public Objets(float X, float Y, int couleur, String type){
		this.posX = X;
		this.posY = Y;
		this.couleur = couleur;
		this.type = type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return this.type;
	}
	
	public float getPosX(){
		return this.posX;
	}
	
	public void setPosX(float x){
		this.posX = x;
	}
	
	public float getPosY(){
		return this.posY;
	}
	
	public void setPosY(float y){
		this.posY = y;
	}
	
	public int getColor(){
		return this.couleur;
	}
	
	public boolean isAtPosition(float x, float y){
		return this.posX == x && this.posY == y;
	}
	
	public abstract boolean isObstacle();
}
