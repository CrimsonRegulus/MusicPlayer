
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class showplaylistResult extends javax.swing.JFrame {

    private Dashboard owner;
    private Connection con;
    private User user;
    private String playlistname;
    private ArrayList<Song> queue;
    private String playlistCreator;
    
    
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
    
    public void setPlaylistName(String name){
        this.playlistname = name;
        playlistnameLabel.setText(this.playlistname);
        queue = new ArrayList<>();
    }
    public void playSong(){
        
        try {
            Musicplayer player = new Musicplayer(this,true);
            
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.songdetail NATURAL JOIN databasedc.playlistdc NATURAL JOIN song NATURAL JOIN databasedc.useraccount WHERE PlaylistName = '"+playlistname+"' AND UserName = '"+playlistCreator+"' AND SongTitle = '"+playlistcontentsTable.getValueAt(playlistcontentsTable.getSelectedRow(), 0).toString()+"'");
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
           // queue.clear();
        //    queue.add(song);
            player.setsong(song);
            player.setcon(getcon());
       //     player.setQueue(queue);
            player.songtoplay();
            playlistcontentsTable.clearSelection();
            player.setVisible(true);
            player.stoptimer();
            player.getwav().stopMusic();
          //  owner.setQueue(queue);
        } catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addtoLib(){
        try {
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.songdetail NATURAL JOIN databasedc.playlistdc NATURAL JOIN song NATURAL JOIN databasedc.useraccount WHERE PlaylistName = '"+playlistname+"' AND UserName = '"+playlistCreator+"' AND SongTitle = '"+playlistcontentsTable.getValueAt(playlistcontentsTable.getSelectedRow(), 0).toString()+"'");
            while(resultchecker.next()){
                Statement insertStatement = getcon().createStatement();
                insertStatement.execute("INSERT INTO databasedc.songlibrary(OwnerID, SongID) VALUES('"+getuser().getid()+"', '"+resultchecker.getInt("SongID")+"')");
            }
            owner.loadlibrary();
        } catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public showplaylistResult() {
        initComponents();
        Color theme = new Color(25,25,25);
        jScrollPane1.getViewport().setBackground(theme);
        jScrollPane1.setBorder(BorderFactory.createEmptyBorder());
    }
    public void addtoQueue(){
        try {
            Statement statechecker = getcon().createStatement();
            ResultSet resultchecker = statechecker.executeQuery("SELECT * FROM databasedc.songdetail NATURAL JOIN databasedc.playlistdc NATURAL JOIN song NATURAL JOIN databasedc.useraccount WHERE PlaylistName = '"+playlistname+"' AND UserName = '"+playlistCreator+"'");
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
    
    public void setplaylistcreator(String name){
        this.playlistCreator = name;
    }
    
    public void initialize(){
        if(getuser().gettype().equals("Artist"))
            addtolibButton.setVisible(false);
        try {
            DefaultTableModel model = (DefaultTableModel) playlistcontentsTable.getModel();
            model.setRowCount(0);
            Statement statementgetartist = getcon().createStatement();
            ResultSet resultgetartist= statementgetartist.executeQuery("SELECT * FROM databasedc.playlistdc NATURAL JOIN useraccount WHERE PlaylistName = '"+playlistname+"' AND UserName = '"+playlistCreator+"'");
            
            while(resultgetartist.next()){
                playlistcreatorLabel.setText(resultgetartist.getString("UserName"));
            }
            Statement statement = getcon().createStatement();
            ResultSet result= statement.executeQuery("SELECT * FROM databasedc.songdetail NATURAL JOIN databasedc.playlistdc NATURAL JOIN song NATURAL JOIN databasedc.useraccount WHERE PlaylistName = '"+playlistname+"' AND UserName = '"+playlistCreator+"'");
            
            while(result.next()){
                model.insertRow(playlistcontentsTable.getRowCount(), new Object[]{
                    result.getString("SongTitle"),
                    result.getString("SongArtist"),
                    result.getString("SongGenre"),
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(showplaylistResult.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        playlistnameLabel = new javax.swing.JLabel();
        playlistcreatorLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playlistcontentsTable = new javax.swing.JTable();
        playsongButton = new javax.swing.JLabel();
        addtoqueueButton = new javax.swing.JLabel();
        addtolibButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));

        playlistnameLabel.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        playlistnameLabel.setForeground(new java.awt.Color(255, 255, 255));
        playlistnameLabel.setText("Playlist Name");

        playlistcreatorLabel.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        playlistcreatorLabel.setForeground(new java.awt.Color(255, 255, 255));
        playlistcreatorLabel.setText("Playlist Name");

        playlistcontentsTable.setBackground(new java.awt.Color(25, 25, 25));
        playlistcontentsTable.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        playlistcontentsTable.setForeground(new java.awt.Color(255, 255, 255));
        playlistcontentsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Artist", "Genre"
            }
        ));
        playlistcontentsTable.setShowHorizontalLines(false);
        playlistcontentsTable.setShowVerticalLines(false);
        jScrollPane1.setViewportView(playlistcontentsTable);

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
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(playlistnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(addtolibButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(playlistcreatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playsongButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(addtoqueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 69, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playlistnameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addtolibButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playlistcreatorLabel)
                    .addComponent(addtoqueueButton)
                    .addComponent(playsongButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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

    private void playsongButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playsongButtonMouseClicked
        if(playlistcontentsTable.getSelectedRow() < 0 )
            JOptionPane.showMessageDialog(null, "Please Select a Song First!","No Song Selected",JOptionPane.WARNING_MESSAGE);
        else
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable playlistcontentsTable;
    private javax.swing.JLabel playlistcreatorLabel;
    private javax.swing.JLabel playlistnameLabel;
    private javax.swing.JLabel playsongButton;
    // End of variables declaration//GEN-END:variables
}
