import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * titlescreen of game
 * 
 * @author Ji 
 * @version 5/20
 */
public class Titlescreen extends World
{
    Label titleLabel = new Label("Planet of Men", 80);
    Label start = new Label("Press space to start", 30);
    
    public Titlescreen()
    {    
        super(600, 400, 1);  
        setBackground("images/titleScreen.jpg");
        prepare();
    }    
    
    private void prepare() {
        addObject(titleLabel, 300, 200);
        addObject(start, 300, 250);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("space")) {
            Cutscene1 cutscene = new Cutscene1();
            Greenfoot.setWorld(cutscene);
            //Tutorial tut = new Tutorial();
            //Greenfoot.setWorld(tut);
        }
    }
}
