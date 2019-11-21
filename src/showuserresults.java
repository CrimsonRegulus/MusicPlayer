
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class showuserresults extends javax.swing.JFrame {

    private Connection con;
    private String username;
    private User user;
    private Dashboard owner;
    
    public void setOwner(Dashboard owner){
        this.owner = owner;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public showuserresults() {
        initComponents();
    }

     public void setcon(Connection con)
    {
        this.con = con;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    public void setUser(User user){
        this.user = user;
    }
    public void initilize(){
        try {
            DefaultTableModel model = (DefaultTableModel) userplaylistsTable.getModel();
            model.setRowCount(0);
            Statement statementgetartist = getcon().createStatement();
            ResultSet resultgetartist= statementgetartist.executeQuery("SELECT * FROM databasedc.playlistdc NATURAL JOIN databasedc.useraccount WHERE UserName= '"+username+"'");
            
            while(resultgetartist.next()){
                model.insertRow(userplaylistsTable.getRowCount(), new Object[]{
                    resultgetartist.getString("PlaylistName")
                });
            }
        } catch (SQLException ex) {
            Logger.getLogger(showuserresults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        followuserButton = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userplaylistsTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        unfollowuserButton = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(25, 25, 25));

        usernameLabel.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        usernameLabel.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel.setText("Username");

        followuserButton.setBackground(new java.awt.Color(51, 51, 51));
        followuserButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        followuserButton.setForeground(new java.awt.Color(255, 255, 255));
        followuserButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        followuserButton.setText("Follow");
        followuserButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        followuserButton.setOpaque(true);
        followuserButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                followuserButtonMouseClicked(evt);
            }
        });

        userplaylistsTable.setAutoCreateRowSorter(true);
        userplaylistsTable.setBackground(new java.awt.Color(25, 25, 25));
        userplaylistsTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        userplaylistsTable.setForeground(new java.awt.Color(255, 255, 255));
        userplaylistsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Playlist Name"
            }
        ));
        jScrollPane1.setViewportView(userplaylistsTable);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Playlists");

        unfollowuserButton.setBackground(new java.awt.Color(51, 51, 51));
        unfollowuserButton.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        unfollowuserButton.setForeground(new java.awt.Color(255, 255, 255));
        unfollowuserButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unfollowuserButton.setText("Unfollow");
        unfollowuserButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        unfollowuserButton.setOpaque(true);
        unfollowuserButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unfollowuserButtonMouseClicked(evt);
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
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(followuserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(unfollowuserButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(followuserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(unfollowuserButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void followuserButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_followuserButtonMouseClicked
        try {
            boolean exists = false;
            int name = 0;
            Statement statementgetartist = getcon().createStatement();
            ResultSet resultgetartist= statementgetartist.executeQuery("SELECT * FROM databasedc.useraccount WHERE UserName= '"+username+"'");
            while(resultgetartist.next()){
                
                name = resultgetartist.getInt("UserID");
            }
            
            Statement check = getcon().createStatement();
            ResultSet checkRS = check.executeQuery("SELECT * FROM databasedc.followuser WHERE UserID = '"+user.getid()+"' AND FollowUserID = '"+name+"'");
            
            while(checkRS.next()){
                exists = true;
            }
            
            if(!exists){
                Statement statement = getcon().createStatement();
                statement.execute("INSERT INTO databasedc.followuser(UserID, FollowUserID, Notify) VALUES('"+user.getid()+"', '"+name+"', '"+"true"+"')");
                JOptionPane.showMessageDialog(null, "You have followed this user!");
                owner.loadfollowed();
            }
            
            else
                JOptionPane.showMessageDialog(null, "You have already followed this user!");
        } catch (SQLException ex) {
            Logger.getLogger(showuserresults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_followuserButtonMouseClicked

    private void unfollowuserButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unfollowuserButtonMouseClicked
        try {
            int name = 0;
            Statement statementgetartist = getcon().createStatement();
            ResultSet resultgetartist= statementgetartist.executeQuery("SELECT * FROM databasedc.useraccount WHERE UserName= '"+username+"'");
            while(resultgetartist.next()){
                
                name = resultgetartist.getInt("UserID");
            }

            Statement statement = getcon().createStatement();
            statement.execute("DELETE FROM databasedc.followuser WHERE UserID = '"+user.getid()+"' AND FollowUserID = '"+name+"'");

            JOptionPane.showMessageDialog(null, "You have unfollowed this user!");
            owner.loadfollowed();
        } catch (SQLException ex) {
            Logger.getLogger(showuserresults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_unfollowuserButtonMouseClicked

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
            java.util.logging.Logger.getLogger(showuserresults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(showuserresults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(showuserresults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(showuserresults.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new showuserresults().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel followuserButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel unfollowuserButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTable userplaylistsTable;
    // End of variables declaration//GEN-END:variables
}
