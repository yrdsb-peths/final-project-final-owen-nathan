import greenfoot.*;  
import java.util.List;
import java.util.Random;

public class Human2 extends Actor {
    GreenfootImage[] attackAnim = new GreenfootImage[5];
    GreenfootImage[] attackAnimFlipped = new GreenfootImage[5];
    GreenfootImage[] deathAnim = new GreenfootImage[5];
    GreenfootImage[] deathAnimFlipped = new GreenfootImage[5];
    GreenfootImage[] hurtAnim = new GreenfootImage[4];
    GreenfootImage[] hurtAnimFlipped = new GreenfootImage[4];

    SimpleTimer animationTimer = new SimpleTimer();

    int frame = 0;
    int hurtFrame = 0;
    int health = 2;

    boolean isDead = false;
    boolean deathFinished = false;
    boolean isHurt = false;
    boolean facingRight = true;
    boolean scoreGiven = false;
    boolean hasDealtDamageThisPunch = false;
    private boolean wasJustHit = false;

    int speed = 2;
    int offsetX, offsetY;

    public Human2() {
        for (int i = 0; i < 5; i++) {
            attackAnim[i] = new GreenfootImage("Human2_Attack" + i + ".png");
            attackAnim[i].scale(120, 100);
            attackAnimFlipped[i] = new GreenfootImage(attackAnim[i]);
            attackAnimFlipped[i].mirrorHorizontally();

            deathAnim[i] = new GreenfootImage("Human2_Death" + i + ".png");
            deathAnim[i].scale(100, 100);
            deathAnimFlipped[i] = new GreenfootImage(deathAnim[i]);
            deathAnimFlipped[i].mirrorHorizontally();
        }

        for (int i = 0; i < 4; i++) {
            hurtAnim[i] = new GreenfootImage("Human2_Hurt" + i + ".png");
            hurtAnim[i].scale(100, 90);
            hurtAnimFlipped[i] = new GreenfootImage(hurtAnim[i]);
            hurtAnimFlipped[i].mirrorHorizontally();
        }

        setImage(attackAnim[0]);
        animationTimer.mark();

        Random rand = new Random();
        offsetX = rand.nextInt(41) - 20;
        offsetY = rand.nextInt(41) - 20;
    }

    public void act() {
        if (!isDead) {
            moveTowardGorilla();
            if (isHurt) {
                playHurtAnimation();
            } else {
                punchLoop();
                checkGorillaPunch();
            }
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

        if (Math.abs(dx) > speed) setLocation(getX() + (dx > 0 ? speed : -speed), getY());
        if (Math.abs(dy) > speed) setLocation(getX(), getY() + (dy > 0 ? speed : -speed));
    }

    private void checkGorillaPunch() {
        Actor gorilla = getOneIntersectingObject(Gorilla.class);
        if (gorilla != null && ((Gorilla) gorilla).isPunching()) {
            if (!wasJustHit && !isDead && !isHurt) {
                takeDamage();  // call takeDamage() instead of decrementing health here
            }
        } else {
            wasJustHit = false;
        }
    }

    private void playHurtAnimation() {
        if (animationTimer.millisElapsed() > 150) {
            if (facingRight) {
                setImage(hurtAnim[hurtFrame]);
            } else {
                setImage(hurtAnimFlipped[hurtFrame]);
            }
            hurtFrame++;
            animationTimer.mark();
    
            if (hurtFrame >= hurtAnim.length) {
                isHurt = false;
                frame = 0;
                wasJustHit = false;  // reset so can be hit again after hurt animation
            }
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

    private void punchLoop() {
        if (animationTimer.millisElapsed() > 200) {
            if (facingRight) {
                setImage(attackAnim[frame]);
            } else {
                setImage(attackAnimFlipped[frame]);
            }

            if (frame == 3 && !hasDealtDamageThisPunch) {
                dealDamageToGorilla();
                hasDealtDamageThisPunch = true;
            }

            frame = (frame + 1) % attackAnim.length;

            if (frame == 0) {
                hasDealtDamageThisPunch = false;
            }

            animationTimer.mark();
        }
    }

    private void dealDamageToGorilla() {
        Gorilla gorilla = (Gorilla) getOneIntersectingObject(Gorilla.class);
        if (gorilla != null) {
            gorilla.updateHealth(-5);
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isPunching() {
        return frame == 3 && !isDead && !isHurt;
    }
    
    public void takeDamage() {
        if (!wasJustHit && !isDead && !isHurt) {
            health--;
            wasJustHit = true;
    
            if (health <= 0) {
                isDead = true;
                frame = 0;
                animationTimer.mark();
            } else {
                isHurt = true;
                hurtFrame = 0;
                animationTimer.mark();
            }
        }
    }


}