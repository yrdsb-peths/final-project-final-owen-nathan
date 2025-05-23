import greenfoot.*;

public class Asteroid extends Actor {
    public static int x = 0;
    
    public void act() {
        setLocation(getX(), getY() + 3);

        if (Greenfoot.isKeyDown("a")) {
            move(-2);
        } else if (Greenfoot.isKeyDown("d")) {
            move(2);
        }

        if(getWorld() instanceof Cutscene1 && getY() >= 399) {
            x = getX(); 
            getWorld().removeObject(this);
            Greenfoot.setWorld(new CutsceneWorld());
            return;
        }

        if(getWorld() instanceof CutsceneWorld && getY() >= 200) {
            CutsceneWorld world = (CutsceneWorld) getWorld();
            world.landed = true;

            int asteroidX = getX(); 

            Gorilla gorilla = new Gorilla();
            Boom boom = new Boom(asteroidX, 200);
            world.addObject(boom, asteroidX, 200);

            world.removeObject(this); 
            return;
        }
    }

    public Asteroid() {
        GreenfootImage asteroid = new GreenfootImage("images/ship.png");
        asteroid.scale(60, 60);
        setImage(asteroid);
    }
}
