import mayflower.*;
/**
 * Write a description of class Static here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Static extends Actor
{
    private Picture s;
    public Static()
    {
        s = new Picture("Images/static.gif");
    }
    public void update() {
        setDrawOrder(1111);
        setPicture(s);
    }
}
