import greenfoot.*;

public class Asteroid extends Actor
{
    public static int x = 0;

    public void act()
    {
        setLocation(getX(), getY() + 3);

        if(Greenfoot.isKeyDown("left")) {
            move(-2);
        } else if(Greenfoot.isKeyDown("right")) {
            move(2);
        }

        if (getWorld() instanceof Cutscene1 && getY() >= 399) {
            x = getX();
            getWorld().removeObject(this);
            Greenfoot.setWorld(new MyWorld());
        }
    }

    public Asteroid() {
        GreenfootImage asteroid = new GreenfootImage("images/asteroid.png");
        asteroid.scale(40, 40);
        setImage(asteroid);
    }
}
