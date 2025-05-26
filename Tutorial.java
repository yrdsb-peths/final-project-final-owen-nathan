import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CutsceneWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tutorial extends World
{
    public boolean landed = false;
    public boolean crossed = false;
    public int score = 0;
    Label text = new Label("A gorilla finds himself stranded in a new world...", 32);
    Label wasdLabel = new Label("WASD to move", 50); // ← track label here
    Label scoreLabel;

    public Tutorial()
    {
        
        
        super(600, 400, 1);
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 50, 50);
        
        addObject(text, 300, 20);

        Ship asteroid = new Ship();
        addObject(asteroid, Ship.x, 0);

        // Add WASD label now; we’ll remove it later
        addObject(wasdLabel, 300, 300);
        spawnHuman(400, 300);
    }

    public void act() {
        if (crossed && wasdLabel != null) {
            removeObject(wasdLabel);
            wasdLabel = null;
        }
    }
    
     public void increaseScore(){
       score++; 
       scoreLabel.setValue(score);
    }
    
    public void spawnHuman(int x, int y) {
    Human1 h1 = new Human1();
    addObject(h1, x, y);
    }   
}

