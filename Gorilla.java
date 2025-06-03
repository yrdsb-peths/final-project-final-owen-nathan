import greenfoot.*;

public class Gorilla extends Actor {
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
    private static Gorilla instance;
    private boolean hPressed = false;
    private boolean pPressed = false;
    private boolean fPressed = false;

    public static Gorilla getInstance() {
        if (instance == null) {
            instance = new Gorilla();
        }
        return instance;
    }

    private Gorilla() {
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
        updateHealthBarPosition();

        checkPunchKey();
        if (!punching) {
            handleMovement();
        }
        animateGorilla();
        handleShopAndWorldLogic();
    }

    public void checkPunchKey() {
        boolean spaceDown = Greenfoot.isKeyDown("space");

        if (spaceDown && !spacePressedLastFrame && !punching) {
            punching = true;
            imageIndex = 0;
            animationTimer.mark();

            int xOffset = facing.equals("right") ? 40 : -40;
            PunchHitbox hitbox = new PunchHitbox();
            getWorld().addObject(hitbox, getX() + xOffset, getY());
        }

        spacePressedLastFrame = spaceDown;
    }

    public void animateGorilla() {
        int delay = punching ? 200 : 100;
        if (animationTimer.millisElapsed() < delay) return;
        animationTimer.mark();

        if (punching) {
            setImage(facing.equals("right") ? punchRight[imageIndex] : punchLeft[imageIndex]);
            imageIndex++;

            if (imageIndex >= punchRight.length) {
                punching = false;
                imageIndex = 0;
                setImage(facing.equals("right") ? walkRight[0] : walkLeft[0]);
            }
        } else {
            if (Greenfoot.isKeyDown("a") || Greenfoot.isKeyDown("d") || Greenfoot.isKeyDown("w") || Greenfoot.isKeyDown("s")) {
                setImage(facing.equals("right") ? walkRight[imageIndex] : walkLeft[imageIndex]);
                imageIndex = (imageIndex + 1) % walkRight.length;
            } else {
                imageIndex = 0;
                setImage(facing.equals("right") ? walkRight[0] : walkLeft[0]);
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

    public void handleShopAndWorldLogic() {
        World world = getWorld();

        if ((world instanceof Battlefield || world instanceof Tutorial) && getX() <= 0 && getY() <= 80) {
            Shop shop = Shop.instance();
            shop.setPreviousWorld(world);
            Greenfoot.setWorld(shop);
            return;
        }

        if (world instanceof Shop && getX() >= 599 && getY() >= 350) {
            Battlefield battlefield = Battlefield._instance();
            if (!battlefield.getObjects(Gorilla.class).contains(this)) {
                battlefield.addObject(this, 50, 50);
                updateHealthBarPosition();
            }
            Greenfoot.setWorld(battlefield);
            return;
        }

        if (world instanceof Tutorial && (getX() >= 400 || getX() <= 200)) {
            ((Tutorial) world).crossed = true;
        }

        // Shop upgrades
        if (world instanceof Shop) {
            if (Greenfoot.isKeyDown("h")) {
                if (!hPressed && Currency.coins >= 10) {
                    Currency.coins -= 10;
                    setHealth(maxHealth);
                    hPressed = true;
                }
            } else {
                hPressed = false;
            }

            if (Greenfoot.isKeyDown("p")) {
                if (!pPressed && Currency.coins >= 50) {
                    Currency.coins -= 50;
                    pPressed = true;
                }
            } else {
                pPressed = false;
            }

            if (Greenfoot.isKeyDown("f")) {
                if (!fPressed && Currency.coins >= 20) {
                    Currency.coins -= 20;
                    FireCounter.fireTraps++;
                    fPressed = true;
                }
            } else {
                fPressed = false;
            }
        }

        if (Greenfoot.isKeyDown("f") && FireCounter.fireTraps > 0) {
            placeTrap();
            FireCounter.fireTraps--;
        }
    }

    public void updateHealth(int change) {
        currentHealth = Math.max(0, Math.min(maxHealth, currentHealth + change));
        healthBar.updateHealth(change);
    }

    public void setHealth(int value) {
        currentHealth = value;
        healthBar.setHealth(value);
    }

    public void updateHealthBarPosition() {
        if (getWorld() != null && healthBar != null) {
            if (!getWorld().getObjects(HealthBar.class).contains(healthBar)) {
                getWorld().addObject(healthBar, getX(), getY() - 50);
            }
            healthBar.setLocation(getX(), getY() - 50);
        }
    }

    public void placeTrap() {
        Trap trap = new Trap();
        getWorld().addObject(trap, getX(), getY());
    }
}
