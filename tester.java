import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tester extends World
{
    Label2 coinLabel;
    Label2 HP = new Label2("HP\u219210", 25);
    Label2 PET = new Label2("PET\u219250", 25);
    Label2 FIRE = new Label2("Fire Trap\u219220", 25);
    Label2 descriptionHP = new Label2("Restores full vitality", 20);
    Label2 descriptionPET = new Label2("Doesn't do much,", 20);
    Label2 descriptionPET2 = new Label2("just here for the ride", 20);
    Label2 descriptionFIRE = new Label2("Trap to damage enemies", 20);
    Label2 buyH = new Label2("Press H to Buy", 25);
    Label2 buyP = new Label2("Press P to Buy", 25);
    Label2 buyF = new Label2("Press F to Buy", 25);
    
    public void act() {
        coinLabel.setValue("" + Currency.getCoins());
    }

    
    /**
     * Constructor for objects of class tester.
     * 
     */
    public tester()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        //Gorilla gorilla = new Gorilla();
        //addObject(gorilla, 550, 350);
        
        Sword sword = new Sword();
        addObject(sword, 575, 370);
        
        coinLabel = new Label2("" + Currency.getCoins(), 40);
        addObject(coinLabel, 40, 360);
        
        Coin coin = new Coin();
        addObject(coin, 20, 362);
        
        Coin coin2 = new Coin();
        addObject(coin2, 132, 72);
        
        Coin coin3 = new Coin();
        addObject(coin3, 332, 72);
        
        Coin coin4 = new Coin();
        addObject(coin4, 555, 72);
        
        Sale sale = new Sale(); 
        addObject(sale, 300, 30);
        
        Health hp = new Health();
        addObject(hp, 95, 135);
        addObject(HP, 90, 70);
        addObject(descriptionHP, 95, 260);
        addObject(buyH, 95, 205);
        
        Fire fire = new Fire();
        addObject(fire, 495, 135);
        addObject(FIRE, 485, 70);
        addObject(descriptionFIRE, 495, 260);
        addObject(buyF, 495, 205);
        
        Pet pet = new Pet();
        addObject(pet, 295, 135);
        addObject(PET, 285, 70);
        addObject(descriptionPET, 295, 258);
        addObject(descriptionPET2, 295, 272);
        addObject(buyP, 295, 205);
    }
}
