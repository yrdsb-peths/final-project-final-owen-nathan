import greenfoot.*; 

//This is our hero that the player plays

public class Gorilla extends Actor
{
    GreenfootImage[] walkRight = new GreenfootImage[3];
    GreenfootImage[] walkLeft = new GreenfootImage[3];
    GreenfootImage[] punchRight = new GreenfootImage[3];
    GreenfootImage[] punchLeft = new GreenfootImage[3];

    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    boolean punching = false;
    boolean spacePressedLastFrame = false;
    int imageIndex = 0;
    int speed = 4;

    public Gorilla() {
        for (int i = 0; i < 3; i++) {
            walkRight[i] = new GreenfootImage("Gorilla_Walk" + i + ".png");
            walkRight[i].scale(80, 80);

            walkLeft[i] = new GreenfootImage("Gorilla_Walk" + i + ".png");
            walkLeft[i].mirrorHorizontally();
            walkLeft[i].scale(80, 80);

            punchRight[i] = new GreenfootImage("Gorilla_Punch" + i + ".png");
            punchRight[i].scale(110, 80);

            punchLeft[i] = new GreenfootImage("Gorilla_Punch" + i + ".png");
            punchLeft[i].mirrorHorizontally();
            punchLeft[i].scale(110, 80);
        }

        animationTimer.mark();
        setImage(walkRight[0]);
    }

    public void act() {
        checkPunchKey();
        if (!punching) {
            handleMovement();
        }
        animateGorilla();

        if(getWorld() instanceof Battlefield && getX() <= 0 && getY() <= 80) {
            Greenfoot.setWorld(new Shop());
            return;
        }

        if(getWorld() instanceof Shop && getX() >= 599 && getY() >= 350) {
            Greenfoot.setWorld(new Battlefield());
            return;
        }
        
        if(getWorld() instanceof Tutorial && (getX() >= 400 || getX() <= 200)) {
            Tutorial world = (Tutorial) getWorld();
            world.crossed = true;
        }

        if (getWorld() instanceof Tutorial && getX() <= 0 && getY() <= 80) {
            Greenfoot.setWorld(new Shop());
            return;
        }
    }



    public void checkPunchKey() {
        boolean spaceDown = Greenfoot.isKeyDown("space");

        // Punch cannot be started the last frame and start again
        if (spaceDown && !spacePressedLastFrame && !punching) {
            punching = true;
            imageIndex = 0;
            animationTimer.mark();
        }

        spacePressedLastFrame = spaceDown;
    }


    public void animateGorilla() {
        //If punching have a longer delay
        int delay = punching ? 200 : 100;
        if (animationTimer.millisElapsed() < delay) {
            return;
        }
        animationTimer.mark();

        if (punching) {
            if (facing.equals("right")) {
                setImage(punchRight[imageIndex]);
            } else {
                setImage(punchLeft[imageIndex]);
            }

            imageIndex++;
            if (imageIndex >= punchRight.length) {
                punching = false;
                imageIndex = 0;

                // Reset to idle frame
                if (facing.equals("right")) {
                    setImage(walkRight[0]);
                } else {
                    setImage(walkLeft[0]);
                }
            }
        } else {
            if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d") ||
                Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("s")) {

                if (facing.equals("right")) {
                    setImage(walkRight[imageIndex]);
                } else {
                    setImage(walkLeft[imageIndex]);
                }

                imageIndex = (imageIndex + 1) % walkRight.length;
            } else {
                imageIndex = 0;
                if (facing.equals("right")) {
                    setImage(walkRight[0]);
                } else {
                    setImage(walkLeft[0]);
                }
            }
        }
    }

    public void handleMovement() {
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - speed, getY());
            facing = "left";
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + speed, getY());
            facing = "right";
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - speed);
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX(), getY() + speed);
        }
    }
    
    public boolean isPunching() {
    return punching;
}
}
