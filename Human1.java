    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Easiest enemy the regular human that throws punches and dies upon 1 hit
 */
public class Human1 extends Actor
{
    GreenfootImage[] punchAnim = new GreenfootImage[4];
    GreenfootImage[] punchAnimFlipped = new GreenfootImage[4]; // mirrored images
    GreenfootImage[] deathAnim = new GreenfootImage[5];
    GreenfootImage[] deathAnimFlipped = new GreenfootImage[5]; // mirrored images

    SimpleTimer animationTimer = new SimpleTimer();
    int frame = 0;
    boolean isDead = false;
    boolean deathFinished = false;
    
    int speed = 1;  // movement speed
    boolean facingRight = true;  // track direction to choose image

    public Human1() {
        // Load punch images and mirrored versions
        for (int i = 0; i < 4; i++) {  
            punchAnim[i] = new GreenfootImage("Human1_Punch" + i + ".png");
            punchAnim[i].scale(80, 80);

            punchAnimFlipped[i] = new GreenfootImage(punchAnim[i]);
            punchAnimFlipped[i].mirrorHorizontally();
        }
        
        // Load death images and mirrored versions
        for (int i = 0; i < 5; i++) { 
            deathAnim[i] = new GreenfootImage("Human1_Death" + i + ".png");
            deathAnim[i].scale(130, 80);

            deathAnimFlipped[i] = new GreenfootImage(deathAnim[i]);
            deathAnimFlipped[i].mirrorHorizontally();
        }

        setImage(punchAnim[0]);
        animationTimer.mark();
    }

    public void act() {
        if (!isDead) {
            moveTowardGorilla();
            punchLoop();
            checkGorillaPunch();
        } else {
            playDeathAnimation();
        }
    }
    
    private void moveTowardGorilla() {
        World world = getWorld();
        if (world == null) return;
        java.util.List<Gorilla> gorillas = world.getObjects(Gorilla.class);
        if (gorillas.isEmpty()) return;
        Gorilla gorilla = gorillas.get(0);
        
        // Decide facing direction based on gorilla position
        facingRight = (gorilla.getX() >= getX());

        int dx = gorilla.getX() - getX();
        int dy = gorilla.getY() - getY();
        
        if (Math.abs(dx) > speed) {
            setLocation(getX() + (dx > 0 ? speed : -speed), getY());
        }
        
        if (Math.abs(dy) > speed) {
            setLocation(getX(), getY() + (dy > 0 ? speed : -speed));
        }
    }

    private void punchLoop() {
        if (animationTimer.millisElapsed() > 200) {
            // Use correct image based on facing direction
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
        if (gorilla != null && ((Gorilla) gorilla).isPunching()) {
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
                getWorld().removeObject(this);
            }
        }
    }
}
