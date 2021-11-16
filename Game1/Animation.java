/**
 * @(#)Animation.java
 *
 *  Lab 5 Starter Code
 *
 *
 * @author Mr May
 * @version 1.00 2016/7/21
 */
import mayflower.*;

public class Animation 
{
    private Picture[] frames;
    private int currentFrame;
    private int frameRate;
    
    public Animation(int rate, String[] filenames) 
    {
        frames = new Picture[filenames.length];
        
        for(int i=0; i < filenames.length; i++)
        {
            int n = i + 1;
            frames[i] = new Picture(filenames[i]);
        }
        
        currentFrame = 0;
        frameRate = rate;
    }
    
    public int getFrameRate()
    {
        return frameRate;
    }
    
    public Picture getNextFrame()
    {
        currentFrame = currentFrame % frames.length;
        Picture ret = frames[currentFrame];
        currentFrame = currentFrame + 1;
        return ret;
    }
    
    public void resize(int w, int h)
    {
        for(Picture p : frames)
        {
            p.resize(w, h);
        }
    }
    
    public void setTransparency(int percent)
    {
        for(Picture p : frames)
        {
            p.setTransparency(percent);
        }
    }
    
    public void flipHorizontal() {
        for(Picture p : frames)
        {
            p.flipHorizontal();
        }
    }
    
    public void setBounds(int x, int y, int w, int h) {
        
    }
    
    public void changeBounds(int x, int y, int w, int h) {
        for (Picture p : frames) {
            p.setBounds(x, y, w, h);
        }
    }
}