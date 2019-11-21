import java.util.Date;

public class Artist extends User 
{
    public Artist(int userid, String username, String password, String firstname, String lastname, Date date) {
        super(userid,username,password,firstname,lastname,"Artist",date);
    }
}