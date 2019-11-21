import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class YourProfile extends javax.swing.JDialog {

    private User user;
    private Connection con;
    
    public YourProfile(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        aligntable();
    }

    public void aligntable()
    {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
        favsong.setDefaultRenderer(Object.class, centerRenderer);
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
    
    public void setprofile()
    {
        userlabel.setText(getuser().getusername());
        namelabel.setText(getuser().getfirstname() + " " + getuser().getlastname());
        datelabel.setText(getuser().getdate().toString());
        useridlabel.setText(String.valueOf(getuser().getid()));
        
        try {
            DefaultTableModel modelsong = (DefaultTableModel) favsong.getModel();
            modelsong.setRowCount(0);
            Statement statesong = getcon().createStatement();
            ResultSet resultsong = statesong.executeQuery("SELECT * FROM databasedc.playlistdc NATURAL JOIN databasedc.songdetail NATURAL JOIN databasedc.song WHERE SongFav = '"+"true"+"' && UserID = '"+getuser().getid()+"'");
            while(resultsong.next())
            {
                String playlistname = resultsong.getString("PlaylistName");
                String title = resultsong.getString("SongTitle");
                String artist = resultsong.getString("SongArtist");
                modelsong.insertRow(favsong.getRowCount(), new Object[]{
                    title,
                    playlistname,
                    artist
                });
            }
            
            DefaultTableModel modelmostsong = (DefaultTableModel) mostsongs.getModel();
            modelmostsong.setRowCount(0);
            Statement statemost = getcon().createStatement();
            ResultSet resultmost = statemost.executeQuery("SELECT MAX(SongPlayed) FROM databasedc.songdetail");
            int max=0;
            while(resultmost.next())
            {
                max = resultmost.getInt("MAX(SongPlayed)");
            }
            
            if(max > 0)
            {
                Statement statecheck = getcon().createStatement();
                ResultSet resultcheck = statecheck.executeQuery("SELECT * FROM databasedc.playlistdc NATURAL JOIN databasedc.songdetail NATURAL JOIN databasedc.song WHERE SongPlayed = '"+max+"' && UserID = '"+getuser().getid()+"'");
                while(resultcheck.next())
                {
                    String playlistname = resultcheck.getString("PlaylistName");
                    String title = resultcheck.getString("SongTitle");
                    String artist = resultcheck.getString("SongArtist");
                    
                    modelmostsong.insertRow(mostsongs.getRowCount(), new Object[]{
                        title,
                        playlistname,
                        artist
                    });
                }
            }
            
            DefaultTableModel modelfollowuser = (DefaultTableModel) followuser.getModel();
            modelfollowuser.setRowCount(0);
            Statement stateuser = getcon().createStatement();
            ResultSet resultuser = stateuser.executeQuery("SELECT * FROM databasedc.followuser WHERE UserID = '"+getuser().getid()+"'");
            ArrayList<Integer> followid = new ArrayList<>();
            while(resultuser.next())
            {
                followid.add(resultuser.getInt("FollowUserID"));
            }
            
            for(int i=0; i<followid.size(); i++)
            {
                Statement statefollowuser = getcon().createStatement();
                ResultSet resultfollowuser = statefollowuser.executeQuery("SELECT * FROM databasedc.useraccount WHERE UserID = '"+followid.get(i)+"'");
                while(resultfollowuser.next())
                {
                    modelfollowuser.insertRow(followuser.getRowCount(), new Object[]{
                        resultfollowuser.getString("UserName"),
                        resultfollowuser.getString("Type")
                    });
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(YourProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        userlabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        datelabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        namelabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        useridlabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        favsong = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        mostsongs = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        followuser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(18, 22, 24));

        userlabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        userlabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Account Created:");

        datelabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        datelabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");

        namelabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        namelabel.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("User ID:");

        useridlabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        useridlabel.setForeground(new java.awt.Color(255, 255, 255));

        favsong.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        favsong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Favorite Songs", "Playlist Name", "Artist"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        favsong.setRowHeight(21);
        favsong.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(favsong);
        if (favsong.getColumnModel().getColumnCount() > 0) {
            favsong.getColumnModel().getColumn(0).setResizable(false);
            favsong.getColumnModel().getColumn(1).setResizable(false);
            favsong.getColumnModel().getColumn(2).setResizable(false);
        }

        mostsongs.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        mostsongs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Most Songs Played", "Playlist Name", "Artist"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        mostsongs.setRowHeight(21);
        mostsongs.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(mostsongs);
        if (mostsongs.getColumnModel().getColumnCount() > 0) {
            mostsongs.getColumnModel().getColumn(0).setResizable(false);
            mostsongs.getColumnModel().getColumn(1).setResizable(false);
            mostsongs.getColumnModel().getColumn(2).setResizable(false);
        }

        followuser.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        followuser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "FollowUser", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        followuser.setRowHeight(21);
        followuser.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(followuser);
        if (followuser.getColumnModel().getColumnCount() > 0) {
            followuser.getColumnModel().getColumn(0).setResizable(false);
            followuser.getColumnModel().getColumn(1).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(namelabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(useridlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 299, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(datelabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useridlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                YourProfile dialog = new YourProfile(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel datelabel;
    private javax.swing.JTable favsong;
    private javax.swing.JTable followuser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable mostsongs;
    private javax.swing.JLabel namelabel;
    private javax.swing.JLabel useridlabel;
    private javax.swing.JLabel userlabel;
    // End of variables declaration//GEN-END:variables
}
