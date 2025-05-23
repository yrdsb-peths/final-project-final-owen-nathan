import greenfoot.*;

public class MyWorld extends World {
    public Label wasdLabel;

    public MyWorld() {
        super(600, 400, 1);

        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        Cart cart = new Cart();
        addObject(cart, 25, 40);
        
        Gorilla gorilla = new Gorilla();
        addObject(gorilla, 50, 50);
    }
}
