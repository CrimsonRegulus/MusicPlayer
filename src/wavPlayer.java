import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import BK.*;
import FF.*;
import Pause.*;
import Play.*;
import Stop.*;
import javax.swing.ImageIcon;

public class wavPlayer{
    private File soundFile;
    private Clip clip;
    private long pausedTime;
    private int FrameCount;
    private boolean isPlaying = false;
    private boolean isPaused = false;
    private ShuffleSong song;
    private ArrayList<ShuffleSong> ss = new ArrayList<>();
    
    private BK bk;
    private FF ff;
    private Pause pause;
    private Play play;
    private Stop stop;
    public BKModel bkm;      
    public FFModel ffm;       
    public PauseModel pausem;
    public PlayModel playm;  
    public StopModel stopm;  
    
    public wavPlayer()
    {
        this.bk = new BK();
        this.ff = new FF();
        this.pause = new Pause();
        this.play = new Play();
        this.stop = new Stop();
    }
    
    public void setshuf(ArrayList<ShuffleSong> arr)
    {
        this.ss = arr;
    }
    
    public ArrayList<ShuffleSong> getshuf()
    {
        return this.ss;
    }

    public void setsong(int song)
    {
        this.song = ss.get(song);
    }
    
    public ShuffleSong getsong()
    {
        return this.song;
    }

    public int getFrameCount() {
        return FrameCount;
    }

    public void setFrameCount(int FrameCount) {
        this.FrameCount = FrameCount;
    }

    public Clip getClip()
    {
        return this.clip;
    }
    
    public void setPausedTime(long pausedtime)
    {
        this.pausedTime = pausedtime;
    }
    
    public void setFile(File file) throws LineUnavailableException, UnsupportedAudioFileException, IOException{
        this.soundFile = file;
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        this.clip = AudioSystem.getClip();
        this.clip.open(audioIn);
        FrameCount  = (int) audioIn.getFrameLength();
    }
    
    public void start()
    {
        this.pausem = new PauseModel(this.clip, this.pausedTime, this.isPlaying, this.isPaused);
        this.playm = new PlayModel(this.clip, this.pausedTime, this.isPlaying, this.isPaused);
        this.bkm = new BKModel(this.clip, this.pausedTime, this.isPlaying, this.isPaused);
        this.ffm = new FFModel(this.clip, this.pausedTime, this.isPlaying, this.isPaused);
        this.stopm = new StopModel(this.clip, this.pausedTime, this.isPlaying, this.isPaused);
    }
    
    public ImageIcon seticonplay()
    {
        return play.playv.playicon();
    }
    
    public ImageIcon seticonpause()
    {
        return pause.pausev.pauseicon();
    }
    
    public ImageIcon seticonstop()
    {
        return stop.stopv.stopicon();
    }
    
    public ImageIcon seticonbk()
    {
        return bk.bkv.bkicon();
    }
    
    public ImageIcon seticonff()
    {
        return ff.ffv.fficon();
    }
    
    public void setplaymod(PlayModel plm)
    {
        this.playm = plm;
    }
    
    public void setpausemod(PauseModel pm)
    {
        this.pausem = pm;
    }
    
    public void setstopmod(StopModel sm)
    {
        this.stopm = sm;
    }
    
    public void setffmod(FFModel ffm)
    {
        this.ffm = ffm;
    }
    
    public void setbkmod(BKModel bkm)
    {
        this.bkm = bkm;
    }
    
    public void playMusic()
    {
        play.playMusic(this.playm);
        setpausemod(play.getpm());
        setstopmod(play.getsm());
        setffmod(play.getff());
        setbkmod(play.getbk());
    }
    
    public void pauseMusic()
    {
        pause.pauseMusic(this.pausem);
        setplaymod(pause.getplm());
        setstopmod(pause.getsm());
        setffmod(pause.getff());
        setbkmod(pause.getbk());
    }
    
    public void stopMusic()
    {
        stop.stopMusic(this.stopm);
        setpausemod(stop.getpm());
        setplaymod(stop.getplm());
        setffmod(stop.getff());
        setbkmod(stop.getbk());
    }
    
    public void bkMusic()
    {
        bk.bkMusic(this.bkm);
        setpausemod(bk.getpm());
        setstopmod(bk.getsm());
        setffmod(bk.getff());
        setplaymod(bk.getplm());
    }
    
    public void ffMusic()
    {
        ff.ffMusic(this.ffm);
        setpausemod(ff.getpm());
        setstopmod(ff.getsm());
        setplaymod(ff.getplm());
        setbkmod(ff.getbk());
    }
    
    public void repeatLoop() {
        if(this.clip.getFramePosition() >= this.FrameCount){
            this.clip.setFramePosition(0);
            pausedTime = 0;
            this.playm = new PlayModel(this.clip, 0, this.isPlaying, this.isPaused);
            this.playMusic();
        }
    }

    public void shuffleloop()
    {
        Random rand = new Random();
        int n = rand.nextInt(ss.size());
        setsong(n);
    }
    
    public void slidermove(long time)
    {
        this.playm = new PlayModel(this.clip, time, this.isPlaying, this.isPaused);
    }
}