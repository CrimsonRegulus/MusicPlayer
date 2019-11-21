package Play;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PlayView 
{
    public ImageIcon playicon()
    {
        BufferedImage imgplay = null;
        
        try {
            imgplay = ImageIO.read(getClass().getResource("/Icons/play.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        return new ImageIcon(imgplay.getScaledInstance(61, 29, Image.SCALE_SMOOTH));
    }
}
