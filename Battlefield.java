import greenfoot.*;

public class Battlefield extends World {
    public Label wasdLabel;
    Label scoreLabel;
    public boolean w1 = false;
    
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
        if (ScoreKeeper.score >= 1 && !w1){
            wave1();
            w1 = true;
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
    }
    
    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
}
