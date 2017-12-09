import java.awt.Color;
import java.util.*;
import java.io.*;
import static java.lang.Class.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Home extends javax.swing.JFrame {
    java.sql.Connection cn;
   Statement st;
   InputStream in;
    ResultSet rs;
    public Home() {
        initComponents();
         Jdconnection cn= new Jdconnection();
         st=cn.get();
         this.getContentPane().setBackground(Color.CYAN);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        HEADING = new javax.swing.JLabel();
        USERNAME = new javax.swing.JLabel();
        PASSWD = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        login = new javax.swing.JButton();
        signup = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Lets Start");
        setBackground(new java.awt.Color(255, 0, 0));
        setForeground(new java.awt.Color(255, 51, 51));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        HEADING.setFont(new java.awt.Font("Times New Roman", 3, 18)); // NOI18N
        HEADING.setForeground(new java.awt.Color(0, 51, 255));
        HEADING.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HEADING.setText("HOW'S YOUR DAY!!!");
        getContentPane().add(HEADING, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 251, 37));

        USERNAME.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        USERNAME.setForeground(new java.awt.Color(255, 0, 0));
        USERNAME.setText("Enter Username");
        getContentPane().add(USERNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 155, 33));

        PASSWD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PASSWD.setForeground(new java.awt.Color(255, 0, 0));
        PASSWD.setText("Enter Password");
        getContentPane().add(PASSWD, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 131, 32));

        username.setToolTipText("Enter Username");
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 162, 33));

        password.setToolTipText("Enter Password");
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 162, 28));

        login.setBackground(new java.awt.Color(255, 251, 19));
        login.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        login.setForeground(new java.awt.Color(0, 0, 255));
        login.setText("LOG IN");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 97, 31));

        signup.setBackground(new java.awt.Color(255, 247, 12));
        signup.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        signup.setForeground(new java.awt.Color(51, 51, 255));
        signup.setText("SIGN UP");
        signup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupActionPerformed(evt);
            }
        });
        getContentPane().add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 270, 97, 31));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Desktop\\image\\edited2.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void signupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupActionPerformed
        // TODO add your handling code here:
        signup s=new signup();
        s.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_signupActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        String uname;
        uname=username.getText();
        char c[]=password.getPassword();
        String pwd= new String(c);
        String sql="SELECT Password FROM account where username='"+uname+"'";
        try{
           rs=st.executeQuery(sql);
            if(rs.next())
            {
                rs.previous();
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid username or password");
                System.out.println("invalid username or password");
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try{
             while(rs.next()){
            String p=rs.getString("Password");
            System.out.println(""+p);
            if(p.equals(pwd)){
                System.out.println("Succesfully executed");
               Cal re=new Cal(uname,1);
               re.setVisible(true);
               this.setVisible(false);
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid username or password");
                System.out.println("invalid username or password");
            }
            }}
             catch(Exception e){
                     System.out.println(e);
                     }
        
    }//GEN-LAST:event_loginActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HEADING;
    private javax.swing.JLabel PASSWD;
    private javax.swing.JLabel USERNAME;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton signup;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
