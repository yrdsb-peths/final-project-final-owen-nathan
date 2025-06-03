import greenfoot.*;
import java.util.List;
import java.util.Random;

public class Human1 extends Actor {
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
    boolean punching = false;
    int punchFrame = 0;
    boolean hasDealtDamageThisPunch = false;

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

        int dx = gorilla.getX() + offsetX - getX();
        int dy = gorilla.getY() + offsetY - getY();

        facingRight = dx >= 0;

        if (!punching) {
            if (Math.abs(dx) > 5) {
                setLocation(getX() + (dx > 0 ? speed : -speed), getY());
            }
            if (Math.abs(dy) > 5) {
                setLocation(getX(), getY() + (dy > 0 ? speed : -speed));
            }
        }
    }

    private void punchLoop() {
        int delay = 250;
        if (animationTimer.millisElapsed() < delay) return;
        animationTimer.mark();

        if (!punching) {
            if (Greenfoot.getRandomNumber(100) < 10) { // 10% chance to start punch
                punching = true;
                punchFrame = 0;
                hasDealtDamageThisPunch = false;
            }
        } else {
            if (facingRight) {
                setImage(punchAnim[punchFrame]);
            } else {
                setImage(punchAnimFlipped[punchFrame]);
            }

            if (punchFrame == 2 && !hasDealtDamageThisPunch) {
                dealDamageToGorilla();
                hasDealtDamageThisPunch = true;
            }

            punchFrame++;
            if (punchFrame >= punchAnim.length) {
                punching = false;
            }
        }
    }

    private void dealDamageToGorilla() {
        World world = getWorld();
        if (world == null) return;
        List<Gorilla> gorillas = world.getObjects(Gorilla.class);
        if (gorillas.isEmpty()) return;

        Gorilla gorilla = gorillas.get(0);
        if (this.intersects(gorilla)) {
            gorilla.updateHealth(-10);
        }
    }

    private void checkGorillaPunch() {
        World world = getWorld();
        if (world == null) return;
        List<PunchHitbox> hits = world.getObjects(PunchHitbox.class);
        for (PunchHitbox hitbox : hits) {
            if (this.intersects(hitbox) && !isDead) {
                takeDamage();
                world.removeObject(hitbox);
                break;
            }
        }
    }

    public void takeDamage() {
        if (!isDead) {
            isDead = true;
            frame = 0;
            animationTimer.mark();
        }
    }

    private void playDeathAnimation() {
        if (deathFinished) return;

        int delay = 150;
        if (animationTimer.millisElapsed() < delay) return;
        animationTimer.mark();

        if (facingRight) {
            setImage(deathAnim[frame]);
        } else {
            setImage(deathAnimFlipped[frame]);
        }

        frame++;
        if (frame >= deathAnim.length) {
            deathFinished = true;
            getWorld().removeObject(this);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isPunching() {
        return punching;
    }
}
