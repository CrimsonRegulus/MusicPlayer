package BK;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BKView 
{
    public ImageIcon bkicon()
    {
        BufferedImage imgbk = null;
        
        try {
            imgbk = ImageIO.read(getClass().getResource("/Icons/bk.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ImageIcon(imgbk.getScaledInstance(49, 29, Image.SCALE_SMOOTH));
    }
}
