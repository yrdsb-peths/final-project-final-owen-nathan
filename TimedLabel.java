import greenfoot.*;

public class TimedLabel extends Actor {
    private int timer; // frames to stay alive

    public TimedLabel(String text, int fontSize, int durationFrames) {
        this.timer = durationFrames;

        // Match Label's rendering style
        Color fillColor = Color.WHITE;
        Color lineColor = Color.WHITE;
        Color transparent = new Color(0, 0, 0, 0);

        GreenfootImage image = new GreenfootImage(text, fontSize, fillColor, transparent, lineColor);
        setImage(image);
    }

    public void act() {
        timer--;
        if (timer <= 0) {
            getWorld().removeObject(this);
        }
    }
}
