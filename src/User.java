import java.util.Date;

public abstract class User 
{
    private final int userid;
    private final String username;
    private final String password;
    private final String firstname;
    private final String lastname;
    private final String type;
    private final Date date;
    
    public User(int userid, String username, String password, String firstname, String lastname, String type, Date date)
    {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.type = type;
        this.date = date;
    }
    
    public int getid()
    {
        return this.userid;
    }
    
    public String getusername()
    {
        return this.username;
    }
    
    public String getpassword()
    {
        return this.password;
    }
    
    public String getfirstname()
    {
        return this.firstname;
    }
    
    public String getlastname()
    {
        return this.lastname;
    }
    
    public String gettype()
    {
        return this.type;
    }
    
    public Date getdate()
    {
        return this.date;
    }
}
