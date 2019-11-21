package BK;

import javax.sound.sampled.Clip;

public class BKModel
{
    private Clip clip;
    private long pausedTime;
    private boolean isPlaying;
    private boolean isPaused;
    
    public BKModel(Clip clip, boolean isPlaying, boolean isPaused)
    {
        this.clip = clip;
        this.isPlaying = isPlaying;
        this.isPaused = isPaused;
    }
    
    public BKModel(Clip clip, long pausedTime, boolean isPlaying, boolean isPaused)
    {
        this.clip = clip;
        this.pausedTime = pausedTime;
        this.isPlaying = isPlaying;
        this.isPaused = isPaused;
    }
    
    public Clip getclip()
    {
        return this.clip;
    }
    
    public long getpausetime()
    {
        return this.pausedTime;
    }
    
    public boolean getisPlaying()
    {
        return this.isPlaying;
    }
    
    public boolean getisPaused()
    {
        return this.isPaused;
    }
    
    public void setpausetime(long pausetime)
    {
        this.pausedTime = pausetime;
    }
}
