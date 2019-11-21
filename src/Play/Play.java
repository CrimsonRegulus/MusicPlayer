package Play;

import BK.*;
import FF.*;
import Pause.*;
import Stop.*;

public class Play 
{
    public PlayView playv = new PlayView();
    private BKModel bkm;      
    private FFModel ffm;       
    private PauseModel pausem; 
    private StopModel stopm; 
    
    public void playMusic(PlayModel play) {
        
        if(play.getclip() != null){
            play.getclip().setMicrosecondPosition(play.getpausetime());
            play.getclip().start();
            play.setisPlaying(true);
            play.setisPaused(false);
            this.pausem = new PauseModel(play.getclip(), play.getisPlaying(), play.getisPaused());
            this.bkm = new BKModel(play.getclip(), play.getisPlaying(), play.getisPaused());
            this.ffm = new FFModel(play.getclip(), play.getisPlaying(), play.getisPaused());
            this.stopm = new StopModel(play.getclip(), play.getisPlaying(), play.getisPaused());
        }
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
    
    public StopModel getsm()
    {
        return this.stopm;
    }
}
