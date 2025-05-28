import greenfoot.*;

public class HealthBar extends Actor {
    private int maxHealth;
    private int currentHealth;

    private int width = 100;
    private int height = 15;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        updateImage();
    }

    public void act() {
        
    }


    public void updateHealth(int change) {
        currentHealth += change;
        if (currentHealth < 0) currentHealth = 0;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
        updateImage();
    }

    private void updateImage() {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.BLACK);
        img.drawRect(0, 0, width - 1, height - 1);
        
        img.setColor(Color.RED);
        int healthWidth = (int)((double)currentHealth / maxHealth * (width - 2));
        img.fillRect(1, 1, healthWidth, height - 2);

        setImage(img);
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
}
