import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Signup extends javax.swing.JDialog {

    private Connection con;
    private boolean signed = false;
    SimpleDateFormat datefor = new SimpleDateFormat("yyyy-MM-dd");
    
    public Signup(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        icons();
        setghostsign();
        copypasteXX();
    }

    public void copypasteXX()
    {
        musicfirstsign.setTransferHandler(null);
        musiclastsign.setTransferHandler(null);
        musicusersign.setTransferHandler(null);
        musicpasssign.setTransferHandler(null);
        musicrepasssign.setTransferHandler(null);
    }
    
    public void setcon(Connection con)
    {
        this.con = con;
    }
    
    public Connection getcon()
    {
        return this.con;
    }
    
    public void setsignup(boolean sign)
    {
        this.signed = sign;
    }
    
    public boolean getsignup()
    {
        return this.signed;
    }
    
    public void clearsign()
    {
        musicfirstsign.setText("");
        musiclastsign.setText("");
        musicusersign.setText("");
        musicpasssign.setText("");
        musicrepasssign.setText("");
        userbox.setSelectedIndex(0);
    }
    
    public void signtodb()
    {
        try {
            String firstS = "";
            String lastS = "";
            String userS = "";
            String passS = "";
            String repassS = "";
            if(!musicusersign.getForeground().equals(Color.GRAY))
               userS = musicusersign.getText().trim();
            if(!musicpasssign.getForeground().equals(Color.GRAY))
               passS = musicpasssign.getText().trim();
            if(!musicrepasssign.getForeground().equals(Color.GRAY))
               repassS = musicrepasssign.getText().trim();
            if(!musicfirstsign.getForeground().equals(Color.GRAY))
               firstS = musicfirstsign.getText().trim();
            if(!musiclastsign.getForeground().equals(Color.GRAY))
               lastS = musiclastsign.getText().trim();
            
            Statement stateS = getcon().createStatement();
            ResultSet resultS = stateS.executeQuery("SELECT UserName FROM databasedc.useraccount");
            boolean userE=false;
            
            while(resultS.next())
            {
                String usernameS = resultS.getString("UserName");
                
                if(userS.equals(usernameS))
                    userE = true;
            }
            
            if(firstS.equals("")){
                JOptionPane.showMessageDialog(null, "Please Input Your Firstname", "No Firstname Found!", JOptionPane.WARNING_MESSAGE);
                musicfirstsign.requestFocus();
            }
            else if(lastS.equals("")){
                JOptionPane.showMessageDialog(null, "Please Input Your LastName", "No LastName Found!", JOptionPane.WARNING_MESSAGE);
                musiclastsign.requestFocus();
            }
            else if(userS.equals("")){
                JOptionPane.showMessageDialog(null,"Please Input Your Username","No Username Found!", JOptionPane.WARNING_MESSAGE);
                musicusersign.requestFocus();
                if(!musicpasssign.getForeground().equals(Color.GRAY)){
                  musicpasssign.setText("");
                  musicpasssign.setEchoChar((char)0);
                  musicpasssign.setText("Enter Password");
                  musicpasssign.setForeground(Color.GRAY);
                }
                if(!musicrepasssign.getForeground().equals(Color.GRAY)){
                  musicrepasssign.setText("");
                  musicrepasssign.setEchoChar((char)0);
                  musicrepasssign.setText("Re-Enter Password");
                  musicrepasssign.setForeground(Color.GRAY);
                }
            }
            else if(userE){
                JOptionPane.showMessageDialog(null, "UserName Already Exists", "Username Exists", JOptionPane.WARNING_MESSAGE);
                musicusersign.requestFocus();
                musicusersign.setText("");
                if(!musicpasssign.getForeground().equals(Color.GRAY)){
                  musicpasssign.setText("");
                  musicpasssign.setEchoChar((char)0);
                  musicpasssign.setText("Enter Password");
                  musicpasssign.setForeground(Color.GRAY);
                }
                if(!musicrepasssign.getForeground().equals(Color.GRAY)){
                  musicrepasssign.setText("");
                  musicrepasssign.setEchoChar((char)0);
                  musicrepasssign.setText("Re-Enter Password");
                  musicrepasssign.setForeground(Color.GRAY);
                }
            }
            else if(passS.equals("")){
                JOptionPane.showMessageDialog(null,"Please Enter Your Password","No Password Found!", JOptionPane.WARNING_MESSAGE);
                musicpasssign.requestFocus();
                if(!musicrepasssign.getForeground().equals(Color.GRAY)){
                  musicrepasssign.setText("");
                  musicrepasssign.setEchoChar((char)0);
                  musicrepasssign.setText("Re-Enter Password");
                  musicrepasssign.setForeground(Color.GRAY);
                }
            }
            else if(repassS.equals("")){
                JOptionPane.showMessageDialog(null, "Please Re-Enter Your Password", "No Password Found!", JOptionPane.WARNING_MESSAGE);
                musicrepasssign.setText("");
                musicrepasssign.requestFocus();
            }
            else if(!repassS.equals(passS)){
                JOptionPane.showMessageDialog(null,"Please Input Correct Password","Not Equal Password!",JOptionPane.WARNING_MESSAGE);
                musicrepasssign.setText("");
                musicrepasssign.requestFocus();
            }
            else if(userbox.getSelectedItem().equals("None"))
                JOptionPane.showMessageDialog(null,"Please Choose Your Role");
            else
            {   
                String Uname = "INSERT INTO useraccount(UserName, PassWord, FirstName, LastName, Type, RegisteredDate) VALUES('" + userS + "','" + passS + "','" + firstS + "','" + lastS + "','" + userbox.getSelectedItem().toString() + "','" + datefor.format(new Date()) + "')";
                stateS.execute(Uname);
                stateS.close();
                setsignup(true);
                JOptionPane.showMessageDialog(null, "Sign Up Successful!"); 
                clearsign();
                this.dispose(); // exit this dialog
            }
        } catch (SQLException ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        musicfirstsignpic = new javax.swing.JLabel();
        musicfirstsign = new javax.swing.JTextField();
        musiclastsignpic = new javax.swing.JLabel();
        musiclastsign = new javax.swing.JTextField();
        musicusersign = new javax.swing.JTextField();
        musicusersignpic = new javax.swing.JLabel();
        musicpasssignpic = new javax.swing.JLabel();
        musicrepasssignpic = new javax.swing.JLabel();
        signupbtn = new javax.swing.JButton();
        musicpasssign = new javax.swing.JPasswordField();
        musicrepasssign = new javax.swing.JPasswordField();
        userbox = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocation(new java.awt.Point(900, 200));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sign Up", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Script MT Bold", 1, 21))); // NOI18N

        musicfirstsign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                musicfirstsignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                musicfirstsignFocusLost(evt);
            }
        });
        musicfirstsign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                musicfirstsignKeyTyped(evt);
            }
        });

        musiclastsignpic.setPreferredSize(new java.awt.Dimension(51, 55));

        musiclastsign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                musiclastsignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                musiclastsignFocusLost(evt);
            }
        });
        musiclastsign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                musiclastsignKeyTyped(evt);
            }
        });

        musicusersign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                musicusersignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                musicusersignFocusLost(evt);
            }
        });
        musicusersign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                musicusersignKeyTyped(evt);
            }
        });

        signupbtn.setBackground(new java.awt.Color(255, 0, 0));
        signupbtn.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        signupbtn.setForeground(new java.awt.Color(255, 255, 255));
        signupbtn.setText("Sign Up");
        signupbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupbtnActionPerformed(evt);
            }
        });

        musicpasssign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                musicpasssignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                musicpasssignFocusLost(evt);
            }
        });
        musicpasssign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                musicpasssignKeyTyped(evt);
            }
        });

        musicrepasssign.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                musicrepasssignFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                musicrepasssignFocusLost(evt);
            }
        });
        musicrepasssign.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                musicrepasssignKeyTyped(evt);
            }
        });

        userbox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Registered", "Artist" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signupbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(musicrepasssignpic, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                            .addComponent(musicusersignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musicpasssignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musicfirstsignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(musicusersign)
                            .addComponent(musicpasssign)
                            .addComponent(musicrepasssign)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(musicfirstsign, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(musiclastsignpic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(musiclastsign, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(userbox, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(musiclastsign, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(musicfirstsign, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(musicfirstsignpic, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(musiclastsignpic, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musicusersign, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(musicusersignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musicpasssign, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                    .addComponent(musicpasssignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(musicrepasssignpic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(musicrepasssign, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(signupbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void musicfirstsignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_musicfirstsignKeyTyped
        int firstl = musicfirstsign.getText().length();
        char d = evt.getKeyChar();
        
        if(firstl > 49)
            evt.consume();
        
        if(!(Character.isAlphabetic(d)) && d != (char)32)
            evt.consume();
    }//GEN-LAST:event_musicfirstsignKeyTyped

    private void musiclastsignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_musiclastsignKeyTyped
        int lastl = musiclastsign.getText().length();
        char d = evt.getKeyChar();
        
        if(lastl > 49)
            evt.consume();
        
        if(!(Character.isAlphabetic(d)) && d != (char)32)
            evt.consume();
    }//GEN-LAST:event_musiclastsignKeyTyped

    private void musicusersignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_musicusersignKeyTyped
        int userl = musicusersign.getText().length();
        char c = evt.getKeyChar();
        
        if(userl > 49)
            evt.consume();
        
        if(c == (char)39 || c == (char)32)
            evt.consume();
    }//GEN-LAST:event_musicusersignKeyTyped

    private void musicpasssignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_musicpasssignKeyTyped
        int passl = musicpasssign.getText().length();
        char c = evt.getKeyChar();
        
        if(passl > 49)
            evt.consume();
        
        if(c == (char)39 || c == (char)32)
            evt.consume();
    }//GEN-LAST:event_musicpasssignKeyTyped

    private void musicrepasssignKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_musicrepasssignKeyTyped
        int repassl = musicrepasssign.getText().length();
        char c = evt.getKeyChar();
        
        if(repassl > 49)
            evt.consume();
        
        if(c == (char)39 || c == (char)32)
            evt.consume();
    }//GEN-LAST:event_musicrepasssignKeyTyped

    private void musicfirstsignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicfirstsignFocusGained
        if(musicfirstsign.getForeground().equals(Color.GRAY)){
            musicfirstsign.setText("");
            musicfirstsign.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_musicfirstsignFocusGained

    private void musicfirstsignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicfirstsignFocusLost
        if(musicfirstsign.getText().equals("")){
            musicfirstsign.setText("Enter FirstName");
            musicfirstsign.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_musicfirstsignFocusLost

    private void musiclastsignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musiclastsignFocusGained
        if(musiclastsign.getForeground().equals(Color.GRAY)){
            musiclastsign.setText("");
            musiclastsign.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_musiclastsignFocusGained

    private void musiclastsignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musiclastsignFocusLost
        if(musiclastsign.getText().equals("")){
            musiclastsign.setText("Enter LastName");
            musiclastsign.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_musiclastsignFocusLost

    private void musicusersignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicusersignFocusGained
        if(musicusersign.getForeground().equals(Color.GRAY)){
            musicusersign.setText("");
            musicusersign.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_musicusersignFocusGained

    private void musicusersignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicusersignFocusLost
        if(musicusersign.getText().equals("")){
            musicusersign.setText("Enter UserName");
            musicusersign.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_musicusersignFocusLost

    private void musicpasssignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicpasssignFocusGained
        if(musicpasssign.getForeground().equals(Color.GRAY)){
            musicpasssign.setEchoChar('*');
            musicpasssign.setText("");
            musicpasssign.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_musicpasssignFocusGained

    private void musicpasssignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicpasssignFocusLost
        if(musicpasssign.getText().equals("")){
            musicpasssign.setEchoChar((char)0);
            musicpasssign.setText("Enter Password");
            musicpasssign.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_musicpasssignFocusLost

    private void musicrepasssignFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicrepasssignFocusGained
        if(musicrepasssign.getForeground().equals(Color.GRAY)){
            musicrepasssign.setEchoChar('*');
            musicrepasssign.setText("");
            musicrepasssign.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_musicrepasssignFocusGained

    private void musicrepasssignFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_musicrepasssignFocusLost
        if(musicrepasssign.getText().equals("")){
            musicrepasssign.setEchoChar((char)0);
            musicrepasssign.setText("Re-Enter Password");
            musicrepasssign.setForeground(Color.GRAY);
        }
    }//GEN-LAST:event_musicrepasssignFocusLost

    private void signupbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupbtnActionPerformed
        signtodb();
    }//GEN-LAST:event_signupbtnActionPerformed

    public void icons()
    {
        BufferedImage imgsignfirst = null;
        BufferedImage imgsignlast = null;
        BufferedImage imgsignuser = null;
        BufferedImage imgsignpass = null;
        BufferedImage imgsignrepass = null;
        
        try {
            imgsignfirst = ImageIO.read(getClass().getResource("/Icons/musicfirstname.png"));
            imgsignlast = ImageIO.read(getClass().getResource("/Icons/musiclastname.png"));
            imgsignuser = ImageIO.read(getClass().getResource("/Icons/musicusersign.png"));
            imgsignpass = ImageIO.read(getClass().getResource("/Icons/musicpasssign.png"));
            imgsignrepass = ImageIO.read(getClass().getResource("/Icons/musicrepasssign.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
                
        musicfirstsignpic.setIcon(new ImageIcon(imgsignfirst.getScaledInstance(52, 42, Image.SCALE_SMOOTH)));
        musiclastsignpic.setIcon(new ImageIcon(imgsignlast.getScaledInstance(52, 42, Image.SCALE_SMOOTH)));
        musicusersignpic.setIcon(new ImageIcon(imgsignuser.getScaledInstance(52, 42, Image.SCALE_SMOOTH)));
        musicpasssignpic.setIcon(new ImageIcon(imgsignpass.getScaledInstance(52, 42, Image.SCALE_SMOOTH)));
        musicrepasssignpic.setIcon(new ImageIcon(imgsignrepass.getScaledInstance(52, 42, Image.SCALE_SMOOTH)));
    }
    
    public void setghostsign()
    {
        musicfirstsign.setText("Enter FirstName");
        musicfirstsign.setForeground(Color.GRAY);
        musiclastsign.setText("Enter LastName");
        musiclastsign.setForeground(Color.GRAY);
        musicusersign.setText("Enter UserName");
        musicusersign.setForeground(Color.GRAY);
        musicpasssign.setEchoChar((char)0);
        musicpasssign.setText("Enter Password");
        musicpasssign.setForeground(Color.GRAY);
        musicrepasssign.setEchoChar((char)0);
        musicrepasssign.setText("Re-Enter Password");
        musicrepasssign.setForeground(Color.GRAY);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Signup dialog = new Signup(new javax.swing.JFrame(), true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField musicfirstsign;
    private javax.swing.JLabel musicfirstsignpic;
    private javax.swing.JTextField musiclastsign;
    private javax.swing.JLabel musiclastsignpic;
    private javax.swing.JPasswordField musicpasssign;
    private javax.swing.JLabel musicpasssignpic;
    private javax.swing.JPasswordField musicrepasssign;
    private javax.swing.JLabel musicrepasssignpic;
    private javax.swing.JTextField musicusersign;
    private javax.swing.JLabel musicusersignpic;
    private javax.swing.JButton signupbtn;
    private javax.swing.JComboBox<String> userbox;
    // End of variables declaration//GEN-END:variables
}
