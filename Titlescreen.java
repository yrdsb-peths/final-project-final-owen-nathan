import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * titlescreen of game
 * 
 * @author Ji 
 * @version 5/20
 */
public class Titlescreen extends World
{
    Label titleLabel = new Label("Planet of Men", 80);
    Label start = new Label("Press space to start", 30);
    /**
     * Constructor for objects of class Titlescreen.
     * 
     */
    public Titlescreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);  
        setBackground("images/titleScreen.jpg");
        addObject(titleLabel, 300, 200);
        addObject(start, 300, 250);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("space")) {
            MyWorld gameWorld = new MyWorld();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
