import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(-6);
        if(getX() <= 0 ) {
            getWorld().removeObject(this);
        } else if(isTouching(Gorilla.class)) {
            Gorilla.isTouchingBomb = true;
        }
    }
    
    public Bomb() {
        GreenfootImage bomb = new GreenfootImage("s-l1200-removebg-preview.png");
        bomb.scale(80, 80);
        setImage(bomb);
    }
}
