
import com.mysql.jdbc.StringUtils;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class Cal extends javax.swing.JFrame {
   String name;
   java.sql.Connection cn;
   Statement st;
   InputStream in;
   ResultSet rs;
   String temp="",path;
   String sql2="";
   
   int pc,currdate,flagDate=1;
    JButton jdays[]=new JButton[32];
    JButton but=null;
    int m[]={31,28,31,30,31,30,31,31,30,31,30,31};
    
   

    public Cal(String name,int t) {
        
        pc=0;
        initComponents();
        
        
        this.getContentPane().setBackground(Color.WHITE);
        this.name=name; 
        
        Jdconnection cn= new Jdconnection();
        
        st=cn.get();
        
        DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
       // System.out.println(dateformat.format(date));
        
        String today="Today's date is ";
        DATE.setText(today+dateformat.format(date));
        
        new Thread(){
            
            public void run()
            {
                while(true)
                {
                    Calendar c=new GregorianCalendar();
                    
                    int hour=c.get(Calendar.HOUR);
                    int min=c.get(Calendar.MINUTE);
                    int sec=c.get(Calendar.SECOND);
                    int typ=c.get(Calendar.AM_PM);
                    String ty="PM";
                    if(typ==0)
                        ty="AM";
                    String time=hour+":"+min+":"+sec+" "+ty;
                    clockLbl.setText(time);
                }
            }
            
        }.start();
        
        
        yearBox.removeAllItems();
        for(int i=1601;i<2100;i++)
        {
            yearBox.addItem(""+i);
        }
        Calendar ca=new GregorianCalendar();
        int y=ca.get(Calendar.YEAR);
        int m=ca.get(Calendar.MONTH);
        currdate=ca.get(Calendar.DATE);
        m+=1;
        selectedDate.setText(currdate+"/"+m+"/"+y);
        //System.out.println(""+y+" "+m);
        yearBox.setSelectedItem(""+y);
        monBox.setSelectedIndex(m-1);
        
        findoddday();
        
        if(t==1){
        String rem=selectedDate.getText();        
        rem=convert(rem);
        System.out.println(rem);
        String sql="SELECT date FROM "+name+" WHERE date='"+rem+"'";
        try{
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                 //System.out.println("lol");
            }
            else{
                JOptionPane.showMessageDialog(null,"Today's entry is not submitted");
            }
           }
         
         
         
         catch(Exception e)
         {
             System.out.println("lol");
         }
        }
        
    }
    
    
    void getDate(java.awt.event.ActionEvent evt)
    {
        //System.out.println("ee");
        JButton l=new JButton();
        l=(JButton)evt.getSource();
        l.setOpaque(true);
        if(but!=null)
            but.setBackground(l.getBackground());
        l.setBackground(Color.cyan);
        but=l;
        String s=l.getText();
        s=s+"/"+(monBox.getSelectedIndex()+1)+"/"+yearBox.getSelectedItem();
        selectedDate.setText(s);
        System.out.println(s);
    }
    
    void findoddday()
    {
        
        String y=yearBox.getSelectedItem().toString();
        int year=Integer.parseInt(y);
        int month=monBox.getSelectedIndex()+1;
        
        int noh=(year-1600)/100;
        int odd1=0;
        for(int i=1;i<=noh;i++)
        {
            if(i%4==0)
            {
                odd1=odd1+6;
            }
            else
            {
                odd1=odd1+5;
            }
        }
        if(odd1>=7)
        {
            odd1=odd1%7;
        }
        
        int odd2=0;
        int nosy=(year%100)-1;
        int lp=nosy/4;
        int or=nosy-lp;
        
        for(int i=0;i<lp;i++)
        {
            odd2+=2;
        }
        
        for(int i=0;i<or;i++)
        {
            odd2+=1;
        }
        if(odd2>=7)
        {
            odd2=odd2%7;
        }
        if(year%4==0)
        {
            m[1]=29;
        }
        
        int tday=0;
        for(int i=0;i<month-1;i++)
        {
            tday=tday+m[i];
        }
        tday+=1;
        int odd3=tday%7;
        if(odd3>=7)
        {
            odd3=odd3%7;
        }
        
        int odd=odd1+odd2+odd3;
        if(odd>=7)
            odd%=7;
        
        discal(odd);
        
    }
    
    void discal(int od)
    {

        String ye=yearBox.getSelectedItem().toString();
        int yea7=Integer.parseInt(ye);
        int month=monBox.getSelectedIndex()+1;
        JLabel j[]=new JLabel[8];
        for(int i=0;i<pc;i++)
        {
            this.remove(jdays[i]);
        }
        
        Font f=new Font("Vijaya",Font.BOLD,15);
        String da[]={"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
        int x=510,y=230;
        for(int i=0;i<7;i++)
        {
            j[i]=new JLabel();      
            j[i].setHorizontalAlignment(SwingConstants.CENTER);
            j[i].setText(da[i]);
            j[i].setBounds(x,y,50,50);
            j[i].setFont(f);
            j[i].setForeground(Color.BLACK);
            x=x+50;
            this.add(j[i]);
            repaint();
            
        }
          int in=0;
        
        Font f1=new Font("Vijaya",Font.PLAIN,15);
        
        int ind=monBox.getSelectedIndex();
        
        String[] date1=new String[100];
        String year=yearBox.getSelectedItem().toString();
       
        String con=Integer.toString(ind+1);
        System.out.println(con+"fhjdsfbj");
        sql2="Select dateorig from "+name+" where favourite=1 and dateorig like '%"+con+"/"+year+"'";
        
        try{
           
            rs=st.executeQuery(sql2);
            
            while(rs.next())
            {
                date1[in]=rs.getString("dateorig");
                System.out.println(date1[in]);
                System.out.println("lol");
                in++;
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
                
            

        int yr=Integer.parseInt(year);
        if(yr%4==0)
        {
            m[1]=29;
        }
        int X=510,Y=310;
        X=X+(50*od);
        int i;
        for( i=0;i<m[ind];i++)
        {
            jdays[i]=new JButton();
            
            if((i+1)==currdate && flagDate==1)
            {
                but=jdays[i];
                jdays[i].setOpaque(true);
                jdays[i].setBackground(Color.cyan); 
                flagDate=0;
            }
            String co=Integer.toString(i+1);
            int l=0;
           for(l=0;l<in;l++)
           {
               int iend=date1[l].indexOf("/");
               String subString=date1[l].substring(0,iend);
               int bcol=Integer.parseInt(subString);
               if(i+1==bcol){
                   jdays[i].setOpaque(true);
                   jdays[i].setBackground(Color.red); 
               }
           }
            jdays[i].addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getDate(evt);
            }
        });
            jdays[i].setText(Integer.toString(i+1));
            jdays[i].setBounds(X, Y, 50, 50);
            jdays[i].setFont(f1);
            jdays[i].setForeground(Color.BLUE);
            if(X==510)
            {
                jdays[i].setForeground(Color.RED);
            }
            this.add(jdays[i]);
            repaint();
            X=X+50;
            if(X>810)
            {
                X=510;
                Y=Y+50;
            }
        }
        
        pc=m[ind];
        m[1]=28;
    }
    
    String convert(String s)
    {
         String temp="";
        char c='/';
        int i=0;
        int len=s.length();
        while(i<len)
        {
            String sd=Character.toString(s.charAt(i));
            if(c!=(s.charAt(i)))
            temp=temp+sd;
            i++;
        }
        return temp;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Add = new javax.swing.JButton();
        DATE = new javax.swing.JLabel();
        SearchAndEdit = new javax.swing.JButton();
        clockLbl = new javax.swing.JLabel();
        yearBox = new javax.swing.JComboBox<>();
        monBox = new javax.swing.JComboBox<>();
        selectedDate = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Its show time");
        setResizable(false);

        Add.setText("Add new note ");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        DATE.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DATE.setForeground(new java.awt.Color(255, 153, 102));
        DATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DATE.setText("jLabel1");

        SearchAndEdit.setText("Search and Edit the note");
        SearchAndEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchAndEditActionPerformed(evt);
            }
        });

        clockLbl.setFont(new java.awt.Font("DS-Digital", 1, 68)); // NOI18N
        clockLbl.setForeground(new java.awt.Color(0, 0, 204));
        clockLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clockLbl.setText("4:65:55 PM");
        clockLbl.setToolTipText("");
        clockLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clockLblMouseClicked(evt);
            }
        });

        yearBox.setFont(new java.awt.Font("Gadugi", 0, 17)); // NOI18N
        yearBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        yearBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                yearBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        yearBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearBoxActionPerformed(evt);
            }
        });

        monBox.setFont(new java.awt.Font("Gadugi", 0, 17)); // NOI18N
        monBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        monBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                monBoxPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        monBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monBoxActionPerformed(evt);
            }
        });

        selectedDate.setFont(new java.awt.Font("Comic Sans MS", 0, 36)); // NOI18N
        selectedDate.setForeground(new java.awt.Color(51, 0, 51));
        selectedDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectedDate.setText("00/00/0000");

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Desktop\\image\\edited12.jpg")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(453, 456));

        jButton1.setText("show favourites");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Select the date");

        jButton2.setText("Delete Note");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 2, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Selected Date");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton4.setText("Log Out");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SearchAndEdit)
                                .addGap(41, 41, 41)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(selectedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(DATE, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(44, 44, 44)
                                        .addComponent(monBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(clockLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(318, 318, 318))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clockLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yearBox, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(DATE, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(selectedDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(SearchAndEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        String s=selectedDate.getText();
      //  System.out.println(s);
      //  System.out.println(name);
        String temp=convert(s);
       // System.out.println(temp);
        String sql="SELECT date FROM "+name+" WHERE date='"+temp+"'";
        try{
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                 JOptionPane.showMessageDialog(null,"Its already done.... use Search or Edit");
            }
            else
            {
                ProcessBuilder pb=new ProcessBuilder("cmd.exe","/C","type nul > "+temp+".txt");
              pb.directory(new File("C:\\Users\\himanshu\\Documents\\DIARY\\"+name));
              sql="insert into "+name+" values('"+temp+"',0,'no image','"+s+"')";             
                 
            try {                            
               Process p=pb.start();
               while(p.isAlive());
               System.out.println("new file created");
              st.execute(sql);
              } 
            catch (Exception ex) {
              Logger.getLogger(Cal.class.getName()).log(Level.SEVERE, null, ex);
                                 }
            try{
          String path="C:\\Users\\himanshu\\Documents\\DIARY\\"+name+"\\"+temp+".txt";
          text t=new text(path,name,s,temp);
          t.setVisible(true);
          t.read_file();
          this.setVisible(false);
            }catch(Exception e)
            {
                System.out.println("its here");
            }
              
            }
        } catch (SQLException ex) {
              Logger.getLogger(Cal.class.getName()).log(Level.SEVERE, null, ex);
          }
          

        
          
    }//GEN-LAST:event_AddActionPerformed

    private void SearchAndEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchAndEditActionPerformed
        // TODO add your handling code here:
        String s=selectedDate.getText();
      //  System.out.println(s);
       // System.out.println(name);
          String temp="",path;
        char c='/';
        int i=0;
        int len=s.length();
        while(i<len)
        {
            String sd=Character.toString(s.charAt(i));
            if(c!=(s.charAt(i)))
            temp=temp+sd;
            i++;
        }
       // System.out.println(temp);
        String sql="SELECT date FROM "+name+" WHERE date='"+temp+"'";
         try{
            rs=st.executeQuery(sql);
            if(rs.next())
            {
                   path="C:\\Users\\himanshu\\Documents\\DIARY\\"+name+"\\"+temp+".txt";
                  text t=new text(path,name,s,temp);
                  t.setVisible(true);
                  t.read_file();
                  this.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Use Add buttom for action...Note isn't there");
            }
         }
         catch(Exception e)
         {
             System.out.println(e);
         }
    }//GEN-LAST:event_SearchAndEditActionPerformed

    private void clockLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clockLblMouseClicked

    }//GEN-LAST:event_clockLblMouseClicked

    private void yearBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_yearBoxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        findoddday();
    }//GEN-LAST:event_yearBoxPopupMenuWillBecomeInvisible

    private void yearBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearBoxActionPerformed

    private void monBoxPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_monBoxPopupMenuWillBecomeInvisible
        // TODO add your handling code here:
        findoddday();
    }//GEN-LAST:event_monBoxPopupMenuWillBecomeInvisible

    private void monBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_monBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String p,s="";
        String sql="Select dateorig from "+name+" where favourite=1";
        try{
            rs=st.executeQuery(sql);
            while(rs.next()){
                p=rs.getString("dateorig");
                s=(s+p+"\n");
                
            }
           Favourite fav=new Favourite(s);
           fav.setVisible(true);
        }
        catch(Exception e){
            
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Home h=new Home();
        h.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String s=selectedDate.getText();
         String temp=convert(s);
         String sql="SELECT date FROM "+name+" WHERE date='"+temp+"'";
         try{
            rs=st.executeQuery(sql);
            if(rs.next()){
        String sq="DELETE from "+name+" where date='"+temp+"'";
        try {
            st.execute(sq);
        } catch (SQLException ex) {
            Logger.getLogger(text.class.getName()).log(Level.SEVERE, null, ex);
        }
        ProcessBuilder pb= new ProcessBuilder("cmd.exe","/C","del "+path+"");
        pb.directory(new File("C:/"));
        try{
            Process p=pb.start();
            
            while(p.isAlive());
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Note Deleted Succesfully");
            }
            else{
                JOptionPane.showMessageDialog(null, "Note is not present");
            }
         }
         catch(Exception e){
             
         }
    }//GEN-LAST:event_jButton2ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JLabel DATE;
    private javax.swing.JButton SearchAndEdit;
    private javax.swing.JLabel clockLbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox<String> monBox;
    private javax.swing.JLabel selectedDate;
    private javax.swing.JComboBox<String> yearBox;
    // End of variables declaration//GEN-END:variables

    private Object charAt(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
