import java.util.*;
import java.io.*;
import static java.lang.Class.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
public class Jdconnection {
    java.sql.Connection cn;
   Statement st;
   InputStream in;
   public Statement get()
   {
       try {
            
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        try{
         //   System.out.println("bhjbh");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Diary?zeroDateTimeBehavior=convertToNull","root","");
          //  System.out.println("bhjbh");
            st=cn.createStatement();
        //    System.out.println("bhjbh");
        
          //  JOptionPane.showMessageDialog(null,"connected");
        }
       catch (Exception e) {
           System.out.println(e);
          // JOptionPane.showMessageDialog(null,"notconnected");
       }
         return st; 
}
}
