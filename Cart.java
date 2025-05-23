import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Cart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Cart extends Actor
{
    /**
     * Act - do whatever the Cart wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Cart() {
        GreenfootImage cart = new GreenfootImage("images/cart.png");
        cart.scale(40, 40);
        setImage(cart);
    }
}
