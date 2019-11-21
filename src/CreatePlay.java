import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class CreatePlay extends CLPlay
{
    private JDialog createD = new JDialog();
    private JPanel createP = new JPanel(null);
    private JButton createB = new JButton("OK");
    private JLabel createL = new JLabel("Playlist Name:");
    private JTextField createT = new JTextField("");
    private boolean created = true;
    SimpleDateFormat datefor = new SimpleDateFormat("yyyy-MM-dd");
    
    public CreatePlay(Connection con, User user, ArrayList<Playlist> playlist)
    {
        super(con,user,playlist);
        initComponents();
        createT.setTransferHandler(null);
    }
    
    public boolean isCreated()
    {
        return this.created;
    }
    
    public void initComponents()
    {
        // JDialog
        createD.setResizable(false);
        createD.setModal(true);
        createD.setSize(410, 130);
        createD.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        createD.setLocation(new java.awt.Point(100, 80));
        
        // JLabel
        createL.setBounds(4, 30, 135, 36);
        createL.setFont(new java.awt.Font("Tahoma", 1, 17));
        
        // JButton
        createB.addActionListener(new OKbtn_Action());
        createB.setBounds(345, 30, 53, 36);
        
        // JTextField
        createT.setBounds(130, 30, 210, 36);
        createT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            @Override
            public void keyTyped (KeyEvent evt)  
            {  
                int playT = createT.getText().length();
                char c = evt.getKeyChar();
                
                if(playT > 49)
                    evt.consume();
                
                if(c == (char)39 || c == (char)32)
                    evt.consume();
            }
        });
        
        // JPanel
        createP.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Create PlayList", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Sitka Small", 2, 18)));
	createP.setBackground(new java.awt.Color(240, 240, 240));
        createP.setSize(410, 99);
        
        createD.add(createP);
        createP.add(createB);
        createP.add(createL);
        createP.add(createT);
        createD.setVisible(true);
    }
    
    public void createplay()
    {
        if(getuser().gettype().equals("Registered") || getuser().gettype().equals("Artist"))
        {
            try {
               String playname = createT.getText();
               boolean same = false;
               
               Statement statesame = getcon().createStatement();
               ResultSet resultsame = statesame.executeQuery("SELECT * FROM databasedc.playlistdc WHERE UserID = '"+getuser().getid()+"'");
               while(resultsame.next())
               {
                   if(resultsame.getString("PlaylistName").trim().equals(playname)){
                       same = true;
                       break;
                   }
               }
            
               if(playname.equals(""))
                   JOptionPane.showMessageDialog(null, "Please Input Playlist Name", "No Playlist Name Found!", JOptionPane.WARNING_MESSAGE);
               else if(same){
                   JOptionPane.showMessageDialog(null, "Please Input Other Playlist Name", "Playlist Name Already Exists!", JOptionPane.WARNING_MESSAGE);
               }else{
                   Statement state = getcon().createStatement();
                   state.execute("INSERT INTO databasedc.playlistdc(PlaylistName,UserID,PlaylistDate) VALUES('"+playname+"','"+getuser().getid()+"','"+datefor.format(new Date())+"')");
                   Statement stategetid = getcon().createStatement();
                   ResultSet resultgetid = stategetid.executeQuery("SELECT MAX(PlaylistID) FROM databasedc.playlistdc");
                   int getid = 0;
                   while(resultgetid.next())
                   {
                       getid = resultgetid.getInt("MAX(PlaylistID)");
                   }
                   
                   setplay(new Playlist(getid, playname, datefor.format(new Date())));
                   
                   Statement updateState = getcon().createStatement();
                   updateState.execute("UPDATE databasedc.followuser SET Notify = '"+"true"+"' WHERE FollowUserID = '"+getuser().getid()+"'");
                   
                   
                   JOptionPane.showMessageDialog(null, "Create Successful!");
                   createD.dispose();
               }
               statesame.close();
            } catch (SQLException ex) {
                Logger.getLogger(CreatePlay.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else
        {
            if(getplaylist().size() == 1)
            {
                JOptionPane.showMessageDialog(null, "Create An Account First To Add More Playlists!");
                int dialogResultGuest = JOptionPane.showConfirmDialog(null, "Do you wanna create an account?", "Create Account", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(dialogResultGuest == JOptionPane.YES_OPTION){
                   Signup sign = new Signup(new JFrame(), true);
                   sign.setcon(getcon());
                   sign.setVisible(true);
                   this.created = false;
                   createD.dispose();
                }
                else{
                    this.created = false;
                    createD.dispose();
                }
            }
            else
            {
                String playname = createT.getText();
             
                if(playname.equals(""))
                    JOptionPane.showMessageDialog(null, "Please Input Playlist Name", "No Playlist Name Found!", JOptionPane.WARNING_MESSAGE);
                else{
                    try {
                        Statement stateplayid = getcon().createStatement();
                        ResultSet resultplayid = stateplayid.executeQuery("SELECT MAX(PlaylistID) FROM databasedc.playlistdc");
                        int maxplay=0;
                        while(resultplayid.next())
                        {
                            maxplay = resultplayid.getInt("MAX(PlaylistID)");
                        }
                        
                        setplay(new Playlist(maxplay+1,playname, datefor.format(new Date())));
                        Statement stateinsertplay = getcon().createStatement();
                        stateinsertplay.execute("INSERT INTO databasedc.playlistdc(PlaylistName,UserID,PlaylistDate) VALUES('"+playname+"','"+getuser().getid()+"','"+datefor.format(new Date())+"')");
                        JOptionPane.showMessageDialog(null, "Create Successful!");
                        createD.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(CreatePlay.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    class OKbtn_Action implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                createplay();
            }
        }
}