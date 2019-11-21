import java.util.Date;

public class Guest extends User 
{
    public Guest(int userid, Date date) {
        super(userid,"Guest","","Guest","User","Guest",date);
    }
}