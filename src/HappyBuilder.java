public class HappyBuilder extends SongBuilder
{
    @Override
    public void buildAlbum(String album)
    {
        song.setAlbum(album);
    }
    
    @Override
    public void buildGenre()
    {
        song.setGenre(new Happy());
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
