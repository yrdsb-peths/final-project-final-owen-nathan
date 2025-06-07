import greenfoot.*;

public class Battlefield extends World {
    Label scoreLabel;
    Label waveLabel;
    Label coinLabel;
    Label fireLabel;
    Cart cart = new Cart();
    
    int waveNumber = 1;
    public boolean ready = false; 
    boolean waveAnnounced = false;
    boolean coinsGiven = false;
    public static boolean waveStarted = false;
    
    private static Battlefield instance_;
    
    Label start = new Label("Press enter to face enemies", 30);
    
    public static Battlefield _instance() {
        if(instance_ == null) {
            instance_ = new Battlefield();
        }
        return instance_;
    }
    
    private Battlefield() {
        super(600, 400, 1);
        ScoreKeeper.score = 1;
        
        GreenfootImage worldBG = new GreenfootImage("images/dgggoyk-fdd28b15-79e9-4a3d-a8bd-d7d966e77900.jpg");
        worldBG.scale(600, 400);
        setBackground(worldBG);
        
        addObject(cart, 25, 40);

        addObject(Gorilla.getInstance(), 50, 50);
        
        scoreLabel = new Label(0, 60);
        addObject(scoreLabel, 550, 60);
        
        coinLabel = new Label("" + Currency.getCoins(), 40);
        addObject(coinLabel, 50, 360);
        
        Coin coin = new Coin();
        addObject(coin, 15, 362);
        
        FireCount fire = new FireCount();
        addObject(fire, 90, 360);
        
        fireLabel = new Label("" + FireCounter.getTraps(), 40);
        addObject(fireLabel, 120, 360);
        
        announceWave();
        waveAnnounced = true;
        prepare();
    }
    
    public void prepare() {
        Gorilla gorilla = Gorilla.getInstance();

        if (gorilla.getWorld() != null) {
            gorilla.getWorld().removeObject(gorilla);
        }

        addObject(gorilla, 50, 50);
        gorilla.updateHealthBarPosition();
    }
    
    public void act() {
        scoreLabel.setValue(ScoreKeeper.score);
        coinLabel.setValue("" + Currency.getCoins()); // also fix: use setValue(String)
        fireLabel.setValue("" + FireCounter.getTraps());
        if (waveCleared() && ready) {
            if (!coinsGiven) {
                giveWaveReward(waveNumber);
                coinsGiven = true;
            }
    
            ready = false;
            waveNumber++;
            announceWave();
            waveAnnounced = true;
        }
    
        if (!ready && waveAnnounced && Greenfoot.isKeyDown("enter")) {
            if (waveLabel != null) {
                waveStarted = true;
                removeObject(waveLabel);
                removeObject(start);
                removeObject(cart);
            }
            startWave(waveNumber);
            ready = true;
            waveAnnounced = false;
            coinsGiven = false; // Reset so next wave reward can trigger
        }
    }

    private Gorilla gorilla;

    public Gorilla getGorilla() {
        return gorilla;
    }
    
    public void spawnHuman(int x, int y) {
        Human1 h1 = new Human1();
        addObject(h1, x, y);
    }
    
    public void spawnHuman2(int x, int y) {
        Human2 h2 = new Human2();
        addObject(h2, x, y);
    }
    
    public void startWave(int num) {
        switch(num) {
            case 1: wave1(); break;
            case 2: wave2(); break;
            case 3: wave3(); break;
            case 4: wave4(); break;
            case 5: wave5(); break;
            case 6: wave6(); break;
            case 7: wave7(); break;
            default: break;
        }
    }
    
    public void wave1() {
        Battlefield world = this;
        //total enemies: 6
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(30, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(90, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }

    
    public void wave2() {
        Battlefield world = this;    
        //total enemies: 12
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(180, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(240, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner2(300, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);

    }
    
    public void wave3() {
        Battlefield world = this;
        //total enemies: 20
        world.addObject(new DelayedSpawner2(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }
    
    public void wave4() {
        Battlefield world = this;
        //total enemies: 30
        world.addObject(new DelayedSpawner(0, 100, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 300, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 100, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 200, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 100, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 300, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 350), 0, 0);
        world.addObject(new DelayedSpawner(0, 400, 200), 0, 0);
    }
    
    public void wave5() {
        Battlefield world = this;
        //total enemies: 35
        world.addObject(new DelayedSpawner2(0, 50, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 150, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 250, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 350, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 450, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 550, 50), 0, 0);
    }
    
    public void wave6() {
        Battlefield world = this;
        //total enemies: 60
        world.addObject(new DelayedSpawner(0, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(60, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(120, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(180, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(240, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(300, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(360, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(420, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(480, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(540, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(600, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(660, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(720, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(780, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(840, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(900, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(960, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1020, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1080, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1140, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1200, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1260, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1320, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1380, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
        world.addObject(new DelayedSpawner(1340, Greenfoot.getRandomNumber(600), Greenfoot.getRandomNumber(400)), 0, 0);
    }
    
    public void wave7() {
        Battlefield world = this;
        //total enemies: 75
        world.addObject(new DelayedSpawner(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner(0, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner(60, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner(60, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner(60, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner(60, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner(60, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(120, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 350), 0, 0);
    }
    
    public void wave8() {
        Battlefield world = this;
        //total enemies: 90
        world.addObject(new DelayedSpawner2(0, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(0, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(0, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(0, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(0, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(60, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(60, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(60, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(60, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(60, 500, 350), 0, 0);
        
        world.addObject(new DelayedSpawner2(120, 500, 50), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 125), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 200), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 275), 0, 0);
        world.addObject(new DelayedSpawner2(120, 500, 350), 0, 0);
    }
    
    public boolean waveCleared() {
        if (waveNumber == 1) return ScoreKeeper.score >= 6;
        if (waveNumber == 2) return ScoreKeeper.score >= 12;
        if (waveNumber == 3) return ScoreKeeper.score >= 20;
        if (waveNumber == 4) return ScoreKeeper.score >= 30;
        if (waveNumber == 5) return ScoreKeeper.score >= 35;
        if (waveNumber == 6) return ScoreKeeper.score >= 60;
        if (waveNumber == 7) return ScoreKeeper.score >= 75;
        if (waveNumber == 8) return ScoreKeeper.score >= 90;
        return false;
    }

    public void giveWaveReward(int wave) {
        if (wave == 1) Currency.addCoins(10);
        if (wave == 2) Currency.addCoins(20);
        if (wave == 3) Currency.addCoins(20);//total coins: 60
        if (wave == 4) Currency.addCoins(30);
        if (wave == 5) Currency.addCoins(40);//total coins: 130
        if (wave == 6) Currency.addCoins(60);
        if (wave == 7) Currency.addCoins(80);
        if (wave == 8) Currency.addCoins(100);//total coins: 370
    }

    
    public void announceWave() {
        waveLabel = new Label("Wave " + waveNumber, 60);
        addObject(waveLabel, getWidth() / 2, getHeight() / 2);
        addObject(start, 300, 250);
        addObject(cart, 25, 40);
        waveStarted = false;
    }

    public void increaseScore() {
        ScoreKeeper.score++;
        scoreLabel.setValue(ScoreKeeper.score);
    }
}
