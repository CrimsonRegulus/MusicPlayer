package tester;

public abstract class Genre
{
    public String type;
    
    public Genre(String type)
    {
        this.type = type;
    }
    
    public String gettype()
    {
        return this.type;
    }
}
