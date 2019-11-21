package FF;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class FFView 
{
    public ImageIcon fficon()
    {
        BufferedImage imgff = null;
        
        try {
            imgff = ImageIO.read(getClass().getResource("/Icons/ff.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new ImageIcon(imgff.getScaledInstance(49, 29, Image.SCALE_SMOOTH));
    }
}
