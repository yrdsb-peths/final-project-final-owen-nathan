import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Coin Icon

public class Coin extends Actor
{
    /**
     * Act - do whatever the Coin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Coin() {
        GreenfootImage coin = new GreenfootImage("images/eebff9473989ed0.png");
        coin.scale(40, 40);
        setImage(coin);
    }
}
