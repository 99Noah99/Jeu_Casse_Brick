


// Taille de la grille
int X = 13;  
int Y = 10;

//Tableau de bricks
Brick briks[] = new Brick[X*Y];

//Position Palais
float player = width/2;

//Vitesse Palais 
float vPlayer = 0;

//Compte à rebours avant départ balle
int delay = 100;

//Score début
int lives = 3;

boolean left = false;
boolean right = false; 

//Police de base
pFont font = new Pfont();

//Pixel balle 
float speed = 5;

//Position 
float ball[] = new float[2];

//Vitesse balle 
float vBall[] = new float [2];


void setup() {
    size(500, 500); // Windows pixel
    frameRate(50);
    font = createFont('Arial', 30); //Police type Arial, Taille.
}


void reset(){
    //Afin de recommencer une nouvelle partie.
    vPlayer = 0 ;
    player = width/2;
    delay = 100;
    live = 3;
    score = 0
    speed = 5;
    float angle = random(1)-0.5; // Angle aléatoire
    ball[0] = width/2;
    ball[1] = 360;
    vBall[0] = sin(angle)*speed; 
    vBall[1] = -cos(angle)*speed; // Direction 
    int count = 0;

    //Type de dommage par brick 
    for (int x = 0; x < X; x++){
        for (int y = 0; y < Y; y++){
        int l = ; // Dommage à infliger au bricks avant destruction
        if(y < 5) l = 2; // Brick en haut doit être taper 2 fois pour être détruit
        if(y < 3) l =3;  // Brick encore plus haut doit être taper 3 fois pour être détruit
        bricks[count] = new Brick(width/2-X*28/2/2+x*28, 50+y*16, l); // Création brick + Position, positon Y, Nombre dégat 
        count++;
        }
    }
}
    
void draw() {
    textAlign(CENTRER, CENTER);
    noStroke();
    background(30, 42, 60);
    //Parcourir toute les bricks
    for(int i = 0; i < X*Y; i++) {
        if(!bricks[i].getDestroyed()){
            brick[i].collide(); // Vérification colision 
            if (bricks[i].getDestroyed()) continue;// Si brick détruite continuer
            fill(150, 150, 200); //Couleur brick  (1 seul contact et détruite)
            if (bricks[i].getDamage() == 2) fill(120, 120, 175); //Couleur brick  (2 contacts et détruite)
            if (bricks[i].getDamage() == 3) fill(90, 90, 140);//Couleur brick  (3 contacts et détruite)
            rect(bricks[i].gex(), bricks[i].getY, 26, 14) ; // Déssine la brick
        }
    }
    //Déssiner Player
    fill (210, 210, 230);
    rect(player-50, height-30, 100, 15); // Player taille 
    fill(240, 240, 255); // Balle couleur
    ellipse(ball[0], ball[1], 14, 14);


}

class Brick{
    int x, y, damage; // Dommage
    boolean destroyed = false; 
    Brick(int _x, int _y, int dam){
        x = _x;
        y = _y;
        damage = dam;
    }
    boolean getDestroyed(){return destroyed;}
    int getDamage(){return damage;}
    int getX(){return x;}
    int getY(){return y;}
    void collide()

}




