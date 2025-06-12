import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Icon for the fire counter 

public class FireCount extends Actor
{
    /**
     * Act - do whatever the FireCount wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public FireCount() {
        GreenfootImage fire = new GreenfootImage("images/Buff_Fire_Rate-removebg-preview.png");
        fire.scale(30, 30);
        setImage(fire);
    }
}
