import java.sql.Connection;
import java.util.ArrayList;

public class CLPlay 
{
    private User user;
    private Connection con;
    private ArrayList<Playlist> playlist;
    private Playlist play;
    
    public CLPlay(Connection con, User user, ArrayList<Playlist> playlist)
    {
        this.con = con;
        this.user = user;
        this.playlist = playlist;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    
    public User getuser()
    {
        return this.user;
    }
    
    public void setplay(Playlist play)
    {
        this.play = play;
    }
    
    public Playlist getplay()
    {
        return this.play;
    }
    
    public ArrayList<Playlist> getplaylist()
    {
        return this.playlist;
    }
}
