import greenfoot.*;

public class Boom extends Actor {
    private int frames = 40;
    private int spawnX, spawnY;
    private Label wasdLabel; 
    GreenfootSound explosionSound = new GreenfootSound("animated-cartoon-explosion-impact-352744.mp3");

    public Boom(int x, int y) {
        spawnX = x;
        spawnY = y;
        GreenfootImage boom = new GreenfootImage("images/boom.png");
        boom.scale(80, 80);
        setImage(boom);
    }

    public void act() {
        explosionSound.play();
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
