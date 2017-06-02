package Model;

import View.Fenetre;

import java.util.ArrayList;

public class Jeu implements DestructibleObservateur{
	
	private ArrayList<Objets> objet = new ArrayList<Objets>();
//	private boolean lancer = false;
	private Fenetre fenetre;
	
	/* Field size */
	private int x = 21;
	private int y = 17;
	
	public Jeu(Fenetre fenetre){
		this.fenetre = fenetre;
		
		Joueur Joueur1 = new Joueur(1,15,2,3,3,0); // (x,y,couleur,maxbomb,vie,numJoueur)
		Joueur Joueur2 = new Joueur(19,1,6,3,3,1);
		
		// Creating one Player at position (1,15), bas gauche
		objet.add(Joueur1);

		// Creating one player at position (19,1), haut droit
		objet.add(Joueur2);
		
		
		// Map building 
		for(int i = 0; i < x; i++){
			// bord haut
			objet.add(new BlockNonFranchissable(i,0));
			// bord bas
			objet.add(new BlockNonFranchissable(i, y-1));
		}
		
		for(int j = 1; j < y-1; j++){
			// bord gauche
			objet.add(new BlockNonFranchissable(0,j));	
			// bord droit
			objet.add(new BlockNonFranchissable(x-1, j));
		}
		
		// BlockNonFranchissable inside
		for(int i = 2; i < 21-2; i++){
			for(int j = 2; j < 17-2; j++){
				if(i%2 == 0 && j%2 == 0){
					objet.add(new BlockNonFranchissable(i,j));
				}
			}
		}
		
		// BlockFranchissable inside
		for(int i = 1; i < 21-1; i++){
			for(int j = 5; j < 13; j++){
				if(j%2 == 1){
					BlockFranchissable block = new BlockFranchissable(i,j);
					block.destructibleFixe(this);
					objet.add(block);
				}
			}
		}
		
		// BlockFranchissable inside haut 1
		for(int i = 1; i<17; i++){
			BlockFranchissable block = new BlockFranchissable(i,1);
			block.destructibleFixe(this);
			objet.add(block);
		}
		
		// BlockFranchissable inside haut 2
		for(int i = 1; i<18; i++){
			BlockFranchissable block = new BlockFranchissable(i,3);
			block.destructibleFixe(this);
			objet.add(block);
		}
		
		// BlockFranchissable inside bas 1
		for(int i = 4; i<21-1; i++){
			BlockFranchissable block = new BlockFranchissable(i,15);
			block.destructibleFixe(this);
			objet.add(block);
		}
				
		// BlockFranchissable inside bas 2
		for(int i = 3; i<21-1; i++){
			BlockFranchissable block = new BlockFranchissable(i,13);
			block.destructibleFixe(this);
			objet.add(block);
		}
		
		// BlockFranchissable inside interblock
		for(int i = 1; i < 21-1; i++){
			for(int j = 4; j < 17-4; j++){
				if(i%2 == 1 && j%2 == 0){
					BlockFranchissable block = new BlockFranchissable(i,j);
					block.destructibleFixe(this);
					objet.add(block);
				}
			}
		}
		
		// BlockFranchissable inside interblock haut
		for(int i = 1; i<21-5; i++){
			if(i%2 == 1){
				BlockFranchissable block = new BlockFranchissable(i,2);
				block.destructibleFixe(this);
				objet.add(block);
			}
		}
		
		// BlockFranchissable inside interblock bas
		for(int i = 5; i<21-1; i++){
			if(i%2 == 1){
				BlockFranchissable block = new BlockFranchissable(i,14);
				block.destructibleFixe(this);
				objet.add(block);
			}
		}
		
		fenetre.setJeuObjets(this.getJeuObjets());
		notificationVue();
	}
	
	
	public void poserBombe(String bombType, int numeroJoueur){
		
		Joueur joueur = ((Joueur) objet.get(numeroJoueur));
		BombeObjet bombePosee = joueur.poserBombe(bombType);
		
		if(bombePosee != null){
			bombePosee.destructibleFixe(this);
			
			// determine if BlockFranchissable
			int[] valeurXNeg = new int[bombePosee.getPortee()+1];
			int[] valeurXPos = new int[bombePosee.getPortee()+1];
			
			int[] valeurYNeg = new int[bombePosee.getPortee()+1];
			int[] valeurYPos = new int[bombePosee.getPortee()+1];
			
			int compteurXNeg = 0;
			int compteurXPos = 0;
			
			int compteurYNeg = 0;
			int compteurYPos = 0;
			for(Objets objet : objet){
				// XNeg
				if(objet.getPosY() == bombePosee.getPosY() &&
					objet.getPosX() >= bombePosee.getPosX()-bombePosee.getPortee() && objet.getPosX() <= bombePosee.getPosX() &&
					objet.type == "BlockFranchissable" ){
					valeurXNeg[compteurXNeg] = 1;
					compteurXNeg += 1;
				}
				// XPos
				if(objet.getPosY() == bombePosee.getPosY() &&
					objet.getPosX() >= bombePosee.getPosX() && objet.getPosX() <= bombePosee.getPosX()+bombePosee.getPortee() &&
					objet.type == "BlockFranchissable"){
					valeurXPos[compteurXPos] = 1;
					compteurXPos += 1;
				}
				// YNeg
				if(objet.getPosX() == bombePosee.getPosX() &&
					objet.getPosY() >= bombePosee.getPosY()-bombePosee.getPortee() && objet.getPosY() <= bombePosee.getPosY() &&
					objet.type == "BlockFranchissable"){
					valeurYNeg[compteurYNeg] = 1;
					compteurYNeg += 1;
				}
				// YPos
				if(objet.getPosX() == bombePosee.getPosX() &&
					objet.getPosY() >= bombePosee.getPosY() && objet.getPosY() <= bombePosee.getPosY()+bombePosee.getPortee() &&
					objet.type == "BlockFranchissable"){
					valeurYPos[compteurYPos] = 1;
					compteurYPos += 1;
				}
			}
			
			// ADD OBJET TO BE EXPLODE
			for(Objets objet : objet){
				
				// XPos
				int indiceXPos = 0;
				while(valeurXPos[indiceXPos]==1 && indiceXPos != valeurXPos.length-1){
					indiceXPos +=1;
				}
				indiceXPos = valeurXPos.length - indiceXPos;
				// test d'instanciation
				if(objet instanceof Explosable &&
					objet.getPosX()>=bombePosee.getPosX() && objet.getPosX()<=bombePosee.getPosX()+indiceXPos &&
					objet.getPosY()==bombePosee.getPosY()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(objet instanceof ExplosableObservateur &&
					objet.getPosX()>=bombePosee.getPosX() && objet.getPosX()<=bombePosee.getPosX()+indiceXPos &&
					objet.getPosY()==bombePosee.getPosY()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// XNeg
				int indiceXNeg = 0;
				while(valeurXNeg[indiceXNeg]==1){
					indiceXNeg +=1;
				}
				indiceXNeg = valeurXNeg.length - indiceXNeg;
				// test d'instanciation
				if(objet instanceof Explosable &&
					objet.getPosX()>=bombePosee.getPosX()-indiceXNeg && objet.getPosX()<=bombePosee.getPosX() && 
					objet.getPosY()==bombePosee.getPosY()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(objet instanceof ExplosableObservateur &&
					objet.getPosX()>=bombePosee.getPosX()-indiceXNeg && objet.getPosX()<=bombePosee.getPosX() && 
					objet.getPosY()==bombePosee.getPosY()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// YPos
				int indiceYPos = 0;
				while(valeurYPos[indiceYPos]==1 && indiceYPos != valeurYPos.length-1){
					indiceYPos +=1;
				}
				indiceYPos = valeurYPos.length - indiceYPos;
				// test d'instanciation
				if(objet instanceof Explosable &&
					objet.getPosY()>= bombePosee.getPosY() && objet.getPosY()<=bombePosee.getPosY()+indiceYPos &&
					objet.getPosX()==bombePosee.getPosX()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(objet instanceof ExplosableObservateur &&
					objet.getPosY()>= bombePosee.getPosY() && objet.getPosY()<=bombePosee.getPosY()+indiceYPos &&
					objet.getPosX()==bombePosee.getPosX()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// YNeg
				int indiceYNeg = 0;
				while(valeurYNeg[indiceYNeg]==1){
					indiceYNeg +=1;
				}
				indiceYNeg = valeurYNeg.length - indiceYNeg;
				// test d'instanciation
				if(objet instanceof Explosable &&
					objet.getPosY()>=bombePosee.getPosY()-indiceYNeg && objet.getPosY()<=bombePosee.getPosY() && 
					objet.getPosX()==bombePosee.getPosX()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(objet instanceof ExplosableObservateur &&
					objet.getPosY()>=bombePosee.getPosY()-indiceYNeg && objet.getPosY()<=bombePosee.getPosY() && 
					objet.getPosX()==bombePosee.getPosX()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
			}
			objet.add(bombePosee);
			notificationVue();
		}
	}
	
	
	public void bougerJoueur(int x, int y, int playerNumber){
		Joueur joueur = ((Joueur) objet.get(playerNumber));
		
		float nextX = joueur.getPosX()+x;
		float nextY = joueur.getPosY()+y;
		
		boolean obstacle = false;
		
		// Stop when we have an obstacle
		for(Objets object : objet){
			if(object.isAtPosition(nextX, nextY)){
				obstacle = object.isObstacle();
			}
			if(obstacle == true){
				break;
			}
		}
		// Go on
		if(obstacle == false){
			joueur.bouger(x,y);
			notificationVue();
		}
	}
	
	// Object destruction
	synchronized public void detruit(Destructible ps, ArrayList<Objets> butin) {
		objet.remove(ps);
		if(butin != null){
			objet.addAll(butin);
		}
		notificationVue();
	}
	
//	public void run(){
//		this.lancer = true;
//	}
	
	private void notificationVue(){
		fenetre.update();
	}

	public ArrayList<Objets> getJeuObjets(){
		return this.objet;
	}
}
