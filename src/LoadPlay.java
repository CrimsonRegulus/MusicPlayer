import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class LoadPlay extends CLPlay
{
    private String playlistnamevalue;
    
    public LoadPlay(Connection con, User user, ArrayList<Playlist> playlist)
    {
        super(con,user,playlist);
    }
    
    public void setplaylistname(String name)
    {
        this.playlistnamevalue = name;
    }
    
    public String getplaylistname()
    {
        return this.playlistnamevalue;
    }
    
    public void loadplay()
    {
        for(int x=0; x<getplaylist().size(); x++)
        {
            if(getplaylist().get(x).getName().equals(getplaylistname()))
            {
                setplay(getplaylist().get(x));
                break;
            }
        }
        
        JOptionPane.showMessageDialog(null, "Load Successful!");
    }
}
