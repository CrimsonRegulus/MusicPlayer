public class Director 
{
    private SongBuilder songbuilder;
    
    public void setSongBuilder(SongBuilder sb)
    {
        songbuilder = sb;
    }
    
    public Song getSong()
    {
        return songbuilder.getSong();
    }
    
    public void constructSong(int songid, String album, String title, String artist)
    {
        songbuilder.createNewSong(songid);
        songbuilder.buildAlbum(album);
        songbuilder.buildGenre();
        songbuilder.buildTitle(title);
        songbuilder.buildArtist(artist);
    }
}
