import greenfoot.*;

public class Battlefield extends World {
    public Label wasdLabel;
    public static int level = 1;
    
    private static Battlefield instance_ = null;
    
    public static Battlefield _instance() {
        if(instance_ == null) {
            instance_ = new Battlefield();
        }
        return instance_;
    }
    
    private Battlefield() {
        super(600, 400, 1);

        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        Cart cart = new Cart();
        addObject(cart, 25, 40);
        
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 50, 50);
        
        
    }
    
    public void spawnHuman(int x, int y) {
    Human1 h1 = new Human1();
    addObject(h1, x, y);
    }   
}
