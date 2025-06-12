import greenfoot.*;  
// Titlescreen of the game

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
        }
    }
}
