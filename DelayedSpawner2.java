import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DelayedSpawner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DelayedSpawner2 extends Actor {
    private int frames;
    private int x, y;

    public DelayedSpawner2(int delayFrames, int x, int y) {
        this.frames = delayFrames;
        this.x = x;
        this.y = y;
    }

    public void act() {
        if (frames <= 0 && getWorld() != null) {
            Battlefield world = (Battlefield)getWorld();
            world.spawnHuman2(x, y);
            getWorld().removeObject(this);
        } else {
            frames--;
        }
    }
}

