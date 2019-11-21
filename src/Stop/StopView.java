package Stop;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class StopView 
{
    public ImageIcon stopicon()
    {
        BufferedImage imgstop = null;
        
        try {
            imgstop = ImageIO.read(getClass().getResource("/Icons/stop.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        return new ImageIcon(imgstop.getScaledInstance(80, 29, Image.SCALE_SMOOTH));
    }
}
