
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Darren
 */
public class CreateAlbum extends javax.swing.JFrame {
    private User user;
    private Connection connection;
    private File file;
    private Dashboard owner;
    SimpleDateFormat datefor = new SimpleDateFormat("yyyy-MM-dd");
    
    public void setOwner(Dashboard owner){
        this.owner = owner;
    }
    public CreateAlbum() {
        try {
            initComponents();
            BufferedImage defaulticon = null;
            defaulticon= ImageIO.read(getClass().getResource("/Icons/question-mark-64.png"));
            albumiconpreview.setIcon(new ImageIcon(defaulticon.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
        } catch (IOException ex) {
            Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            file = new File("src/Icons/question-mark-64.png");
    }
    
    public void createalbumMethod() {
        String albumName = albumNameTextfield.getText();
        
        if(albumName.equals(""))
            JOptionPane.showMessageDialog(null, "Please Enter An Album Name!");
        
        else {
            boolean exists = false;
            ArrayList<Album> albums = owner.getAlbums();
            
            for(int i = 0; i < albums.size(); i++){
                if(albums.get(i).getName().equals(albumName)){
                    exists = true;
                    break;
                }
            }
            
            if(!exists){
                try {
                    FileInputStream fis = new FileInputStream(file);
                    PreparedStatement statement = getcon().prepareStatement("INSERT INTO databasedc.albumdc(AlbumName, UserID, AlbumDate, AlbumCover) VALUES(?, ?, ?, ?)");
                    statement.setString(1, albumName);
                    statement.setInt(2, getuser().getid());
                    statement.setString(3, datefor.format(new Date()));
                    statement.setBinaryStream(4, fis, (int) file.length());
                    
                    statement.execute();

                    Statement getAlbumIDState = getcon().createStatement();
                    ResultSet rs = getAlbumIDState.executeQuery("SELECT MAX(AlbumID) FROM databasedc.albumdc");

                    int albumID = 0;

                    while(rs.next()){
                        albumID = rs.getInt("MAX(AlbumID)");
                    }
                    PreparedStatement getBlobStatement = getcon().prepareStatement("SELECT * FROM databasedc.albumdc WHERE AlbumID = ?");
                    getBlobStatement.setInt(1, albumID);
                    ResultSet getBlobRS = getBlobStatement.executeQuery();
                    Blob blob = null;
                    while(getBlobRS.next()){
                        blob = getBlobRS.getBlob("AlbumCover");
                    }
                    
                    albums.add(new Album(albumID, albumName, datefor.format(new Date()),blob,getuser().getfirstname() + " " + getuser().getlastname()));
                    owner.setAlbums(albums);
                    JOptionPane.showMessageDialog(null, "Album Created Successfully!");
                    owner.loadalbums();
                    this.dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CreateAlbum.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                
            else
                JOptionPane.showMessageDialog(null, "Album Already Exists!");
        }
    }
    
    public void setcon(Connection connection){
        this.connection = connection;
    }
    
    
    public Connection getcon(){
        return this.connection;
    }
    public void setuser(User user)
    {
        this.user = user;
    }
    
    public User getuser()
    {
        return this.user;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        albumiconChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        albumNameTextfield = new javax.swing.JTextField();
        albumiconpreview = new javax.swing.JLabel();
        changealbumpictureBtn = new javax.swing.JButton();
        createalbumBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create Album");

        jLabel2.setText("Album Name : ");

        changealbumpictureBtn.setText("Change Album Picture");
        changealbumpictureBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changealbumpictureBtnMouseClicked(evt);
            }
        });
        changealbumpictureBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changealbumpictureBtnActionPerformed(evt);
            }
        });

        createalbumBTN.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        createalbumBTN.setText("CREATE ALBUM");
        createalbumBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createalbumBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(albumiconpreview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(albumNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(changealbumpictureBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(112, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(createalbumBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(126, 126, 126))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(albumNameTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumiconpreview, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(changealbumpictureBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createalbumBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void changealbumpictureBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changealbumpictureBtnMouseClicked

        int returnVal = albumiconChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = albumiconChooser.getSelectedFile();
            BufferedImage imgprof = null;
        
            try {
                imgprof = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             albumiconpreview.setIcon(new ImageIcon(imgprof.getScaledInstance(64, 64, Image.SCALE_SMOOTH)));
        }
    
    }//GEN-LAST:event_changealbumpictureBtnMouseClicked

    private void createalbumBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createalbumBTNActionPerformed
        createalbumMethod();
    }//GEN-LAST:event_createalbumBTNActionPerformed

    private void changealbumpictureBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changealbumpictureBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changealbumpictureBtnActionPerformed

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
            java.util.logging.Logger.getLogger(CreateAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAlbum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateAlbum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField albumNameTextfield;
    private javax.swing.JFileChooser albumiconChooser;
    private javax.swing.JLabel albumiconpreview;
    private javax.swing.JButton changealbumpictureBtn;
    private javax.swing.JButton createalbumBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
