package Stop;

import BK.BKModel;
import FF.FFModel;
import Pause.PauseModel;
import Play.PlayModel;

public class Stop 
{
    public StopView stopv = new StopView();
    private BKModel bkm;      
    private FFModel ffm;       
    private PauseModel pausem;
    private PlayModel playm;  
    
    public void stopMusic(StopModel stop)
    {
        stop.setpausetime(0);
        stop.getclip().stop();
        stop.setisPlaying(false);
        stop.setisPaused(true);
        this.pausem = new PauseModel(stop.getclip(), stop.getpausetime(), stop.getisPlaying(), stop.getisPaused());
        this.playm = new PlayModel(stop.getclip(), stop.getpausetime(), stop.getisPlaying(), stop.getisPaused());
        this.bkm = new BKModel(stop.getclip(), stop.getpausetime(), stop.getisPlaying(), stop.getisPaused());
        this.ffm = new FFModel(stop.getclip(), stop.getpausetime(), stop.getisPlaying(), stop.getisPaused());
    }
    
    public PauseModel getpm()
    {
        return this.pausem;
    }
    
    public BKModel getbk()
    {
        return this.bkm;
    }
    
    public FFModel getff()
    {
        return this.ffm;
    }
    
    public PlayModel getplm()
    {
        return this.playm;
    }
}
