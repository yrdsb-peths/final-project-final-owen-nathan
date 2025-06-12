import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Health Icon in Shop

public class Health extends Actor
{
    /**
     * Act - do whatever the Health wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Health() {
        GreenfootImage hp = new GreenfootImage("images/Buff_Health-removebg-preview.png");
        hp.scale(100, 100);
        setImage(hp);
    }
}
