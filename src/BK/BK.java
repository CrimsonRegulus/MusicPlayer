package BK;

import FF.FFModel;
import Pause.PauseModel;
import Play.PlayModel;
import Stop.StopModel;

public class BK 
{
    public BKView bkv = new BKView();     
    private FFModel ffm;       
    private PauseModel pausem;
    private PlayModel playm;  
    private StopModel stopm;
    
    public void bkMusic(BKModel bk){
        if(bk.getisPlaying()){
            if(bk.getclip().getMicrosecondPosition()<5000000){
                //pausedTime = this.clip.getMicrosecondPosition();
                bk.getclip().setMicrosecondPosition(0);
                
                if(!bk.getisPaused())
                    bk.getclip().start();
                else
                    bk.setpausetime(0);
            }
            else{
                bk.setpausetime(bk.getclip().getMicrosecondPosition());
                bk.getclip().setMicrosecondPosition(bk.getpausetime()-5000000);
                
                if(!bk.getisPaused())
                    bk.getclip().start();
                else
                    bk.setpausetime(bk.getpausetime() - 5000000);
            }
            this.pausem = new PauseModel(bk.getclip(), bk.getpausetime(), bk.getisPlaying(), bk.getisPaused());
            this.playm = new PlayModel(bk.getclip(), bk.getpausetime(), bk.getisPlaying(), bk.getisPaused());
            this.ffm = new FFModel(bk.getclip(), bk.getpausetime(), bk.getisPlaying(), bk.getisPaused());
            this.stopm = new StopModel(bk.getclip(), bk.getpausetime(), bk.getisPlaying(), bk.getisPaused());
        }
    }
    
    public PauseModel getpm()
    {
        return this.pausem;
    }
    
    public PlayModel getplm()
    {
        return this.playm;
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
