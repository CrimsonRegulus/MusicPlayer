public abstract class SongBuilder 
{
    protected Song song;
    
    public Song getSong()
    {
        return song;
    }
    
    public void createNewSong(int songid)
    {
        song = new Song(songid);
    }
    
    public abstract void buildAlbum(String album);
    public abstract void buildGenre();
    public abstract void buildTitle(String title);
    public abstract void buildArtist(String artist);
}
