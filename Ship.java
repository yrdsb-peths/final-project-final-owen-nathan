import greenfoot.*;

public class Ship extends Actor {
    public static int x = 0;
    
    public void act() {
        setLocation(getX(), getY() + 2);

        if (Greenfoot.isKeyDown("a")) {
            move(-2);
        } else if (Greenfoot.isKeyDown("d")) {
            move(2);
        }

        if(getWorld() instanceof Cutscene1 && getY() >= 399) {
            x = getX(); 
            getWorld().removeObject(this);
            Greenfoot.setWorld(new Tutorial());
            return;
        }

        if(getWorld() instanceof Tutorial && getY() >= 200) {
            Tutorial world = (Tutorial) getWorld();
            world.landed = true;

            int asteroidX = getX(); 

            Gorilla gorilla = new Gorilla();
            Boom boom = new Boom(asteroidX, 200);
            world.addObject(boom, asteroidX, 200);

            world.removeObject(this); 
            return;
        }
    }

    public Ship() {
        GreenfootImage asteroid = new GreenfootImage("images/ship.png");
        asteroid.scale(60, 60);
        setImage(asteroid);
    }
}
