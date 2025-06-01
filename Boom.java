import greenfoot.*;

public class Boom extends Actor {
    private int frames = 40;
    private int spawnX, spawnY;
    private Label wasdLabel; 

    public Boom(int x, int y) {
        spawnX = x;
        spawnY = y;
        GreenfootImage boom = new GreenfootImage("images/boom.png");
        boom.scale(80, 80);
        setImage(boom);
    }

    public void act() {
        if (frames <= 0 && getWorld() != null) {
            World world = getWorld();
            Gorilla gorilla = Gorilla.getInstance();
            world.addObject(gorilla, spawnX, spawnY);
    
            world.removeObject(this); 
            return;
        }
    
        frames--;
    }

}
