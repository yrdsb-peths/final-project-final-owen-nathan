import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * titlescreen of game
 * 
 * @author Ji 
 * @version 5/20
 */
public class Titlescreen extends World
{

    /**
     * Constructor for objects of class Titlescreen.
     * 
     */
    public Titlescreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);  
        setBackground("images/titleScreen.jpg");
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Label label = new Label("100 Men Vs. 1 Gorilla", 50);
        addObject(label,289,192);
    }
}
