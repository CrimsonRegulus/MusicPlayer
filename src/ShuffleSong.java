public class ShuffleSong 
{
    private int tableid;
    private String title;
    private String artist;
    
    public ShuffleSong(int id, String title, String artist)
    {
        this.tableid = id;
        this.title = title;
        this.artist = artist;
    }
    
    public int getid()
    {
        return this.tableid;
    }
    
    public String gettitle()
    {
        return this.title;
    }
    
    public String getartist()
    {
        return this.artist;
    }
}
