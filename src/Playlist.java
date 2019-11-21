import java.util.ArrayList;

public class Playlist 
{
    private ArrayList<Song> playlist;
    private int playlistid;
    private String name;
    private String date;
    
    public Playlist(int playlistid ,String name, String date){
        playlist = new ArrayList();
        this.playlistid = playlistid;
        this.name = name;
        this.date = date;
    }
    
    public void cleararray()
    {
        playlist = new ArrayList();
    }
    
    public int getid()
    {
        return this.playlistid;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void changeName(String name){
        this.name = name;
    }
    
    public String getdate()
    {
        return this.date;
    }
    
    public void addSong(Song song){
        this.playlist.add(song);
    }
    
    public ArrayList<Song> getSongs(){
        return this.playlist;
    }
}
