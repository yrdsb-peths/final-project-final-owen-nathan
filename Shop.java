import greenfoot.*;

// Shop where players buy things

public class Shop extends World {
    private static Shop _instance = null;
    private World prevWorld;
    Label2 fireLabel;
    Label2 coinLabel;
    Label2 HP = new Label2("HP\u219210", 25);
    Label2 PET = new Label2("PET\u219240", 25);
    Label2 FIRE = new Label2("Fire Trap\u219230", 25);
    Label2 descriptionHP = new Label2("Restores full vitality", 20);
    Label2 descriptionPET = new Label2("Press P on", 20);
    Label2 descriptionPET2 = new Label2("battlefield and may help...", 20);
    Label2 descriptionFIRE = new Label2("Trap to damage tier 1s", 20);
    Label2 descriptionFIRE2 = new Label2("F to deploy", 20);
    Label2 buyH = new Label2("Press H to Buy", 25);
    Label2 buyP = new Label2("Press P to Buy", 25);
    Label2 buyF = new Label2("Press F to Buy", 25);

    public static Shop instance() {
        if (_instance == null) {
            _instance = new Shop();
        }
        return _instance;
    }

    private Shop() {
        super(600, 400, 1);
        setup();
    }

    public void setPreviousWorld(World world) {
        this.prevWorld = world;

        Gorilla gorilla = Gorilla.getInstance();

        // Remove Gorilla from old world if needed
        World currentWorld = gorilla.getWorld();
        if (currentWorld != null) {
            currentWorld.removeObject(gorilla);
        }

        addObject(gorilla, 550, 350);
        gorilla.updateHealthBarPosition();
    }

    public void act() {
        coinLabel.setValue("" + Currency.getCoins());
        fireLabel.setValue("" + FireCounter.getTraps());
    }

    private void setup() {
        Sword sword = new Sword();
        addObject(sword, 575, 370);

        coinLabel = new Label2("" + Currency.getCoins(), 40);
        addObject(coinLabel, 50, 360);
        
        FireCount fire2 = new FireCount();
        addObject(fire2, 90, 360);
        
        fireLabel = new Label2("" + FireCounter.getTraps(), 40);
        addObject(fireLabel, 120, 360);
        
        addObject(new Coin(), 15, 362);
        addObject(new Coin(), 132, 72);
        addObject(new Coin(), 332, 72);
        addObject(new Coin(), 555, 72);

        addObject(new Sale(), 300, 30);

        Health hp = new Health();
        addObject(hp, 95, 135);
        addObject(HP, 90, 70);
        addObject(descriptionHP, 95, 260);
        addObject(buyH, 95, 205);

        Fire fire = new Fire();
        addObject(fire, 495, 135);
        addObject(FIRE, 485, 70);
        addObject(descriptionFIRE, 495, 258);
        addObject(descriptionFIRE2, 495, 273);
        addObject(buyF, 495, 205);

        Pet pet = new Pet();
        addObject(pet, 295, 135);
        addObject(PET, 285, 70);
        addObject(descriptionPET, 295, 258);
        addObject(descriptionPET2, 295, 272);
        addObject(buyP, 295, 205);
    }
}
