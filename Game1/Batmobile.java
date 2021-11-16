import mayflower.*;
/**
 * Write a description of class Batmobile here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Batmobile extends Actor
{
    public Batmobile()
    {
        
    }

    public void update() {
        setDrawOrder(200);
        setPicture("Images/batmobile.gif");
    }
}
