import greenfoot.*;

public class Asteroid extends Actor {
    public static int x = 0;
    
    public void act() {
        setLocation(getX(), getY() + 3);

        if (Greenfoot.isKeyDown("left")) {
            move(-2);
        } else if (Greenfoot.isKeyDown("right")) {
            move(2);
        }

        // If we're in Cutscene1 and asteroid reaches bottom, transition to MyWorld
        if (getWorld() instanceof Cutscene1 && getY() >= 399) {
            x = getX(); // Save the x-position
            getWorld().removeObject(this);
            Greenfoot.setWorld(new MyWorld());
            return;
        }

        // Spawn gorilla and remove asteroid at Y >= 200
        if (getWorld() instanceof MyWorld && getY() >= 200) {
            MyWorld world = (MyWorld) getWorld();
            world.landed = true;

            int asteroidX = getX(); // save X before removing the asteroid

            Gorilla gorilla = new Gorilla();
            Boom boom = new Boom(asteroidX, 200);
            world.addObject(boom, asteroidX, 200);

            world.removeObject(this); // now safe to remove
            return;
        }
    }

    public Asteroid() {
        GreenfootImage asteroid = new GreenfootImage("images/asteroid.png");
        asteroid.scale(40, 40);
        setImage(asteroid);
    }
}
