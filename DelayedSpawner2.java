import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Delays the spawning of human2

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

