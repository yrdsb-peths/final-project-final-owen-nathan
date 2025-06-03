import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Trap extends Actor {
    private GreenfootImage[] fireFrames = new GreenfootImage[11];
    private int frame = 0;
    private int animationCounter = 0;
    private final int ANIMATION_SPEED = 5;

    public Trap() {
        for (int i = 0; i < fireFrames.length; i++) {
            fireFrames[i] = new GreenfootImage("Fire_" + i + ".png");
            fireFrames[i].scale(80, 80);
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
