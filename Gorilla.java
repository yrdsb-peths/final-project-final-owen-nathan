import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Gorilla extends Actor
{
    GreenfootImage[] idleRight = new GreenfootImage[3];
    GreenfootImage[] idleLeft = new GreenfootImage[3];

    // Direction the gorilla is facing
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();

    int imageIndex = 0;
    int speed = 4;

    public Gorilla() {
        for (int i = 0; i < 3; i++) {
            idleRight[i] = new GreenfootImage("Gorilla_Walk" + i + ".png");
            idleRight[i].scale(80, 80);
        }

        for (int i = 0; i < idleLeft.length; i++) {
            idleLeft[i] = new GreenfootImage("Gorilla_Walk" + i + ".png");
            idleLeft[i].mirrorHorizontally();
            idleLeft[i].scale(80, 80);
        }

        animationTimer.mark();
        setImage(idleRight[0]);
    }

    public void act() {
        handleMovement();
        animateGorilla();

        if (getWorld() instanceof MyWorld && getX() <= 0 && getY() <= 80) {
            Greenfoot.setWorld(new Shop());
            return;
        }
    }


    public void animateGorilla() {
        if (animationTimer.millisElapsed() < 100) {
            return;
        }
        animationTimer.mark();
        if (facing.equals("right")) {
            setImage(idleRight[imageIndex]);
        } else {
            setImage(idleLeft[imageIndex]);
        }
        imageIndex = (imageIndex + 1) % idleRight.length;
    }

    public void handleMovement() {
        boolean moved = false;

        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
            facing = "left";
            moved = true;
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
            facing = "right";
            moved = true;
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
            moved = true;
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
            moved = true;
        }

        if (!moved) {
            // Optional: pause animation if not moving
            imageIndex = 0;
            if (facing.equals("right")) {
                setImage(idleRight[0]);
            } else {
                setImage(idleLeft[0]);
            }
        }
    }
}
