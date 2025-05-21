import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Asteroid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Asteroid extends Actor
{
    /**
     * Act - do whatever the Asteroid wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        setLocation(getX(), getY() + 3);
        
        if(Greenfoot.isKeyDown("left")) {
            move(-2);
        } else if(Greenfoot.isKeyDown("right")) {
            move(2);
        }
        
        Cutscene1 cutscene = (Cutscene1) getWorld();
        if(getY() >= 399) {
            cutscene.removeObject(this);
        }
    }
    
    public Asteroid() {
        GreenfootImage asteroid = new GreenfootImage("images/asteroid.png");
        asteroid.scale(40, 40);
        setImage(asteroid);
    }
}
