import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class EditPlay extends javax.swing.JDialog implements Edit{

    private String change, prevname;
    private ArrayList<Playlist> yourplaylist = new ArrayList<>();
    private Connection con;
    private boolean edited = false;
    
    public EditPlay(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        changenametext.setTransferHandler(null);
    }
    
    public void setarrplay(ArrayList<Playlist> arr)
    {
        this.yourplaylist = arr;
    }
    
    public ArrayList<Playlist> getarrplay()
    {
        return this.yourplaylist;
    }
    
    public void setprevname(String prev)
    {
        this.prevname = prev;
    }
    
    public String getprevname()
    {
        return this.prevname;
    }
    
    @Override
    public void setcon(Connection con)
    {
        this.con = con;
    }
    
    @Override
    public Connection getcon()
    {
        return this.con;
    }
    
    public String getchange()
    {
        return this.change;
    }
    
    public boolean getedited()
    {
        return this.edited;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        changenametext = new javax.swing.JTextField();
        okbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(100, 80));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Playlist Name", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 2, 20))); // NOI18N

        changenametext.setFont(new java.awt.Font("Tahoma", 0, 19)); // NOI18N
        changenametext.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changenametextKeyTyped(evt);
            }
        });

        okbtn.setText("OK");
        okbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okbtnActionPerformed(evt);
            }
        });
        okbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                okbtnKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changenametext, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(okbtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(changenametext, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(okbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    @Override
    public void edit()
    {
        String edittext = changenametext.getText();
        boolean exists = false;
        
        for(int x=0; x<getarrplay().size(); x++)
        {
            if(getarrplay().get(x).getName().equals(edittext))
            {
                exists = true;
                break;
            }
        }
        
        if(edittext.equals(""))
            JOptionPane.showMessageDialog(null, "Please Input A Playlist Name", "No Playlist Name Found", JOptionPane.WARNING_MESSAGE);
        else if(edittext.equals(getprevname())){
            JOptionPane.showMessageDialog(null, "Nothing Change");
            changenametext.requestFocus();
        }
        else if(exists){
            JOptionPane.showMessageDialog(null, "PlaylistName Already Exists");
            changenametext.requestFocus();
        }
        else{
            try {
                int playid = 0;
                for(int z=0; z<getarrplay().size(); z++)
                {
                    if(getarrplay().get(z).getName().equals(getprevname()))
                    {
                        playid = getarrplay().get(z).getid();
                        break;
                    }
                }
                
                Statement statechangeplayname = getcon().createStatement();
                statechangeplayname.execute("UPDATE databasedc.playlistdc SET PlaylistName = '"+edittext+"' WHERE PlaylistName = '"+getprevname()+"' AND PlaylistID = '"+playid+"'");
            } catch (SQLException ex) {
                Logger.getLogger(EditPlay.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.edited = true;
            JOptionPane.showMessageDialog(null, "Edit Successful!");
            changenametext.setText("");
            this.change = edittext;
            this.dispose();
        }    
    }
    
    private void okbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okbtnActionPerformed
        edit();
    }//GEN-LAST:event_okbtnActionPerformed

    private void changenametextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changenametextKeyTyped
        int playT = changenametext.getText().length();
        char c = evt.getKeyChar();
        
        if(playT > 49)
            evt.consume();
        
        if(c == (char)39 || c == (char)32)
            evt.consume();
    }//GEN-LAST:event_changenametextKeyTyped

    private void okbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_okbtnKeyPressed
        edit();
    }//GEN-LAST:event_okbtnKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditPlay dialog = new EditPlay(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField changenametext;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okbtn;
    // End of variables declaration//GEN-END:variables
}
