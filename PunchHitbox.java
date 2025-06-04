import greenfoot.*;

public class PunchHitbox extends Actor {
    private int lifespan = 20; // frames to exist before disappearing
    private int age = 0;

    public PunchHitbox() {
        GreenfootImage img = new GreenfootImage(50, 30);
        img.setColor(new Color(255, 0, 0, 100)); // translucent red
        img.fill();
        setImage(img);
    }

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
        }
    }
}
