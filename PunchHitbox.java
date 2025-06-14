import greenfoot.*;

//Red square that acts as the hitbox for the gorilla
//Instead of checking for intersections with the gorilla, it checks for intersections between the gorilla and enemys

public class PunchHitbox extends Actor {
    private int lifespan = 20; // frames to exist before disappearing
    private int age = 0;
    private Gorilla owner;
    //Owner is just used to locate the Gorilla that spawned the hitbox
    
    public PunchHitbox(Gorilla owner) {
        this.owner = owner;
        GreenfootImage img = new GreenfootImage(40, 30);
        img.setColor(new greenfoot.Color(255, 0, 0, 100)); // translucent red
        img.fill();
        setImage(img);
    }
    
    //Determines how long the hitbox should last
    public void act() {
        age++;
        if (age >= lifespan) {
            getWorld().removeObject(this);
            return;
        }
        checkHit();
    }

    private void checkHit() {
        // Damage Human1
        Human1 human1 = (Human1) getOneIntersectingObject(Human1.class);
        if (human1 != null && !human1.isDead()) {
            human1.takeDamage();
            getWorld().removeObject(this);
            return;
        }

        // Damage Human2
        Human2 human2 = (Human2) getOneIntersectingObject(Human2.class);
        if (human2 != null && !human2.isDead()) {
            human2.takeDamage();
            getWorld().removeObject(this);
            return;
        }

        // Damage FinalBoss and knock back Gorilla owner
        FinalBoss boss = (FinalBoss) getOneIntersectingObject(FinalBoss.class);
        if (boss != null) {
            boss.takeDamage();

            int knockbackAmount = 300; // Adjust this for distance pushed
            if (owner.getFacing().equals("right")) {
                owner.applyKnockback(-knockbackAmount, 0); // Knockback left
            } else {
                owner.applyKnockback(-knockbackAmount, 0);  // Knockback right
            }

            getWorld().removeObject(this);
        }
    }
}
