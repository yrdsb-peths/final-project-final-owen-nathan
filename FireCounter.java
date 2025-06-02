/**
 * Write a description of class FireCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireCounter {
    public static int fireTraps = 0;

    public static void addTrap(int amount) {
        fireTraps += amount;
    }

    public static void subtractTrap(int amount) {
        if (fireTraps >= amount) {
            fireTraps -= amount;
        }
    }

    public static int getTraps() {
        return fireTraps;
    }

    public static void setTraps(int amount) {
        fireTraps = amount;
    }
}

