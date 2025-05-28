import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

public class Human1 extends Actor
{
    GreenfootImage[] punchAnim = new GreenfootImage[4];
    GreenfootImage[] punchAnimFlipped = new GreenfootImage[4];
    GreenfootImage[] deathAnim = new GreenfootImage[5];
    GreenfootImage[] deathAnimFlipped = new GreenfootImage[5];

    SimpleTimer animationTimer = new SimpleTimer();
    int frame = 0;
    boolean isDead = false;
    boolean deathFinished = false;
    boolean facingRight = true;
    boolean scoreGiven = false;

    int speed = 1;
    int offsetX, offsetY;

    public Human1() {
        for (int i = 0; i < 4; i++) {
            punchAnim[i] = new GreenfootImage("Human1_Punch" + i + ".png");
            punchAnim[i].scale(80, 80);

            punchAnimFlipped[i] = new GreenfootImage(punchAnim[i]);
            punchAnimFlipped[i].mirrorHorizontally();
        }

        for (int i = 0; i < 5; i++) {
            deathAnim[i] = new GreenfootImage("Human1_Death" + i + ".png");
            deathAnim[i].scale(130, 80);

            deathAnimFlipped[i] = new GreenfootImage(deathAnim[i]);
            deathAnimFlipped[i].mirrorHorizontally();
        }

        setImage(punchAnim[0]);
        animationTimer.mark();

        // Small random offset to prevent stacking of enemy
        Random rand = new Random();
        offsetX = rand.nextInt(41) - 20; 
        offsetY = rand.nextInt(41) - 20;
    }

    public void act() {
        if (!isDead) {
            moveTowardGorilla();
            punchLoop();
            checkGorillaPunch();
        } else {
            playDeathAnimation();
            if (!scoreGiven) {
                World world = getWorld();
                if (world instanceof Tutorial) {
                    ((Tutorial) world).increaseScore();
                } else if (world instanceof Battlefield) {
                    ((Battlefield) world).increaseScore();
                }
                scoreGiven = true;
            }
        }
    }

    private void moveTowardGorilla() {
        World world = getWorld();
        if (world == null) return;
        List<Gorilla> gorillas = world.getObjects(Gorilla.class);
        if (gorillas.isEmpty()) return;

        Gorilla gorilla = gorillas.get(0);

        int dx = (gorilla.getX() + offsetX) - getX();
        int dy = (gorilla.getY() + offsetY) - getY();

        facingRight = dx >= 0;

        if (Math.abs(dx) > speed) {
            setLocation(getX() + (dx > 0 ? speed : -speed), getY());
        }

        if (Math.abs(dy) > speed) {
            setLocation(getX(), getY() + (dy > 0 ? speed : -speed));
        }
    }

    private void punchLoop() {
        if (animationTimer.millisElapsed() > 200) {
            if (facingRight) {
                setImage(punchAnim[frame]);
            } else {
                setImage(punchAnimFlipped[frame]);
            }
            frame = (frame + 1) % punchAnim.length;
            animationTimer.mark();
        }
    }

    private void checkGorillaPunch() {
        Actor gorilla = getOneIntersectingObject(Gorilla.class);
        if (gorilla != null && ((Gorilla)gorilla).isPunching()) {
            isDead = true;
            frame = 0;
            animationTimer.mark();
        }
    }

    private void playDeathAnimation() {
        if (!deathFinished && animationTimer.millisElapsed() > 200) {
            if (facingRight) {
                setImage(deathAnim[frame]);
            } else {
                setImage(deathAnimFlipped[frame]);
            }
            frame++;
            animationTimer.mark();

            if (frame >= deathAnim.length) {
                deathFinished = true;
                if (getWorld() != null) {
                    getWorld().removeObject(this);
                }
            }
        }
    }
}
