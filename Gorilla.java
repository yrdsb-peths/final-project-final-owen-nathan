import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gorilla here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gorilla extends Actor
{
    private int speed = 4;

    public void act()
    {
        checkMovement();
    }

    private void checkMovement()
    {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
        }
    }
}
