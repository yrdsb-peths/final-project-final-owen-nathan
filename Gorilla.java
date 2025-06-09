import greenfoot.*;

public class Gorilla extends Actor {
    GreenfootImage[] walkRight = new GreenfootImage[3];
    GreenfootImage[] walkLeft = new GreenfootImage[3];
    GreenfootImage[] punchRight = new GreenfootImage[3];
    GreenfootImage[] punchLeft = new GreenfootImage[3];

    GreenfootSound moneySound = new GreenfootSound("cashier-quotka-chingquot-sound-effect-129698.mp3");
    GreenfootSound punchSound = new GreenfootSound("punch-2-37333.mp3");
    GreenfootSound elephantSound = new GreenfootSound("elephant-trumpets-growls-6047.mp3");
    GreenfootSound healSound = new GreenfootSound("084373_heal-36672.mp3");
    GreenfootSound fireSound = new GreenfootSound("fast-whoosh-118248.mp3");
    GreenfootSound battleSound = new GreenfootSound("battle_horn_1-6931.mp3");
    GreenfootSound deathSound = new GreenfootSound("zombie-3-106344.mp3");
    GreenfootSound firePlaceSound = new GreenfootSound("lighter-click-fire-woosh-326482.mp3");
    
    GreenfootImage grave = new GreenfootImage("images/Zombie_Grave-removebg-preview.png");
        
    String facing = "right";
    SimpleTimer animationTimer = new SimpleTimer();
    boolean punching = false;
    boolean spacePressedLastFrame = false;
    int imageIndex = 0;
    int speed = 4;
    private HealthBar healthBar;
    public static int currentHealth;
    private int maxHealth = 100;
    private static Gorilla instance;
    private boolean hPressed = false;
    private boolean fPressed = false;
    public static boolean isDead = false;
    private int cooldownTimer = 0;
    private boolean onCooldown = false;
    private boolean pPressedLastFrame = false;
    public static boolean isTouchingBomb = false;

    public static boolean dead = false;
    
    private Elephant pet;
    public static boolean hasPet = false;

    private boolean deathSoundPlayed = false;

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
        if (isDead) {
            if (!deathSoundPlayed) {
                deathSound.play();
                deathSoundPlayed = true;
            }
            if (getWorld() != null && getWorld().getObjects(HealthBar.class).contains(healthBar)) {
                getWorld().removeObject(healthBar);
                getWorld().removeObjects(getWorld().getObjects(Human1.class));
                getWorld().removeObjects(getWorld().getObjects(Human2.class));
            }
            grave.scale(60, 60);
            setImage(grave);
            return;
        }

        if (onCooldown) {
            cooldownTimer--;
            if (cooldownTimer <= 0) {
                onCooldown = false;
            }
        }

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

        if (hasPet) updatePetPosition();
    }

    public void checkPunchKey() {
        boolean spaceDown = Greenfoot.isKeyDown("space");

        if (spaceDown && !spacePressedLastFrame && !punching) {
            punchSound.play();
            punching = true;
            imageIndex = 0;
            animationTimer.mark();

            int xOffset = facing.equals("right") ? 50 : -50;
            PunchHitbox hitbox = new PunchHitbox(this);
            getWorld().addObject(hitbox, getX() + xOffset, getY());
        }

        spacePressedLastFrame = spaceDown;
    }

    public void animateGorilla() {
        int delay = punching ? 200 : 50;
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
        
        if ((world instanceof Battlefield || world instanceof Tutorial) && Battlefield.waveStarted == false && getX() <= 0 && getY() <= 80) {
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

        if (world instanceof Shop) {
            if (Greenfoot.isKeyDown("h")) {
                if (!hPressed && Currency.coins >= 10) {
                    moneySound.play();
                    healSound.play();
                    Currency.coins -= 10;
                    setHealth(maxHealth);
                    hPressed = true;
                }
            } else {
                hPressed = false;
            }

            if (Greenfoot.isKeyDown("p")) {
                if (Currency.coins >= 50 && !hasPet) {
                    moneySound.play();
                    elephantSound.play();
                    Currency.coins -= 50;
                    givePet();
                    hasPet = true;
                }
            }

            if (Greenfoot.isKeyDown("f")) {
                if (!fPressed && Currency.coins >= 30) {
                    moneySound.play();
                    fireSound.play();
                    Currency.coins -= 30;
                    FireCounter.fireTraps++;
                    fPressed = true;
                }
            } else {
                fPressed = false;
            }
        }

        if (world instanceof Battlefield) {
            if (Greenfoot.isKeyDown("p")) {
                if (!pPressedLastFrame && !onCooldown && Battlefield.waveStarted && hasPet) {
                    placeTrap();
                    onCooldown = true;
                    cooldownTimer = 300;
                    elephantSound.play();
                }
                pPressedLastFrame = true;
            } else {
                pPressedLastFrame = false;
            }
        
            if (Greenfoot.isKeyDown("f")) {
                if (!fPressed && FireCounter.fireTraps > 0) {
                    firePlaceSound.play();
                    placeTrap();
                    FireCounter.fireTraps--;
                    fPressed = true;
                }
            } else {
                fPressed = false;
            }
        }
    }
    
    public void updateHealth(int change) {
        currentHealth = Math.max(0, Math.min(maxHealth, currentHealth + change));
        healthBar.updateHealth(change);
    
        if (currentHealth <= 0 && !isDead) {
            die();
            dead = true;
        }
    }

    public void die() {
        isDead = true;
        punching = false;
        imageIndex = 0;
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

    public void givePet() {
        if (!hasPet) {
            pet = new Elephant();
            hasPet = true;
            if (getWorld() != null) {
                getWorld().addObject(pet, getX() - 40, getY() + 30);
            }
        }
    }

    public void updatePetPosition() {
        if (getWorld() != null && pet != null) {
            if (!getWorld().getObjects(Elephant.class).contains(pet)) {
                getWorld().addObject(pet, getX() - 40, getY() + 30);
            } else {
                pet.setLocation(getX() - 40, getY() + 30);
            }
        }
    }

    public boolean isPunching() {
        return punching;
    }
    
    public static void resetInstance() {
        instance = null;
    }
    
    public void knockBack(int strength) {
        // Example: push Gorilla back along X axis by 'strength' pixels
        // Adjust direction as needed depending on your game logic
        int newX = getX() - strength; 
        setLocation(newX, getY());
    }
    
    public String getFacing() {
        return facing;
    }

    public void setFacing(String direction) {
        facing = direction;
    }

    public void applyKnockback(int dx, int dy) {
        setLocation(getX() + dx, getY() + dy);
    }

    public void setPunching(boolean punching) {
        this.punching = punching;
    }
}
