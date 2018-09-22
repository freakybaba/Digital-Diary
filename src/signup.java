
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class signup extends javax.swing.JFrame {
        Statement st;
        InputStream in;
        ResultSet rs1;
        public signup() {
        initComponents();
        Jdconnection cn= new Jdconnection();
        st=cn.get();
        this.getContentPane().setBackground(Color.WHITE);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SIGNINUSERNAME = new javax.swing.JLabel();
        SIGNINPASSWD = new javax.swing.JLabel();
        enter = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        HINT = new javax.swing.JLabel();
        hint = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Its better to be late then never");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SIGNINUSERNAME.setFont(new java.awt.Font("Goudy Old Style", 1, 16)); // NOI18N
        SIGNINUSERNAME.setForeground(new java.awt.Color(255, 0, 0));
        SIGNINUSERNAME.setText("ENTER USERNAME");
        getContentPane().add(SIGNINUSERNAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 152, 32));

        SIGNINPASSWD.setFont(new java.awt.Font("Goudy Old Style", 1, 16)); // NOI18N
        SIGNINPASSWD.setForeground(new java.awt.Color(255, 0, 0));
        SIGNINPASSWD.setText("ENTER PASSWORD");
        getContentPane().add(SIGNINPASSWD, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 152, 32));

        enter.setBackground(new java.awt.Color(255, 255, 255));
        enter.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        enter.setText("SIGN IN");
        enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enterActionPerformed(evt);
            }
        });
        getContentPane().add(enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 120, 33));

        username.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        username.setToolTipText("Enter name");
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 230, 32));

        password.setToolTipText("Enter Password");
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 230, 32));

        HINT.setBackground(new java.awt.Color(255, 255, 255));
        HINT.setFont(new java.awt.Font("Goudy Old Style", 1, 16)); // NOI18N
        HINT.setForeground(new java.awt.Color(255, 0, 0));
        HINT.setText("ENTER HINT");
        getContentPane().add(HINT, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 130, 30));

        hint.setToolTipText("enter hint");
        hint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hintActionPerformed(evt);
            }
        });
        getContentPane().add(hint, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 230, 34));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Desktop\\image\\edited6.jpg")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void enterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enterActionPerformed
        // TODO add your handling code here:
        String uname,hin;
        uname=username.getText();
        hin=hint.getText();
        char c[]=password.getPassword();
        String sql="SELECT username FROM account where username='"+uname+"'";
        try{
        rs1=st.executeQuery(sql);
        if(rs1.next())
        {
            JOptionPane.showMessageDialog(null,"Username Exist");
        }
        else{        
        String pwd= new String(c);
        String qr="insert into account values('"+uname+"' ,'"+pwd+"','"+hin+"','C:\\Users\\himanshu\\Documents\\DIARY\\"+uname+"')";
        String create_table="CREATE TABLE "+uname+" (date VARCHAR(100),favourite INT(5),imagepath VARCHAR(100),dateorig VARCHAR(100))";
        try{
        st.execute(qr);
        st.execute(create_table);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       // System.out.println("reached");
        ProcessBuilder pb1=new ProcessBuilder("cmd.exe","/C","mkdir "+uname);
        pb1.directory(new File("C:\\Users\\himanshu\\Documents\\DIARY"));
      //  System.out.println("reachedhg");
       try {
          Process p= pb1.start();
          while(p.isAlive()); 
       } catch (IOException ex) {
           Logger.getLogger(signup.class.getName()).log(Level.SEVERE, null, ex);
       }
        
       Cal re=new Cal(uname,1);
       re.setVisible(true);
       this.setVisible(false);
        }
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_enterActionPerformed

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void hintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel HINT;
    private javax.swing.JLabel SIGNINPASSWD;
    private javax.swing.JLabel SIGNINUSERNAME;
    private javax.swing.JButton enter;
    private javax.swing.JTextField hint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
