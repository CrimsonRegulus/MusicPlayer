import java.sql.Connection;

public interface Edit 
{
    public void setcon(Connection con);
    public Connection getcon();
    public void edit();
}
