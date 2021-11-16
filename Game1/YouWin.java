import mayflower.*;
import java.awt.Color;
public class YouWin extends Stage
{
    private Text title;  
    private Logo logo;
    boolean pressed = false;
    public YouWin()
    {
        logo = new Logo();
        addActor(logo, 400, 200);
        title = new Text("YOU WIN!", new Color(255, 255, 255), 80, "Helvetica");
        addActor(title, 215, 400);
    }

    public void update() {
        
    }
}