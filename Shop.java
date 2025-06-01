import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Shop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shop extends World
{
    public Battlefield prevWorld;
    
    private static Shop _instance = null;
    Label coinLabel;
    
    public static Shop instance() {
        if(_instance == null) {
            _instance = new Shop();
        }
        return _instance;
    }
    
    /**
     * Constructor for objects of class Shop.
     * 
     */
    private Shop()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 550, 350);
        
        Sword sword = new Sword();
        addObject(sword, 575, 370);
        
        coinLabel = new Label("" + Currency.getCoins(), 40);
        addObject(coinLabel, 40, 360);
        
        Coin coin = new Coin();
        addObject(coin, 20, 362);
    }
}
