import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Musicplayer extends javax.swing.JDialog {

    private int progress;
    private Connection con;
    private Song song;
    private boolean isrep;
    private wavPlayer test = new wavPlayer();
    private boolean isFinishedPlaying = false;
    private ArrayList<Song> queue;
    private Dashboard dashboard;
    
    ActionListener songListener = new ActionListener() {
        long minute;
        long second;
        
        @Override
        public void actionPerformed(ActionEvent e) {
            minute = test.getClip().getMicrosecondPosition()/60000000;
            second = test.getClip().getMicrosecondPosition()/1000000;
            progress = (int)(((double)test.getClip().getFramePosition() / (double) test.getFrameCount()) * 100);
            
            if(second >= 60)
                second -= 60 * minute;
                
            if(second < 10){
                lblStartTime.setText(String.valueOf(minute) + ":0" + String.valueOf(second));
                sldrSound.setValue(progress);                    
            }
            else{
                lblStartTime.setText(String.valueOf(minute) + ":" + String.valueOf(second));
                sldrSound.setValue(progress);                
            }
            
            if(getrep())
            {
                test.repeatLoop();
                yesloop();
            }
            else
                noloop();
            
            if(progress == 100)
                isFinishedPlaying = true;
        }  
    };
    
    private Timer timeCounter = new Timer(200, songListener);
    
    public Musicplayer(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        timeCounter.setRepeats(true);
        sldrSound.setValue(0);
        isrep = false;
        othericon();
        playicon();
        noloop();
        
    }
    
    public void yesloop()
    {
        BufferedImage imgloop = null;
        
        try {
            imgloop = ImageIO.read(getClass().getResource("/Icons/loop.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btnLoop.setIcon(new ImageIcon(imgloop.getScaledInstance(81, 29, Image.SCALE_SMOOTH)));
    }
    
    public void noloop()
    {
        BufferedImage imgnoloop = null;
        
        try {
            imgnoloop = ImageIO.read(getClass().getResource("/Icons/noloop.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btnLoop.setIcon(new ImageIcon(imgnoloop.getScaledInstance(81, 29, Image.SCALE_SMOOTH)));
    }
    
    public wavPlayer getwav()
    {
        return this.test;
    }

    public boolean isIsFinishedPlaying() {
        return isFinishedPlaying;
    }

    public void setIsFinishedPlaying(boolean isFinishedPlaying) {
        this.isFinishedPlaying = isFinishedPlaying;
    }

    public ArrayList<Song> getQueue() {
        return queue;
    }

    public void setQueue(ArrayList<Song> queue) {
        this.queue = queue;
    }

    public Dashboard getDashboard() {
        return dashboard;
    }

    public void setDashboard(Dashboard dashboard) {
        this.dashboard = dashboard;
    }
    
    
    
    public void setcon(Connection con)
    {
        this.con = con;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    
    public void setsong(Song song)
    {
        this.song = song;
    }
    
    public Song getsong()
    {
        return this.song;
    }
    
    public void isRepeat(boolean rep)
    {
        this.isrep = rep;
    }
    
    public boolean getrep()
    {
        return this.isrep;
    }
    
    public void stoptimer()
    {
        test.stopMusic();
        timeCounter.stop();
    }
    
    public void pauseicon()
    {     
        btnPlay.setIcon(test.seticonpause());
    } 
    
    public void playicon()
    {       
        btnPlay.setIcon(test.seticonplay());
    }
    
    public void othericon()
    {
        BufferedImage imgshu = null;
        
        try {
            imgshu = ImageIO.read(getClass().getResource("/Icons/shuffle.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        btnStop.setIcon(test.seticonstop());
        btnFF.setIcon(test.seticonff());
        btnBK.setIcon(test.seticonbk());
        btnShuffle.setIcon(new ImageIcon(imgshu.getScaledInstance(80, 29, Image.SCALE_SMOOTH)));
    }
    
    public void songtoplay()
    {
        //for(int i = 0; i < queue.size(); i++){
        //   setsong(queue.get(i));
            
            try {
                File temp = new File("temp.wav");
                Blob blob = null;
                try {
                    Statement state = getcon().createStatement();
                    ResultSet result = state.executeQuery("SELECT * FROM databasedc.song WHERE SongID = '"+getsong().getid()+"'");
                    while(result.next())
                    {
                        blob = result.getBlob("SongBlob");
                    }
                     byte [] array = blob.getBytes( 1, ( int ) blob.length() );
                     InputStream in = blob.getBinaryStream();
                     OutputStream out = new FileOutputStream(temp);
                     int len = 0;

                    while ((len = in.read(array)) != -1) {
                        out.write(array, 0, len);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
                }

                if(blob != null){
                    System.out.println("HELLO NAKAPASOK AKOOOOOOOOO!!!!");
                    test.setFile(temp);
                    lblName.setText(getsong().getTitle());
                    lblArtist.setText(getsong().getArtist());
                    lblStartTime.setText("0:00");
                    lblStopTime.setText(String.valueOf(test.getClip().getMicrosecondLength()/60000000) + ":" + String.valueOf((test.getClip().getMicrosecondLength() - ((test.getClip().getMicrosecondLength()/60000000) * 60000000))/1000000));
                    test.start();
                }
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          //  while(!isFinishedPlaying){
        //        System.out.print("");
       //     }
            
         //   queue.remove(i);
         //   dashboard.setQueue(queue);
        //}
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnStop = new javax.swing.JButton();
        btnFF = new javax.swing.JButton();
        btnBK = new javax.swing.JButton();
        btnLoop = new javax.swing.JButton();
        lblName = new javax.swing.JLabel();
        lblStartTime = new javax.swing.JLabel();
        lblStopTime = new javax.swing.JLabel();
        sldrSound = new javax.swing.JSlider(0, 100);
        btnPlay = new javax.swing.JButton();
        lblArtist = new javax.swing.JLabel();
        btnShuffle = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Player", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Sitka Text", 2, 22))); // NOI18N

        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnFF.setEnabled(false);
        btnFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFFActionPerformed(evt);
            }
        });

        btnBK.setEnabled(false);
        btnBK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBKActionPerformed(evt);
            }
        });

        btnLoop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoopActionPerformed(evt);
            }
        });

        lblName.setFont(new java.awt.Font("Tempus Sans ITC", 1, 24)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblStopTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        sldrSound.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sldrSoundMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sldrSoundMouseReleased(evt);
            }
        });

        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        lblArtist.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        lblArtist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnShuffle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShuffleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(btnBK, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFF, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblStopTime, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sldrSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnLoop, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShuffle, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArtist, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(sldrSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblStartTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblStopTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFF, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnLoop, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(btnStop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnShuffle, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBKActionPerformed
        if(test.getClip() != null) {
            test.bkMusic();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBKActionPerformed

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        if(test.playm.getclip() != null) {
            if(test.playm.getisPlaying())
            {
                test.pauseMusic();
                btnBK.setEnabled(false);
                btnFF.setEnabled(false);
                playicon();
            }
            else if(test.playm.getisPaused())
            {
                test.playMusic();
                btnBK.setEnabled(true);
                btnFF.setEnabled(true);
                timeCounter.start();
                pauseicon();
            }
            else
            {
                test.playMusic();
                btnBK.setEnabled(true);
                btnFF.setEnabled(true);
                timeCounter.start();
                pauseicon();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if(test.stopm.getclip() != null) {
            test.stopMusic();
            timeCounter.stop();
            sldrSound.setValue(0);
            lblStartTime.setText("0:00");
            btnBK.setEnabled(false);
            btnFF.setEnabled(false);
            playicon();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnLoopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoopActionPerformed
        if(test.getClip() != null) {
            if(!getrep())
                isRepeat(true);
            else{
                isRepeat(false);
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnLoopActionPerformed

    private void btnFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFFActionPerformed
        if(test.getClip() != null) {
            test.ffMusic();
        }
        else{
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFFActionPerformed

    private void sldrSoundMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldrSoundMouseReleased
        try{
            progress = sldrSound.getValue();
            double frame = (double) test.getFrameCount() * ((double) progress / 100.0);


            test.getClip().setFramePosition((int)frame);
            test.slidermove(test.getClip().getMicrosecondPosition());

            timeCounter.start();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Please open a file first before playing!", "Error!", JOptionPane.ERROR_MESSAGE);
            sldrSound.setValue(0);
        }
    }//GEN-LAST:event_sldrSoundMouseReleased

    private void sldrSoundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sldrSoundMousePressed
        timeCounter.stop();
    }//GEN-LAST:event_sldrSoundMousePressed

    private void btnShuffleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShuffleActionPerformed
        test.stopMusic();
        test.shuffleloop();
        try {
            Statement stateshuf = getcon().createStatement();
            ResultSet resultshuf = stateshuf.executeQuery("SELECT * FROM databasedc.songdetail NATURAL JOIN databasedc.song WHERE SongTitle = '"+test.getsong().gettitle()+"' && SongArtist = '"+test.getsong().getartist()+"'");
            Director build = new Director();
            while(resultshuf.next())
            {
                build.setSongBuilder(new NoBuilder());
                build.constructSong(resultshuf.getInt("SongID"), resultshuf.getString("AlbumName"), test.getsong().gettitle(), test.getsong().getartist());
                setsong(build.getSong());
            }
        songtoplay();
        test.playMusic();
        
        } catch (SQLException ex) {
            Logger.getLogger(Musicplayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnShuffleActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Musicplayer dialog = new Musicplayer(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBK;
    private javax.swing.JButton btnFF;
    private javax.swing.JButton btnLoop;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnShuffle;
    private javax.swing.JButton btnStop;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblArtist;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblStartTime;
    private javax.swing.JLabel lblStopTime;
    private javax.swing.JSlider sldrSound;
    // End of variables declaration//GEN-END:variables
}
