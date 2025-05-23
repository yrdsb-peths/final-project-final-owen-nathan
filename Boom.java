import greenfoot.*;

public class Boom extends Actor {
    private int frames = 40;
    private int spawnX, spawnY;

    public Boom(int x, int y) {
        spawnX = x;
        spawnY = y;
        GreenfootImage boom = new GreenfootImage("images/boom.png");
        boom.scale(80, 80);
        setImage(boom);
    }

    public void act() {
        if (frames <= 0) {
            getWorld().addObject(new Gorilla(), spawnX, spawnY);
            getWorld().removeObject(this);
        } else {
            frames--;
        }
    }
}
