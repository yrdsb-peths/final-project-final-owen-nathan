import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

//Pet Icon

public class Pet extends Actor
{
    /**
     * Act - do whatever the Pet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }
    
    public Pet() {
        GreenfootImage pet = new GreenfootImage("images/Buff_Pet-removebg-preview.png");
        pet.scale(100, 100);
        setImage(pet);
    }
}
