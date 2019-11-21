
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
public class specificalbumwindow extends javax.swing.JFrame {
    private Album album;
    private ArrayList<Album> albums;
    private Dashboard owner;
    private ArrayList<Song> queue;
    
    private Connection connection;
    public specificalbumwindow() {
        initComponents();
    }
    public void setQueue(ArrayList<Song> queue){
        this.queue = queue;
    }
    public void initialize(){
        
         try {
            albumnamelabel.setText(this.album.getName());
            albumartist.setText(this.album.getArtist());
            BufferedImage albumimage = null;
            File image = new File("Album Cover");
            Blob imageBlob = album.getFile();
             try {
                 InputStream in = imageBlob.getBinaryStream();
             } catch (SQLException ex) {
                 Logger.getLogger(specificalbumwindow.class.getName()).log(Level.SEVERE, null, ex);
             }
            OutputStream out = new FileOutputStream(image);
            byte[] bytes = null;
             try {
                 bytes = imageBlob.getBytes(1,(int)imageBlob.length());
             } catch (SQLException ex) {
                 Logger.getLogger(specificalbumwindow.class.getName()).log(Level.SEVERE, null, ex);
             }
            out.write(bytes);
            albumimage = ImageIO.read(image);
            albumimagelabel.setIcon(new ImageIcon(albumimage.getScaledInstance(64, 64,Image.SCALE_SMOOTH)));
            DefaultTableModel modelp = (DefaultTableModel) albumsongsTable.getModel();
            modelp.setRowCount(0);
            for(int i = 0 ; i < album.getSongs().size(); i++ ){
                modelp.insertRow(albumsongsTable.getRowCount(), new Object[]{
                   album.getSongs().get(i).getTitle(),
                   album.getSongs().get(i).getGenre().getGenre()
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(specificalbumwindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playsong(){
        Musicplayer player = new Musicplayer(this,true);
        
        for(int i = 0; i < album.getSongs().size(); i++){
            if(album.getSongs().get(i).getTitle().equals(albumsongsTable.getValueAt(albumsongsTable.getSelectedRow(), 0).toString())){
                //queue.clear();
                player.setsong(album.getSongs().get(i));
                //queue.add(album.getSongs().get(i));
            }
        }

        player.setcon(getcon());
        //player.setQueue(queue);
        player.songtoplay();
        albumsongsTable.clearSelection();
        player.setVisible(true);
        player.stoptimer();
        player.getwav().stopMusic();
        //owner.setQueue(queue);
    }
    
    public void addtoQueue(){
            for(int i = 0; i < album.getSongs().size(); i++){
                queue.add(album.getSongs().get(i));
            }
            owner.setQueue(queue);
    }
    
    public void deletesong(){
        try {
            if(albumsongsTable.getSelectedRow() < 0)
                JOptionPane.showMessageDialog(null, "Select A Song First Before Deleting!");
            else{
                for(Iterator<Song> itr = album.getSongs().iterator(); itr.hasNext();){
                    Song tempSong = itr.next();
                    
                    if(tempSong.getTitle().equals(albumsongsTable.getValueAt(albumsongsTable.getSelectedRow(), 0).toString())){
                        Statement removeSongAlbumState = getcon().createStatement();
                        removeSongAlbumState.execute("DELETE FROM databasedc.songalbum WHERE SongID = '"+tempSong.getid()+"'");
                        
                        Statement updateSongDetail = getcon().createStatement();
                        updateSongDetail.execute("UPDATE databasedc.songdetail SET AlbumName = '"+""+"' WHERE SongID = '"+tempSong.getid()+"'");
                        
                        itr.remove();
                        JOptionPane.showMessageDialog(null, "Song has been deleted");
                        
                        DefaultTableModel modelp = (DefaultTableModel) albumsongsTable.getModel();
                        modelp.setRowCount(0);
                        for(int i = 0 ; i < album.getSongs().size(); i++ ){
                            modelp.insertRow(albumsongsTable.getRowCount(), new Object[]{
                               album.getSongs().get(i).getTitle(),
                               album.getSongs().get(i).getGenre().getGenre()
                            });
                        }
                        
                        break;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        for(int i = 0; i < albums.size(); i++){
            if(albums.get(i).getName().equals(album.getName())){
                for(int a = 0; a < albums.get(i).getSongs().size();a++){
                    if(albums.get(i).getSongs().get(a).getTitle().equals(albumsongsTable.getValueAt(albumsongsTable.getSelectedRow(), 0).toString())){
                        albums.get(i).getSongs().remove(a);
                        break;
                    }
                }
            }
        }
        owner.setAlbums(albums);
    }

    public void setowner(Dashboard owner){
        this.owner = owner;
    }
    
    public void setAlbums(ArrayList<Album> albums){
        this.albums = albums;
    }
    
    public void setalbum(Album album){
        this.album = album;
    }
    
    public void setcon(Connection connection){
        this.connection = connection;
    }
   
    public Connection getcon(){
        return this.connection;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        albumimagelabel = new javax.swing.JLabel();
        albumartist = new javax.swing.JLabel();
        albumnamelabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        albumsongsTable = new javax.swing.JTable();
        AlbumPlayButton = new javax.swing.JLabel();
        AlbumDeleteButton = new javax.swing.JLabel();
        addalbumtoqueueButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        albumimagelabel.setMaximumSize(new java.awt.Dimension(64, 64));
        albumimagelabel.setMinimumSize(new java.awt.Dimension(64, 64));
        albumimagelabel.setPreferredSize(new java.awt.Dimension(64, 64));

        albumartist.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        albumartist.setText("Artist name");

        albumnamelabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        albumnamelabel.setText("Album Name");

        albumsongsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title ", "Genre"
            }
        ));
        jScrollPane1.setViewportView(albumsongsTable);

        AlbumPlayButton.setBackground(new java.awt.Color(51, 51, 51));
        AlbumPlayButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        AlbumPlayButton.setForeground(new java.awt.Color(255, 255, 255));
        AlbumPlayButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AlbumPlayButton.setText("Play");
        AlbumPlayButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AlbumPlayButton.setOpaque(true);
        AlbumPlayButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlbumPlayButtonMouseClicked(evt);
            }
        });

        AlbumDeleteButton.setBackground(new java.awt.Color(51, 51, 51));
        AlbumDeleteButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        AlbumDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        AlbumDeleteButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AlbumDeleteButton.setText("Delete");
        AlbumDeleteButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        AlbumDeleteButton.setOpaque(true);
        AlbumDeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlbumDeleteButtonMouseClicked(evt);
            }
        });

        addalbumtoqueueButton.setBackground(new java.awt.Color(51, 51, 51));
        addalbumtoqueueButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        addalbumtoqueueButton.setForeground(new java.awt.Color(255, 255, 255));
        addalbumtoqueueButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addalbumtoqueueButton.setText("Add to Queue");
        addalbumtoqueueButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addalbumtoqueueButton.setOpaque(true);
        addalbumtoqueueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addalbumtoqueueButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(albumimagelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(albumartist, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(AlbumDeleteButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AlbumPlayButton, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(addalbumtoqueueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(albumnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(204, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(albumimagelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addalbumtoqueueButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(albumartist, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(AlbumPlayButton)
                            .addComponent(AlbumDeleteButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(albumnamelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(305, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AlbumPlayButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlbumPlayButtonMouseClicked
        if(albumsongsTable.getSelectedRow() < 0)
            JOptionPane.showMessageDialog(null, "Please select a song first before playing!", "No song selected", JOptionPane.WARNING_MESSAGE);
        else
            playsong();
    }//GEN-LAST:event_AlbumPlayButtonMouseClicked

    private void AlbumDeleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlbumDeleteButtonMouseClicked
        deletesong();
    }//GEN-LAST:event_AlbumDeleteButtonMouseClicked

    private void addalbumtoqueueButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addalbumtoqueueButtonMouseClicked
        addtoQueue();
    }//GEN-LAST:event_addalbumtoqueueButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
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
            java.util.logging.Logger.getLogger(specificalbumwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(specificalbumwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(specificalbumwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(specificalbumwindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new specificalbumwindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AlbumDeleteButton;
    private javax.swing.JLabel AlbumPlayButton;
    private javax.swing.JLabel addalbumtoqueueButton;
    private javax.swing.JLabel albumartist;
    private javax.swing.JLabel albumimagelabel;
    private javax.swing.JLabel albumnamelabel;
    private javax.swing.JTable albumsongsTable;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
