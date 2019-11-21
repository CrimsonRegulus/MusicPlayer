package Pause;

import BK.BKModel;
import FF.FFModel;
import Play.PlayModel;
import Stop.StopModel;

public class Pause 
{
    public PauseView pausev = new PauseView();
    private BKModel bkm;      
    private FFModel ffm;       
    private PlayModel playm;  
    private StopModel stopm;
    
    public void pauseMusic(PauseModel pause){
        if(pause.getclip() != null){
            pause.setpausetime(pause.getclip().getMicrosecondPosition()); 
            pause.getclip().stop();
            pause.setisPlaying(false);
            pause.setisPaused(true);
            this.playm = new PlayModel(pause.getclip(), pause.getpausetime(), pause.getisPlaying(), pause.getisPaused());
            this.bkm = new BKModel(pause.getclip(), pause.getpausetime(), pause.getisPlaying(), pause.getisPaused());
            this.ffm = new FFModel(pause.getclip(), pause.getpausetime(), pause.getisPlaying(), pause.getisPaused());
            this.stopm = new StopModel(pause.getclip(), pause.getpausetime(), pause.getisPlaying(), pause.getisPaused());
        }
    }
    
    public PlayModel getplm()
    {
        return this.playm;
    }
    
    public BKModel getbk()
    {
        return this.bkm;
    }
    
    public FFModel getff()
    {
        return this.ffm;
    }
    
    public StopModel getsm()
    {
        return this.stopm;
    }
}
