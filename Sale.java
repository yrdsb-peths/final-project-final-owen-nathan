import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Image for the shop

public class Sale extends Actor
{
    /**
     * Act - do whatever the Sale wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Sale() {
        GreenfootImage sale = new GreenfootImage("images/Buff_Sale-removebg-preview.png");
        sale.scale(100, 100);
        setImage(sale);
    }
}
