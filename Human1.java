import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Easiest enemy the regular human tha throws punches and dies upon 1 hit
 */
public class Human1 extends Actor
{
    GreenfootImage[] punchAnim = new GreenfootImage[4];
    GreenfootImage[] deathAnim = new GreenfootImage[5];

    SimpleTimer animationTimer = new SimpleTimer();
    int frame = 0;
    boolean isDead = false;
    boolean deathFinished = false;

    public Human1() {
        for (int i = 0; i < 4; i++) {  
            punchAnim[i] = new GreenfootImage("Human1_Punch" + i + ".png");
            punchAnim[i].scale(80, 80);
        }
        
        for (int i = 0; i < 5; i++) { 
            deathAnim[i] = new GreenfootImage("Human1_Death" + i + ".png");
            deathAnim[i].scale(130, 80);
        }
        setImage(punchAnim[0]);
        animationTimer.mark();
    }

    public void act() {
        if (!isDead) {
            punchLoop();
            checkGorillaPunch();
        } else {
            playDeathAnimation();
        }
    }

    private void punchLoop() {
        if (animationTimer.millisElapsed() > 200) {
            setImage(punchAnim[frame]);
            frame = (frame + 1) % punchAnim.length;
            animationTimer.mark();
        }
    }

    private void checkGorillaPunch() {
        Actor gorilla = getOneIntersectingObject(Gorilla.class);
        if (gorilla != null && ((Gorilla) gorilla).isPunching()) {
            isDead = true;
            frame = 0;
            animationTimer.mark();
        }
    }

    private void playDeathAnimation() {
        if (!deathFinished && animationTimer.millisElapsed() > 200) {
            setImage(deathAnim[frame]);
            frame++;
            animationTimer.mark();
            if (frame >= deathAnim.length) {
                deathFinished = true;
                getWorld().removeObject(this);
            }
        }
    }
}