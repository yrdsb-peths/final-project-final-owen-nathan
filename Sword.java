import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Battlefield logo

public class Sword extends Actor
{
    /**
     * Act - do whatever the Sword wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Sword() {
        GreenfootImage sword = new GreenfootImage("images/sword.png");
        sword.scale(40, 40);
        setImage(sword);
    }
}
