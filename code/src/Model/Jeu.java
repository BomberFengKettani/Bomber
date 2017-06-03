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
			
			for(Objets objet : objet){
				// XNeg
				if(objet.getPosY() == bombePosee.getPosY() &&
					objet.getPosX() >= bombePosee.getPosX()-bombePosee.getPortee() && objet.getPosX() < bombePosee.getPosX()){
					if(objet.type == "BlockFranchissable"){
						valeurXNeg[(int)bombePosee.getPosX()-(int)objet.getPosX()] = 1;
					}else if(objet.type == "BlockNonFranchissable"){
						valeurXNeg[(int)bombePosee.getPosX()-(int)objet.getPosX()] = 2;
					}else if(objet.type != "BlockFranchissable" && objet.type != "BlockNonFranchissable"){
						valeurXNeg[(int)bombePosee.getPosX()-(int)objet.getPosX()] = 0;
					}
				}
				
				// XPos
				if(objet.getPosY() == bombePosee.getPosY() &&
					objet.getPosX() >= bombePosee.getPosX() && objet.getPosX() <= bombePosee.getPosX()+bombePosee.getPortee()){
					if(objet.type == "BlockFranchissable"){
						valeurXPos[(int)objet.getPosX()-(int)bombePosee.getPosX()] = 1;
					}else if(objet.type == "BlockNonFranchissable"){
						valeurXPos[(int)objet.getPosX()-(int)bombePosee.getPosX()] = 2;
					}else if(objet.type != "BlockFranchissable" && objet.type != "BlockNonFranchissable"){
						valeurXPos[(int)objet.getPosX()-(int)bombePosee.getPosX()] = 0;
					}
				}
				
				// YNeg
				if(objet.getPosX() == bombePosee.getPosX() &&
					objet.getPosY() >= bombePosee.getPosY()-bombePosee.getPortee() && objet.getPosY() < bombePosee.getPosY()){
					if(objet.type == "BlockFranchissable"){
						valeurYNeg[(int)bombePosee.getPosY()-(int)objet.getPosY()] = 1;
					}else if(objet.type == "BlockNonFranchissable"){
						valeurYNeg[(int)bombePosee.getPosY()-(int)objet.getPosY()] = 2;
					}else if(objet.type != "BlockFranchissable" && objet.type != "BlockNonFranchissable"){
						valeurYNeg[(int)bombePosee.getPosY()-(int)objet.getPosY()] = 0;
					}
				}

				// YPos
				if(objet.getPosX() == bombePosee.getPosX() &&
					objet.getPosY() >= bombePosee.getPosY() && objet.getPosY() <= bombePosee.getPosY()+bombePosee.getPortee()){
					if(objet.type == "BlockFranchissable"){
						valeurYPos[(int)objet.getPosY()-(int)bombePosee.getPosY()] = 1;
					}else if(objet.type == "BlockNonFranchissable"){
						valeurYPos[(int)objet.getPosY()-(int)bombePosee.getPosY()] = 2;
					}else if(objet.type != "BlockFranchissable" && objet.type != "BlockNonFranchissable"){
						valeurYPos[(int)objet.getPosY()-(int)bombePosee.getPosY()] = 0;
					}
				}
			}

			for(int i = 0; i<valeurYNeg.length; i++){
				System.out.print(valeurYNeg[i]);
			}
			
			// ADD OBJET TO BE EXPLODE
			for(Objets objet : objet){
				
				// XPos
				int indiceXPos = 0;
				while(indiceXPos < valeurXPos.length && valeurXPos[indiceXPos]==0){
					indiceXPos +=1;
				}
				if(indiceXPos >= 4){ // Eviter le d�bordement
					indiceXPos = bombePosee.getPortee();
				}
				if(valeurXPos[indiceXPos] == 2){ // Eviter d'afficher l'effet explosion sur les BlockNonFranchissables
					indiceXPos -= 1;
				}
				// Fixe la portee 
				bombePosee.setporteeXPos(indiceXPos);
				// test d'instanciation
				if(indiceXPos < valeurXPos.length && valeurXPos[indiceXPos]==1 &&
					objet instanceof Explosable &&
					objet.getPosX()>=bombePosee.getPosX() && objet.getPosX()<=bombePosee.getPosX()+indiceXPos &&
					objet.getPosY()==bombePosee.getPosY()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(indiceXPos < valeurXPos.length && valeurXPos[indiceXPos]==1 &&
					objet instanceof ExplosableObservateur &&
					objet.getPosX()>=bombePosee.getPosX() && objet.getPosX()<=bombePosee.getPosX()+indiceXPos &&
					objet.getPosY()==bombePosee.getPosY()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// XNeg
				int indiceXNeg = 0;
				while(indiceXNeg < valeurXNeg.length && valeurXNeg[indiceXNeg]==0){
					indiceXNeg +=1;
				}
				if(indiceXNeg >= 4){
					indiceXNeg = bombePosee.getPortee();
				}
				if(valeurXNeg[indiceXNeg] == 2){
					indiceXNeg -= 1;
				}
				// Fixe la portee 
				bombePosee.setporteeXNeg(indiceXNeg);
				// test d'instanciation
				if(indiceXNeg < valeurXNeg.length && valeurXNeg[indiceXNeg]==1 &&
					objet instanceof Explosable &&
					objet.getPosX()>=bombePosee.getPosX()-indiceXNeg && objet.getPosX()<=bombePosee.getPosX() && 
					objet.getPosY()==bombePosee.getPosY()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(indiceXNeg < valeurXNeg.length && valeurXNeg[indiceXNeg]==1 &&
					objet instanceof ExplosableObservateur &&
					objet.getPosX()>=bombePosee.getPosX()-indiceXNeg && objet.getPosX()<=bombePosee.getPosX() && 
					objet.getPosY()==bombePosee.getPosY()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// YPos
				int indiceYPos = 0;
				while(indiceYPos < valeurYPos.length && valeurYPos[indiceYPos]==0){
					indiceYPos +=1;
				}
				if(indiceYPos >= 4){
					indiceYPos = bombePosee.getPortee();
				}
				if(valeurYPos[indiceYPos]==2){
					indiceYPos -= 1;
				}
				// Fixe la portee 
				bombePosee.setporteeYPos(indiceYPos);
				// test d'instanciation
				if(indiceYPos < valeurYPos.length && valeurYPos[indiceYPos]==1 && 
					objet instanceof Explosable &&
					objet.getPosY()>= bombePosee.getPosY() && objet.getPosY()<=bombePosee.getPosY()+indiceYPos &&
					objet.getPosX()==bombePosee.getPosX()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(indiceYPos < valeurYPos.length && valeurYPos[indiceYPos]==1 &&
					objet instanceof ExplosableObservateur &&
					objet.getPosY()>= bombePosee.getPosY() && objet.getPosY()<=bombePosee.getPosY()+indiceYPos &&
					objet.getPosX()==bombePosee.getPosX()){
					bombePosee.explosableFixe(((ExplosableObservateur) objet));
				}
				
				// YNeg
				int indiceYNeg = 0;
				while(indiceYNeg < valeurYNeg.length && valeurYNeg[indiceYNeg]==0){
					indiceYNeg +=1;
				}
				if(indiceYNeg >= 4){
					indiceYNeg = bombePosee.getPortee();
				}
				if(valeurYNeg[indiceYNeg]==2){
					indiceYNeg -= 1;
				}
				// Fixe la portee 
				bombePosee.setporteeYNeg(indiceYNeg);
				// test d'instanciation
				if(indiceYNeg < valeurYNeg.length && valeurYNeg[indiceYNeg]==1 &&
					objet instanceof Explosable &&
					objet.getPosY()>=bombePosee.getPosY()-indiceYNeg && objet.getPosY()<=bombePosee.getPosY() && 
					objet.getPosX()==bombePosee.getPosX()){
					// add object to be explode
					((BombeObjet) objet).explosableFixe(((ExplosableObservateur) bombePosee));
				}
				if(indiceYNeg < valeurYNeg.length && valeurYNeg[indiceYNeg]==1 &&
					objet instanceof ExplosableObservateur &&
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