import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

public class Login extends javax.swing.JFrame {

    private ConnectiontoDB connection = new ConnectiontoDB();
    SimpleDateFormat datefor = new SimpleDateFormat("yyyy-MM-dd");
    
    public Login() {
        initComponents();
        connection.createConnection();
    }
    
    public void clearlog()
    {
        AccountNameField.setText("");
        PasswordField.setText("");
        showpass.setSelected(false);
    }
    
   public void logToPlayer(){
        String username;
        String password;
        if(AccountNameField.getText().equals("") && PasswordField.getPassword().equals(""))
            JOptionPane.showMessageDialog(null,"Username and Password Field Cannot be Empty","No Username and password Found!", JOptionPane.WARNING_MESSAGE);
        else if(AccountNameField.getText().equals(""))
            JOptionPane.showMessageDialog(null,"Username Field Cannot be Empty","No Username Found!", JOptionPane.WARNING_MESSAGE);
        else if(PasswordField.getPassword().equals(""))
            JOptionPane.showMessageDialog(null,"Password Field Cannot be Empty","No Password Found!", JOptionPane.WARNING_MESSAGE);
        else{
            try {
                username = AccountNameField.getText();
                password = new String(PasswordField.getPassword());
                boolean exist = false;
                Statement stateL = connection.getconnect().createStatement();
                ResultSet resultL = stateL.executeQuery("SELECT * FROM databasedc.useraccount WHERE UserName = '"+username+"' AND PassWord = '"+password+"'");
                while(resultL.next()){
                    exist = true;
                    int userid = resultL.getInt("UserID");
                    String firstname = resultL.getString("FirstName");
                    String lastname = resultL.getString("LastName");
                    String type = resultL.getString("Type").trim();
                    Date date = resultL.getDate("RegisteredDate");
                    
                    this.dispose();
                    
                    Dashboard dashboard = new Dashboard();
                    
                    if(type.equals("Artist")){
                        dashboard.setuser(new Artist(userid, username, password, firstname, lastname, date));
                        dashboard.setcon(connection.getconnect());
                        dashboard.initializePlayer();
                    }
                    else if(type.equals("Registered")){
                        dashboard.setuser(new Registered(userid, username, password, firstname, lastname, date));
                        dashboard.setcon(connection.getconnect());
                        dashboard.initializePlayer();
                    }
                    dashboard.setVisible(true);
                    
                }
                if(!exist)
                    JOptionPane.showMessageDialog(null,"User Doesn't exist","No User Found", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void loginguest()
    {
        try {
            int guestid = 0;
            Date guestdate = null;
            Statement state = connection.getconnect().createStatement();
            Statement stateCheck = connection.getconnect().createStatement();
            String insertguest = "INSERT INTO useraccount(UserName, PassWord, FirstName, LastName, Type, RegisteredDate) VALUES('"+"Guest"+"','"+""+"','"+"Guest"+"','"+"User"+"','"+"Guest"+"','" + datefor.format(new Date()) + "')";   
            state.execute(insertguest);
            state.close();
            
            ResultSet resultCheck2 = stateCheck.executeQuery("SELECT MAX(UserID) FROM databasedc.useraccount");
            if(resultCheck2.next())
                guestid = resultCheck2.getInt("MAX(UserID)");
            
            ResultSet resultCheck = stateCheck.executeQuery("SELECT * FROM databasedc.useraccount WHERE UserID = '"+guestid+"'");
            if(resultCheck.next())
                guestdate = resultCheck.getDate("RegisteredDate");
            
            stateCheck.close();
            JOptionPane.showMessageDialog(null, "Log In Successful!"); 
            this.dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setuser(new Guest(guestid, guestdate));
            dashboard.setcon(connection.getconnect());
            dashboard.initializePlayer();
            dashboard.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        Musicplayericon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        AccountNameField = new javax.swing.JTextField();
        PasswordField = new javax.swing.JPasswordField();
        showpass = new javax.swing.JCheckBox();
        exitButton = new javax.swing.JLabel();
        AccountName = new javax.swing.JLabel();
        Password = new javax.swing.JLabel();
        LoginAsGuestQuestion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        CreateNewAccountQuestion = new javax.swing.JLabel();
        LoginAsGuestButton = new javax.swing.JLabel();
        LoginButton = new javax.swing.JLabel();
        CreateNewAccountButton = new javax.swing.JLabel();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(700, 200));
        setUndecorated(true);
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(25, 25, 25));
        jPanel2.setMaximumSize(new java.awt.Dimension(65, 23));
        jPanel2.setMinimumSize(new java.awt.Dimension(65, 23));
        jPanel2.setPreferredSize(new java.awt.Dimension(65, 23));

        Musicplayericon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/MusicNinjaLogo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Music Ninja");

        AccountNameField.setBackground(new java.awt.Color(25, 25, 25));
        AccountNameField.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        AccountNameField.setForeground(new java.awt.Color(255, 255, 255));
        AccountNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                AccountNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                AccountNameFieldFocusLost(evt);
            }
        });
        AccountNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AccountNameFieldActionPerformed(evt);
            }
        });

        PasswordField.setBackground(new java.awt.Color(25, 25, 25));
        PasswordField.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        PasswordField.setForeground(new java.awt.Color(255, 255, 255));
        PasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                PasswordFieldFocusLost(evt);
            }
        });

        showpass.setBackground(new java.awt.Color(25, 25, 25));
        showpass.setFont(new java.awt.Font("Verdana", 0, 11)); // NOI18N
        showpass.setForeground(new java.awt.Color(255, 255, 255));
        showpass.setText(" Show Password");
        showpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpassActionPerformed(evt);
            }
        });

        exitButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitButton.setText("X");
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitButtonMouseClicked(evt);
            }
        });

        AccountName.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        AccountName.setForeground(new java.awt.Color(255, 255, 255));
        AccountName.setText("Account name");

        Password.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        Password.setForeground(new java.awt.Color(255, 255, 255));
        Password.setText("Password");

        LoginAsGuestQuestion.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        LoginAsGuestQuestion.setForeground(new java.awt.Color(255, 255, 255));
        LoginAsGuestQuestion.setText("Want to try Music Ninja first?");

        CreateNewAccountQuestion.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        CreateNewAccountQuestion.setForeground(new java.awt.Color(255, 255, 255));
        CreateNewAccountQuestion.setText("Don't have a Music Ninja account?");

        LoginAsGuestButton.setBackground(new java.awt.Color(51, 51, 51));
        LoginAsGuestButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        LoginAsGuestButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginAsGuestButton.setText(" LOGIN AS GUEST");
        LoginAsGuestButton.setOpaque(true);
        LoginAsGuestButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginAsGuestButtonMouseClicked(evt);
            }
        });

        LoginButton.setBackground(new java.awt.Color(51, 51, 51));
        LoginButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        LoginButton.setForeground(new java.awt.Color(255, 255, 255));
        LoginButton.setText(" LOGIN");
        LoginButton.setOpaque(true);
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
        });

        CreateNewAccountButton.setBackground(new java.awt.Color(51, 51, 51));
        CreateNewAccountButton.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        CreateNewAccountButton.setForeground(new java.awt.Color(255, 255, 255));
        CreateNewAccountButton.setText(" CREATE A NEW ACCOUNT..");
        CreateNewAccountButton.setOpaque(true);
        CreateNewAccountButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreateNewAccountButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LoginAsGuestQuestion, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CreateNewAccountQuestion, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LoginAsGuestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateNewAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(Password)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(showpass)
                                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(AccountName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AccountNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(93, 93, 93)
                                        .addComponent(Musicplayericon)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)))))
                        .addGap(37, 37, 37))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2))
                    .addComponent(Musicplayericon))
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AccountNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AccountName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Password))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showpass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginAsGuestQuestion)
                    .addComponent(LoginAsGuestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateNewAccountQuestion)
                    .addComponent(CreateNewAccountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(85, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 922, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitButtonMouseClicked

    private void showpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpassActionPerformed

        if(!PasswordField.getForeground().equals(Color.GRAY))
        {
            if(showpass.isSelected())
            PasswordField.setEchoChar((char)0);
            else
            PasswordField.setEchoChar('\u25CF');
        }
    }//GEN-LAST:event_showpassActionPerformed

    private void PasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusLost

    }//GEN-LAST:event_PasswordFieldFocusLost

    private void PasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFieldFocusGained
        
    }//GEN-LAST:event_PasswordFieldFocusGained

    private void AccountNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AccountNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AccountNameFieldActionPerformed

    private void AccountNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AccountNameFieldFocusLost
       
    }//GEN-LAST:event_AccountNameFieldFocusLost

    private void AccountNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AccountNameFieldFocusGained
       
    }//GEN-LAST:event_AccountNameFieldFocusGained

    private void LoginAsGuestButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginAsGuestButtonMouseClicked
       loginguest();
    }//GEN-LAST:event_LoginAsGuestButtonMouseClicked

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked
        logToPlayer();
    }//GEN-LAST:event_LoginButtonMouseClicked

    private void CreateNewAccountButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreateNewAccountButtonMouseClicked
        Signup sign = new Signup(this, true);
        sign.setcon(connection.getconnect());
        sign.setVisible(true);
    }//GEN-LAST:event_CreateNewAccountButtonMouseClicked

    
    
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AccountName;
    private javax.swing.JTextField AccountNameField;
    private javax.swing.JLabel CreateNewAccountButton;
    private javax.swing.JLabel CreateNewAccountQuestion;
    private javax.swing.JLabel LoginAsGuestButton;
    private javax.swing.JLabel LoginAsGuestQuestion;
    private javax.swing.JLabel LoginButton;
    private javax.swing.JLabel Musicplayericon;
    private javax.swing.JLabel Password;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JLabel exitButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox showpass;
    // End of variables declaration//GEN-END:variables
}
