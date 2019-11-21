package FF;

import BK.BKModel;
import Pause.PauseModel;
import Play.PlayModel;
import Stop.StopModel;

public class FF 
{
    public FFView ffv = new FFView();
    private BKModel bkm;            
    private PauseModel pausem;
    private PlayModel playm;  
    private StopModel stopm;
    
    public void ffMusic(FFModel ff){
        if(ff.getisPlaying()){
            ff.setpausetime(ff.getclip().getMicrosecondPosition());
            ff.getclip().setMicrosecondPosition(ff.getpausetime()+5000000);
            if(ff.getclip().getMicrosecondPosition() >= ff.getclip().getMicrosecondLength()){
                ff.setpausetime(0);
                ff.getclip().setMicrosecondPosition(ff.getclip().getMicrosecondLength());
                ff.getclip().stop();
            }
            else{
                if(!ff.getisPaused())
                   ff.getclip().start();
                else
                    ff.setpausetime(ff.getpausetime()+5000000);
            }
            this.pausem = new PauseModel(ff.getclip(), ff.getpausetime(), ff.getisPlaying(), ff.getisPaused());
            this.playm = new PlayModel(ff.getclip(), ff.getpausetime(), ff.getisPlaying(), ff.getisPaused());
            this.bkm = new BKModel(ff.getclip(), ff.getpausetime(), ff.getisPlaying(), ff.getisPaused());
            this.stopm = new StopModel(ff.getclip(), ff.getpausetime(), ff.getisPlaying(), ff.getisPaused());
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
    
    public PlayModel getplm()
    {
        return this.playm;
    }
    
    public StopModel getsm()
    {
        return this.stopm;
    }
}
