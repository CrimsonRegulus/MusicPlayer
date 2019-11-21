
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darren
 */
public class showsongresult extends javax.swing.JFrame {

    private Dashboard owner;
    private Connection con;
    private User user;
    private String songname;
    private ArrayList<Song> queue;
    private String artist;
    
    public void addtoLib(){
        try {
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.song WHERE SongTitle = '"+songname+"' AND SongArtist = '"+artist+"'");
            while(resultchecker.next()){
                Statement insertStatement = getcon().createStatement();
                insertStatement.execute("INSERT INTO databasedc.songlibrary(OwnerID, SongID) VALUES('"+getuser().getid()+"', '"+resultchecker.getInt("SongID")+"')");
            }
            owner.loadlibrary();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null,"Song is already in Library");
        }
        catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void setartist(String artist){
        this.artist = artist;
    }
    
    public void settitle(String title){
        this.songname  = title;
    }
    
    public void setcon(Connection con)
    {
        this.con = con;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    public void setuser(User user)
    {
        this.user = user;
    }
    
    public User getuser()
    {
        return this.user;
    }
    
    public void setOwner(Dashboard owner){
        this.owner = owner;
    }
    
    public Dashboard getOwner(){
        return this.owner;
    }
    
    public void setQueue(ArrayList<Song> queue){
        this.queue = queue;
    }
    
    
    public void playSong(){
        
        try {
            Musicplayer player = new Musicplayer(this,true);
            
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.song WHERE SongTitle = '"+songname+"' AND SongArtist = '"+artist+"'");
            Song song = null;
            while(resultchecker.next()){
                String genre = resultchecker.getString("SongGenre");
                Director build = new Director();
                
                if(genre.equals("Happy")){
                    build.setSongBuilder(new HappyBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else if(genre.equals("Sad")){
                    build.setSongBuilder(new SadBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else if(genre.equals("Senti")){
                    build.setSongBuilder(new SentiBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else{
                    build.setSongBuilder(new NoBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                song = build.getSong();
            }
            //queue.clear();
            //queue.add(song);
            player.setsong(song);
            player.setcon(getcon());
           // player.setQueue(queue);
            player.songtoplay();
            player.setVisible(true);
            player.stoptimer();
            player.getwav().stopMusic();
            //owner.setQueue(queue);
        } catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public showsongresult() {
        initComponents();
    }
    public void addtoQueue(){
        try {
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.song WHERE SongTitle = '"+songname+"' AND SongArtist = '"+artist+"'");
            Song song = null;
            
            while(resultchecker.next()){
                String genre = resultchecker.getString("SongGenre");
                Director build = new Director();
                
                if(genre.equals("Happy")){
                    build.setSongBuilder(new HappyBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else if(genre.equals("Sad")){
                    build.setSongBuilder(new SadBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else if(genre.equals("Senti")){
                    build.setSongBuilder(new SentiBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                else{
                    build.setSongBuilder(new NoBuilder());
                    build.constructSong(resultchecker.getInt("SongID"), "", resultchecker.getString("SongTitle").trim(), resultchecker.getString("SongArtist").trim());
                }
                song = build.getSong();
                queue.add(song);
            }
            owner.setQueue(queue);
        } catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void initialize(){
        if(getuser().gettype().equals("Artist"))
            addtolibButton.setVisible(false);
        songtitlelabel.setText(songname);
        songartistlabel.setText(artist);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        songtitlelabel = new javax.swing.JLabel();
        playsongButton = new javax.swing.JLabel();
        addtoqueueButton = new javax.swing.JLabel();
        songartistlabel = new javax.swing.JLabel();
        addtolibButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));

        songtitlelabel.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        songtitlelabel.setForeground(new java.awt.Color(255, 255, 255));
        songtitlelabel.setText("Title");

        playsongButton.setBackground(new java.awt.Color(51, 51, 51));
        playsongButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        playsongButton.setForeground(new java.awt.Color(255, 255, 255));
        playsongButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playsongButton.setText("Play");
        playsongButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        playsongButton.setOpaque(true);
        playsongButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playsongButtonMouseClicked(evt);
            }
        });

        addtoqueueButton.setBackground(new java.awt.Color(51, 51, 51));
        addtoqueueButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        addtoqueueButton.setForeground(new java.awt.Color(255, 255, 255));
        addtoqueueButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addtoqueueButton.setText("Add to Queue");
        addtoqueueButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addtoqueueButton.setOpaque(true);
        addtoqueueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addtoqueueButtonMouseClicked(evt);
            }
        });

        songartistlabel.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        songartistlabel.setForeground(new java.awt.Color(255, 255, 255));
        songartistlabel.setText("Artist");

        addtolibButton.setBackground(new java.awt.Color(51, 51, 51));
        addtolibButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        addtolibButton.setForeground(new java.awt.Color(255, 255, 255));
        addtolibButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addtolibButton.setText("Add to Library");
        addtolibButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addtolibButton.setOpaque(true);
        addtolibButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addtolibButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(songartistlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(songtitlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(playsongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addtoqueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addtolibButton, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(songtitlelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addtolibButton))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(songartistlabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(playsongButton)
                            .addComponent(addtoqueueButton))))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playsongButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playsongButtonMouseClicked
            playSong();
    }//GEN-LAST:event_playsongButtonMouseClicked

    private void addtoqueueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addtoqueueButtonMouseClicked
       addtoQueue();
    }//GEN-LAST:event_addtoqueueButtonMouseClicked

    private void addtolibButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addtolibButtonMouseClicked
       addtoLib();
    }//GEN-LAST:event_addtolibButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced playlistcontentsTable SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(showplaylistResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showplaylistResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showplaylistResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showplaylistResult.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new showplaylistResult().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addtolibButton;
    private javax.swing.JLabel addtoqueueButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel playsongButton;
    private javax.swing.JLabel songartistlabel;
    private javax.swing.JLabel songtitlelabel;
    // End of variables declaration//GEN-END:variables
}
