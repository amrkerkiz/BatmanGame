import mayflower.*;
/**
 * Write a description of class ParallaxBackground here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ParallaxBackground extends Actor
{
    // instance variables - replace the example below with your own
    private Picture bg;

    /**
     * Constructor for objects of class ParallaxBackground
     */
    public ParallaxBackground()
    {
        bg = new Picture("Images/extraDark.gif");
        //System.out.println("Background dimensions: "+bg.getWidth()+" * "+bg.getHeight());
        setPicture(bg);
    }
    
    public void update() {
        setDrawOrder(1);
    }
}
