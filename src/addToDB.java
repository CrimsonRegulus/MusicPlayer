import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class addToDB 
{
    private Connection con;
    SimpleDateFormat datefor = new SimpleDateFormat("yyyy-MM-dd");
    
    public void setcon(Connection con)
    {
        this.con = con;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    
    /*
    public void addSong(File file, String artist, String title, int userid)
    {
        String INSERT_SONG = "INSERT INTO databasedc.song(SongBlob,SongTitle,SongArtist,UserID,UploadedDate) VALUES(?,?,?,?,?)";
        try (FileInputStream fis = new FileInputStream(file);
                    PreparedStatement ps = getcon().prepareStatement(INSERT_SONG)) {
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.setString(2, title);
            ps.setString(3, artist);
            ps.setInt(4, userid);
            ps.setString(5, datefor.format(new Date()));
            ps.executeUpdate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void addSong(File file, String artist, String title, int userid, String gen)
    {
        String INSERT_SONG = "INSERT INTO databasedc.song(SongBlob,SongTitle,SongArtist,SongGenre,UserID,UploadedDate) VALUES(?,?,?,?,?,?)";
        try (FileInputStream fis = new FileInputStream(file);
                    PreparedStatement ps = getcon().prepareStatement(INSERT_SONG)) {
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.setString(2, title);
            ps.setString(3, artist);
            ps.setString(4, gen);
            ps.setInt(5, userid);
            ps.setString(6, datefor.format(new Date()));
            ps.executeUpdate();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addToDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
