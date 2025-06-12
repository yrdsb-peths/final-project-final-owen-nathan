import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Delay the spawning of human1

public class DelayedSpawner extends Actor {
    private int frames;
    private int x, y;

    public DelayedSpawner(int delayFrames, int x, int y) {
        this.frames = delayFrames;
        this.x = x;
        this.y = y;
    }

    public void act() {
        if (frames <= 0 && getWorld() != null) {
            Battlefield world = (Battlefield)getWorld();
            world.spawnHuman(x, y);
            getWorld().removeObject(this);
        } else {
            frames--;
        }
    }
}

