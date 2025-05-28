import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cutscene1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cutscene1 extends World
{
    Label text = new Label("a crash landing onto an unfamiliar planet...", 32);
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
        
        addObject(text, 340, 380);
        
        Ship asteroid = new Ship();
        addObject(asteroid, Greenfoot.getRandomNumber(600), 0);
    }
}
