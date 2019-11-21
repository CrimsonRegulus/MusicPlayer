import java.io.File;
import java.sql.Blob;
import java.util.ArrayList;

public class Album 
{
    private ArrayList<Song> album;
    private int albumid;
    private String name;
    private String date;
    private Blob picture;
    private String artist;
    
    public Album(int albumid ,String name, String date,Blob file,String artist){
        album = new ArrayList();
        this.albumid = albumid;
        this.name = name;
        this.date = date;
        this.picture = file;
        this.artist = artist;
    }
    
    public void cleararray()
    {
        album = new ArrayList();
    }
    
    public int getid()
    {
        return this.albumid;
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
        this.album.add(song);
    }
    
    public ArrayList<Song> getSongs(){
        return this.album;
    }
    
    public Blob getFile(){
        return this.picture;
    }
    
    public void setFile(Blob file){
        this.picture = file;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    
    
}
