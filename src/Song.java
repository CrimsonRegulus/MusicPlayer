import java.util.Comparator;

public class Song
{
    private int songID;
    private Genre genre;
    private String Album;
    private boolean favorite;
    private int timesPlayed;
    private String titlesong;
    private String artistsong;
        
    public Song(int songID){
        this.songID = songID;
        this.favorite = false;
        this.timesPlayed = 0;
    }
    
    public void setGenre(Genre genre){
        this.genre = genre;
    }
    
    public void setAlbum(String album){
        this.Album = album;
    }
    
    public void setTitle(String title){
        this.titlesong = title;
    }
    
    public void setArtist(String artist){
        this.artistsong = artist;
    }
    
    public void setFavorite(){
        this.favorite = true;
    }
    
    public void setUnfavorite(){
        this.favorite = false;
    }
    
    public boolean getfavorite(){
        return this.favorite;
    }
    
    public void setTimesPlayed(int times){
        this.timesPlayed = times;
    }
    
    public void addTimesPlayed(){
        this.timesPlayed++;
    }
    
    public int getTimesPlayed(){
        return this.timesPlayed;
    }
    
    public int getid()
    {
        return this.songID;
    }
    
    public Genre getGenre(){
        return this.genre;
    }
    
    public String getAlbum(){
        return this.Album;
    }
    
    public String getTitle(){
        return this.titlesong;
    }
    
    public String getArtist(){
        return this.artistsong;
    }
    
    public static Comparator<Song> AlbumNameComparator = new Comparator<Song>() {

        @Override
	public int compare(Song an1, Song an2) {
	   String AlbumName1 = an1.getAlbum().toUpperCase();
	   String AlbumName2 = an2.getAlbum().toUpperCase();
	   return AlbumName1.compareTo(AlbumName2);
    }};
    
    public static Comparator<Song> TitleComparator = new Comparator<Song>() {

        @Override
	public int compare(Song tt1, Song tt2) {
	   String Title1 = tt1.getTitle().toUpperCase();
	   String Title2 = tt2.getTitle().toUpperCase();
	   return Title1.compareTo(Title2);
    }};
    
    public static Comparator<Song> ArtistComparator = new Comparator<Song>() {

        @Override
	public int compare(Song ar1, Song ar2) {
	   String Artist1 = ar1.getArtist().toUpperCase();
	   String Artist2 = ar2.getArtist().toUpperCase();
	   return Artist1.compareTo(Artist2);
    }};
}
