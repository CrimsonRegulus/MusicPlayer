public class SentiBuilder extends SongBuilder 
{
    @Override
    public void buildAlbum(String album)
    {
        song.setAlbum(album);
    }
    
    @Override
    public void buildGenre()
    {
        song.setGenre(new Senti());
    }
    
    @Override
    public void buildTitle(String title)
    {
        song.setTitle(title);
    }
    
    @Override
    public void buildArtist(String artist)
    {
        song.setArtist(artist);
    }
}
