import greenfoot.*;

public class Battlefield extends World {
    public Label wasdLabel;
    Label scoreLabel;
    public boolean w1 = false;
    public boolean w2 = false;
    public boolean w3 = false;
    
    int waveNumber = 1;
    Label waveLabel;
    boolean waveAnnounced = false;
    
    private static Battlefield instance_ = null;
    
    public static Battlefield _instance() {
        if(instance_ == null) {
            instance_ = new Battlefield();
        }
        return instance_;
    }
    
    private Battlefield() {
        super(600, 400, 1);
        
        ScoreKeeper.score = 1;
        
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        Cart cart = new Cart();
        addObject(cart, 25, 40);
        
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 50, 50);
        

        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 550, 60);
    }
    
    public void act(){
        scoreLabel.setValue(ScoreKeeper.score);
        
        if ((ScoreKeeper.score == 1 || ScoreKeeper.score == 6 || ScoreKeeper.score == 12 || ScoreKeeper.score == 18) && !waveAnnounced) {
             announceWave();
        }   
        
        if (waveAnnounced && Greenfoot.isKeyDown("space")) {
            removeObject(waveLabel);
            waveAnnounced = false;
        }
        
        if (ScoreKeeper.score == 1 && waveNumber == 1 && waveAnnounced){
            wave1();
        } else if (ScoreKeeper.score == 6 && waveNumber == 2 && waveAnnounced) {
            wave2();
        } else if (ScoreKeeper.score == 12 && waveNumber == 3 && waveAnnounced) {
            wave3();
        }
    }

    public void spawnHuman(int x, int y) {
    Human1 h1 = new Human1();
    addObject(h1, x, y);
    }   
    
    public void wave1(){
        spawnHuman(550, 10);
        spawnHuman(50, 60);
        spawnHuman(550, 110);
        spawnHuman(50, 150);
        spawnHuman(550, 200);
        w1 = true;
        waveNumber++;
    }
    
    public void wave2(){
        spawnHuman(100, 50);
        spawnHuman(500, 60);
        spawnHuman(100, 110);
        spawnHuman(500, 150);
        spawnHuman(100, 200);
        spawnHuman(500, 250);
        w2 = true;
        waveNumber++;
    }
    
    public void wave3(){
        spawnHuman(150, 50);
        spawnHuman(450, 100);
        spawnHuman(150, 150);
        spawnHuman(450, 200);
        spawnHuman(150, 250);
        spawnHuman(450, 300);
        w3 = true;
        waveNumber++;
    }

    
    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
    
    public void announceWave() {
        waveLabel = new Label("Wave " + waveNumber, 60);
        addObject(waveLabel, getWidth() / 2, getHeight() / 2);
        waveAnnounced = true;
    }

}
