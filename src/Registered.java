import java.util.Date;

public class Registered extends User 
{
    public Registered(int userid, String username, String password, String firstname, String lastname, Date date) {
        super(userid,username,password,firstname,lastname,"Registered",date);
    }
}