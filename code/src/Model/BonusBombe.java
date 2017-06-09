//Kenza KETTANI
//Voici, comme convenu, les parties du code qui ont posé problème
// J'ai organisé le code correspondant aux bonus en deux classes : BonusBombe pour les bombes 
// et VitesseBonus pour speed up et speed down.
// Ce code n'étant pas encore finalisé, on ne l'a pas encore implanté. 

//package Model;
   
//Ce qu'on peut faire pour les 9 bonus possibles:
//Dans la classe joueur, rajouter une liste qui correspond aux bonus 
 
//private int [] BonusBombe={1,2,3,4,5,6,7,8,9} avec:
 // 1--> Flamme bleue
 // 2--> Flamme jaune
 // 3--> Flamme rouge
 // 4--> Bombe rouge
 // 5--> Vie
 // 6--> Speed up
 // 7--> Speed down
 // 8--> Bombe plus
 // 9--> Bombe moins
 
// pour coder le random:
// private int choix Math.random(9) car 9 bonus possibles en tout

 //public class BonusBombe() {
//	  private boolean envoye;    //envoyé ou non,apparaît avec le random
//	  private int posx;          //position x dans le terrain
//	  private int posy;          //position y dans le terrain
//	  private BonusBombe next;   //bonus suivant dans la liste 
//	  public  int type;         //type de bonus
//	  private Plateau pl;      // place occupée par le bonus sur le plateau 
 

//constructeur
//public BonusBombe(Plateau pla,int x,int y,int type){
 //   posx=x;
 //   posy=y;
 //   envoye=false;
 //   next=null;
//    pl=pla;
//    this.type=type;
//    if  (pla!=null);
  
// Non fini
// 
////constructeur
////ajoute le bonus a la fin de la liste
////check : =true : verifie qu'il n'y a pas deja de bonus a cet endroit (le supprime dans ce cas) 
////check==false,ne verifie pas.
// public bonus(Plateau pla,int x,int y,int type,bonus first,boolean check) 
// {
//     px=x;
//     py=y;
//     envoye=false;
//     add_last(first,this);
//     pl=pla;
//     this.type=type;
//     
//     if  (pl!=null)
//     {
//         if  (!check)
//             Non fini
//     }
// 
// 
////defini comme envoyé
// public  void set_sent()
// {
//     envoye=true;
// }
// 
// //defini comme non envoye
// public  void set_no_sent()
// {
//     envoye=false;
// }
// }
// 
// //effet du bonus sur le joueur lorsqu'il le ramasse --> PROBLEME
// public  void    effect_bonus(joueur j)
// {
//     pl.set_tile_at(Plateau,px,py);
//     pl.refresh_tile(px,py);
//     effect_bonus(j,type);
// }
// }
 
//effet du bonus de type type --> PROBLEME
// static public  void    effect_bonus(joueur j,byte type)
// {
//     switch(type)
//     {
//         case    Plateau.BombeObjet:     //bombe rouge
//             j.give_bomberouge(3); // le nombre 3 correspond à  celui accordé au bonus bombe rouge
//         break;
//         
//         case    Plateau.Flamme:    //flammes
//             // confère Soukaina
//         break;
//        
//       ???
//         break;
//     }
// }
//
//
//// bombe plus
//// bombe moins
// 
// 
//// Bonus de Vie --> NON FINI
// public void viesup(joueur j)
// this.vie=this.vie+1
// 
 
