import mayflower.*;
/**
 * Write a description of class HealthBar here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HealthBar extends Actor
{
    private Picture health;
    private boolean notHealth;
    private int newSize;
    private int oldSize;
    public HealthBar()
    {
        health = new Picture("Images/health.png");
        health.resize(300, 40);
        setPicture(health);
        notHealth = false;
        newSize = 0;
        oldSize = 320;
    }
    public void setToCover() {
        health = new Picture("Images/shield.png");
        health.resize(320, 70);
        setPicture(health);
        notHealth = true;
        //health.setTransparency(50);
    }
    public void update() {
        
    }
    public void changeHealth(int healthNum) {
        if (healthNum > 0) {
            newSize = (300*healthNum)/100;
            move((oldSize-newSize)/2, "West");
            health.resize(newSize, 40);
            setPicture(health);
            oldSize = newSize;
        }
    }
}
