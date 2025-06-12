import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Initial cutscene/animation

public class Cutscene1 extends World
{
    Label text = new Label("a crash landing onto an unfamiliar planet...", 30);
    /**
     * Constructor for objects of class Cutscene1.
     * 
     */
    public Cutscene1()
    {    
        // Create a n578ew world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        GreenfootImage earth = new GreenfootImage("images/earth.jpg");
        earth.scale(600, 400);
        setBackground(earth);
        
        addObject(text, 300, 380);
        
        Ship asteroid = new Ship();
        addObject(asteroid, Greenfoot.getRandomNumber(600), 0);
    }
}
