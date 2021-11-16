import mayflower.*;
import java.awt.Color;
public class GameOver extends Stage
{
    private Text title;  
    private Logo logo;
    private Logo deadBats;
    boolean pressed = false;
    public GameOver()
    {
        deadBats = new Logo();
        deadBats.setPicture("Images/dead.gif");
        addActor(deadBats, 400, 200);
        
        title = new Text("GAME OVER", new Color(255, 255, 255), 80, "Helvetica");
        addActor(title, 130, 400);
    }

    public void update() {
        Keyboard kb = getKeyboard();
        if (kb.isKeyPressed("Enter")) {
            if (!pressed) {
                getMayflower().setStage(new Cutscene1(3,0));
                pressed = true;
            }
        }
        
    }
}