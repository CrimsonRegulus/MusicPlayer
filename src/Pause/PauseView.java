package Pause;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PauseView 
{
    public ImageIcon pauseicon()
    {
        BufferedImage imgpause = null;
        
        try {
            imgpause = ImageIO.read(getClass().getResource("/Icons/pause.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        return new ImageIcon(imgpause.getScaledInstance(61, 29, Image.SCALE_SMOOTH));
    }
}
