import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Animation for the fire trap that gets placed down after bought in the shop

public class Trap extends Actor {
    private GreenfootImage[] fireFrames = new GreenfootImage[11];
    private int frame = 0;
    private int animationCounter = 0;
    private final int ANIMATION_SPEED = 5;
    //only works against first class humans to make the game harder
    public Trap() {
        for (int i = 0; i < fireFrames.length; i++) {
            fireFrames[i] = new GreenfootImage("Fire_" + i + ".png");
            fireFrames[i].scale(30, 30);
        }
        setImage(fireFrames[0]);
    }

    public void act() {
        animateFire();
    }

    private void animateFire() {
        animationCounter++;
        if (animationCounter >= ANIMATION_SPEED) {
            frame = (frame + 1) % fireFrames.length;
            setImage(fireFrames[frame]);
            animationCounter = 0;
        }
    }
}