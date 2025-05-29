import greenfoot.*; 

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
    private HealthBar healthBar;
    private int currentHealth;
    private int maxHealth = 100;

    public Gorilla() {
        currentHealth = maxHealth;
        healthBar = new HealthBar(maxHealth);

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
        if (getWorld() != null && !getWorld().getObjects(HealthBar.class).contains(healthBar)) {
            getWorld().addObject(healthBar, getX(), getY() - 50);
        }
        healthBar.setLocation(getX(), getY() - 50);

        checkPunchKey();
        if (!punching) {
            handleMovement();
        }
        animateGorilla();

        if(getWorld() instanceof Battlefield && getX() <= 0 && getY() <= 80) {
            Greenfoot.setWorld(Shop.instance());
            return;
        }

        if(getWorld() instanceof Shop && getX() >= 599 && getY() >= 350) {
            Greenfoot.setWorld(Battlefield._instance());          
            return;
        }
        
        if(getWorld() instanceof Tutorial && (getX() >= 400 || getX() <= 200)) {
            Tutorial world = (Tutorial) getWorld();
            world.crossed = true;
        }
    
        if (getWorld() instanceof Tutorial && getX() <= 0 && getY() <= 80) {
            Greenfoot.setWorld(Shop.instance());
            return;
        }
    }



    public void checkPunchKey() {
        boolean spaceDown = Greenfoot.isKeyDown("space");

        if (spaceDown && !spacePressedLastFrame && !punching) {
            punching = true;
            imageIndex = 0;
            animationTimer.mark();
        }

        spacePressedLastFrame = spaceDown;
    }


    public void animateGorilla() {
        int delay = punching ? 200 : 100;
        healthBar.setLocation(getX(), getY() - 50);
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
    
    public void updateHealth(int change) {
        currentHealth += change;
        if (currentHealth < 0) currentHealth = 0;
        if (currentHealth > maxHealth) currentHealth = maxHealth;

        healthBar.updateHealth(change);  
        if (currentHealth <= 0) {
            // Gorilla died! 
        }
    }

}
