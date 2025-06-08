import greenfoot.*;
import java.util.List;
import java.util.Random;

public class FinalBoss extends Actor {
    // Animation arrays
    GreenfootImage[] attackAnim = new GreenfootImage[5];
    GreenfootImage[] attackAnimFlipped = new GreenfootImage[5];
    GreenfootImage[] deathAnim = new GreenfootImage[5];
    GreenfootImage[] deathAnimFlipped = new GreenfootImage[5];
    GreenfootImage[] hurtAnim = new GreenfootImage[5];
    GreenfootImage[] hurtAnimFlipped = new GreenfootImage[5];

    SimpleTimer animationTimer = new SimpleTimer();
    SimpleTimer shootTimer = new SimpleTimer();
    SimpleTimer hurtTimer = new SimpleTimer();
    
    private int verticalDirection = 1;
    private int speed = 3;
    private int changeDirectionCooldown = 0;
    int health = 1;
    int frame = 0;
    boolean scoreGiven = false;
    boolean facingRight = true;
    boolean isDead = false;
    boolean deathFinished = false;
    boolean isHurt = false;
    boolean isAttacking = false;

    public FinalBoss() {
        // Load images and scale them
        for (int i = 0; i < 5; i++) {
            attackAnim[i] = new GreenfootImage("RobotAttack_" + i + ".png");
            attackAnim[i].scale(120, 120);
            attackAnimFlipped[i] = new GreenfootImage(attackAnim[i]);
            attackAnimFlipped[i].mirrorHorizontally();

            deathAnim[i] = new GreenfootImage("RobotDeath_" + i + ".png");
            deathAnim[i].scale(130, 130);
            deathAnimFlipped[i] = new GreenfootImage(deathAnim[i]);
            deathAnimFlipped[i].mirrorHorizontally();

            hurtAnim[i] = new GreenfootImage("RobotHurt_" + i + ".png");
            hurtAnim[i].scale(120, 120);
            hurtAnimFlipped[i] = new GreenfootImage(hurtAnim[i]);
            hurtAnimFlipped[i].mirrorHorizontally();
        }

        setImage(attackAnim[0]);
        animationTimer.mark();
        shootTimer.mark();
        hurtTimer.mark();
    }

    public void act() {
        if (isDead) {
            playDeathAnimation();
            if (!scoreGiven) {
                World world = getWorld();
                ((Battlefield) world).increaseScore();
                scoreGiven = true;
            }
            return;
        }

        if (isHurt) {
            playHurtAnimation();
            return;
        }

        moveVertically();
        facePlayer();
        handleAttackAnimation();
        shootBeamIfReady();
    }

    private void moveVertically() {
        if (changeDirectionCooldown <= 0) {
            // Random chance to change direction every frame when cooldown hits 0
            if (Greenfoot.getRandomNumber(100) < 10) {  // 10% chance to switch direction
                verticalDirection = -verticalDirection;
                changeDirectionCooldown = 50;  
            }
        } else {
            changeDirectionCooldown--;
        }
    
        // Move vertically
        int newY = getY() + speed * verticalDirection;
    
        // Keep inside vertical bounds
        if (newY <= 20) {
            newY = 20;
            verticalDirection = 1; // go down
            changeDirectionCooldown = 20;
        } else if (newY >= getWorld().getHeight() - 20) {
            newY = getWorld().getHeight() - 20;
            verticalDirection = -1; 
            changeDirectionCooldown = 20;
        }
    
        setLocation(getX(), newY);
    }
    
    private void facePlayer() {
        World world = getWorld();
        if (world == null) return;
        List<Gorilla> gorillas = world.getObjects(Gorilla.class);
        if (gorillas.isEmpty()) return;
    
        Gorilla gorilla = gorillas.get(0);
    
        if (gorilla.getX() < getX()) {
            facingRight = false; 
        } else {
            facingRight = true; 
        }
    }

    private void handleAttackAnimation() {
        int delay = 100; // ms between frames
        if (animationTimer.millisElapsed() < delay) return;
        animationTimer.mark();

        isAttacking = true;

        if (facingRight) {
            setImage(attackAnim[frame]);
        } else {
            setImage(attackAnimFlipped[frame]);
        }

        frame++;
        if (frame >= attackAnim.length) {
            frame = 0;
            isAttacking = false;
        }
    }

    private void shootBeamIfReady() {
        int shootDelay = 400; // milliseconds between shots
        if (shootTimer.millisElapsed() < shootDelay) return;

        shootTimer.mark();

        // Spawn a beam
        World world = getWorld();
        if (world == null) return;

        int beamX = facingRight ? getX() + 60 : getX() - 60;
        int beamY = getY();

        Beam beam = new Beam(facingRight);
        world.addObject(beam, beamX, beamY);
    }

    public void takeDamage() {
        if (isDead || isHurt) return;

        health --;;
        if (health <= 0) {
            isDead = true;
            frame = 0;
            animationTimer.mark();
        } else {
            // Hurt animation trigger
            isHurt = true;
            frame = 0;
            hurtTimer.mark();
        }
    }

    private void playHurtAnimation() {
        int hurtDelay = 100; // ms per frame / frequency of attack
        if (hurtTimer.millisElapsed() < hurtDelay) return;
        hurtTimer.mark();

        if (facingRight) {
            setImage(hurtAnim[frame]);
        } else {
            setImage(hurtAnimFlipped[frame]);
        }

        frame++;
        if (frame >= hurtAnim.length) {
            frame = 0;
            isHurt = false;
        }
    }

    private void playDeathAnimation() {
        int deathDelay = 150;
        if (animationTimer.millisElapsed() < deathDelay) return;
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
    
    private void checkGorillaPunch() {
        World world = getWorld();
        if (world == null) return;
    
        List<Gorilla> gorillas = world.getObjects(Gorilla.class);
        if (gorillas.isEmpty()) return;
    
        Gorilla gorilla = gorillas.get(0);
    }

}
