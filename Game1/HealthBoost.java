import mayflower.*;
/**
 * Write a description of class HealthBoost here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class HealthBoost extends PowerUp
{
    public HealthBoost()
    {
        setPicture("Images/healthBoost.png");
    }
    public void update() {
        super.update();
        setPicture("Images/healthBoost.png");
    }
}
