import mayflower.*;
/**
 * Write a description of class talkingBatman here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class talkingBatman extends Actor
{

    public talkingBatman()
    {
        
    }
    public void update() {
        setDrawOrder(100);
        setPicture("Images/talk.gif");
    }
}
