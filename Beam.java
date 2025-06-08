import greenfoot.*;
import greenfoot.Color;

public class Beam extends Actor {
    private int speed = 5;
    private boolean facingRight;

    public Beam(boolean facingRight) {
        this.facingRight = facingRight;
        // Simple rectangle as beam
        GreenfootImage img = new GreenfootImage(30, 6);
        img.setColor(new Color(173, 216, 230)); // Light Blue
        img.fillRect(0, 0, 30, 6);
        setImage(img);
    }

    public void act() {
        if (facingRight) {
            setLocation(getX() + speed, getY());
        } else {
            setLocation(getX() - speed, getY());
        }
    
        if (getX() < 0 || getX() > getWorld().getWidth()) {
            getWorld().removeObject(this);
            return;
        }
    
        Gorilla gorilla = (Gorilla) getOneIntersectingObject(Gorilla.class);
        if (gorilla != null) {
            gorilla.updateHealth(-5);
            getWorld().removeObject(this);
        }
    }

}
