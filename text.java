
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.inet.jortho.SpellChecker;
import com.inet.jortho.FileUserDictionary;
import java.awt.event.ActionEvent;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

public class text extends javax.swing.JFrame {
    java.sql.Connection cn;
    Statement st;
    InputStream in;
    FileReader fr = null;
    BufferedReader br = null;
    ResultSet rs;
    String path,name,curr,temp,impath;
    public text(String path,String name,String curr_date,String temp) {
        initComponents();
       
        this.getContentPane().setBackground(Color.BLACK);
        this.path=path;
        this.name=name;
        this.curr=curr_date;
        this.temp=temp;
        Jdconnection cn= new Jdconnection();
        st=cn.get();
        DateFormat dateformat=new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
       // System.out.println(dateformat.format(date));
        String today="Today's date is ";
        TODAYDATE.setText(today+dateformat.format(date));
        CURRENTNOTEDATE.setText("Note's date"+curr_date);
         String sql="Select favourite from "+name+" where date='"+temp+"'";
        try{
            rs=st.executeQuery(sql);
            while(rs.next()){
                int i=rs.getInt("favourite");
                if(i==0)
                {
                  jButton1.setBackground(Color.white);
                  jButton2.setBackground(Color.red);
                  jButton2.setToolTipText("Not a favourite note");
                  jButton1.setToolTipText("Click to add on favourite notes");
                }else{
                    jButton1.setBackground(Color.red);
                    jButton2.setBackground(Color.white);
                    jButton2.setToolTipText("Click to remove from favourite notes");
                    jButton1.setToolTipText("Favourite one");
                }
            }
        }
        catch(Exception e){
            
        }
        spellchecker();
        und();
        
        
    }
    void read_file()
        {
            String str="",ptr=null;
            Encryption en=new Encryption();
            NOTE.setText("");
        try { 
            fr=new FileReader(path);
            br=new BufferedReader(fr);
            
            while((str=br.readLine())!=null)
            {
               ptr=(str+"\n");
            }
            
        } catch (Exception ex) {
            
        }
        if(ptr!=null)
        str=en.decrypt(ptr);
         NOTE.setText(str);
        String sql="SELECT imagepath from "+name+" where date='"+temp+"'";
        try{
        rs=st.executeQuery(sql);
        }
        catch(Exception e){
            
        }try{
        if(rs.next()){
            String p=rs.getString("imagepath");
         //   System.out.println(""+p);
            if(p.equals("no image"))
            {
                
            }
            else
            {
              //  System.out.println(""+p);
                jp.insertIcon(new ImageIcon("C:\\Users\\himanshu\\Documents\\DIARY\\"+name+"\\"+p));
                
            }
        }
        }
        catch(Exception e){
            
        }
        }
    
     boolean isImage(File file){
       String name=file.getName();
       return name.endsWith(".jpg")||name.endsWith(".png");
   }
      void spellchecker()
      {
          String userDictionaryPath="/dictionary/";
          SpellChecker.setUserDictionaryProvider(new FileUserDictionary(userDictionaryPath));
          SpellChecker.registerDictionaries(getClass().getResource(userDictionaryPath),"en");
          SpellChecker.register(NOTE);
      }
        void und(){
             final UndoManager undo = new UndoManager();
   Document doc = NOTE.getDocument();
    
   // Listen for undo and redo events
   doc.addUndoableEditListener(new UndoableEditListener() {
       public void undoableEditHappened(UndoableEditEvent evt) {
           undo.addEdit(evt.getEdit());
       }
   });
    
   // Create an undo action and add it to the text component
   NOTE.getActionMap().put("Undo",
       new AbstractAction("Undo") {
           public void actionPerformed(ActionEvent evt) {
               try {
                   if (undo.canUndo()) {
                       undo.undo();
                   }
               } catch (CannotUndoException e) {
               }
           }
      });
    
   // Bind the undo action to ctl-Z
   NOTE.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
    
   // Create a redo action and add it to the text component
   NOTE.getActionMap().put("Redo",
       new AbstractAction("Redo") {
           public void actionPerformed(ActionEvent evt) {
               try {
                   if (undo.canRedo()) {
                       undo.redo();
                   }
               } catch (CannotRedoException e) {
               }
           }
       });
    
   // Bind the redo action to ctl-Y
   NOTE.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");

        }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        NOTE = new javax.swing.JTextArea();
        TODAYDATE = new javax.swing.JLabel();
        CURRENTNOTEDATE = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        style = new javax.swing.JComboBox<>();
        face = new javax.swing.JComboBox<>();
        FONTSELECTOR = new javax.swing.JButton();
        SIZE = new javax.swing.JTextField();
        Logoutb = new javax.swing.JButton();
        Saveb = new javax.swing.JButton();
        Searchb = new javax.swing.JButton();
        Addb = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jp = new javax.swing.JTextPane();
        InsertImage = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        FILE = new javax.swing.JMenu();
        sav = new javax.swing.JMenuItem();
        LOGOUT = new javax.swing.JMenuItem();
        DELETENOTE = new javax.swing.JMenuItem();
        SEARCH = new javax.swing.JMenu();
        SEARCHORADD = new javax.swing.JMenuItem();
        EDIT = new javax.swing.JMenu();
        CUT = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        COPY = new javax.swing.JMenuItem();
        PASTE = new javax.swing.JMenuItem();
        BACKGROUNDCOLOR = new javax.swing.JMenuItem();
        FONTCOLOR = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Share your secret");
        setBackground(new java.awt.Color(0, 0, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NOTE.setColumns(20);
        NOTE.setRows(5);
        jScrollPane2.setViewportView(NOTE);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 400, 380));

        TODAYDATE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TODAYDATE.setForeground(new java.awt.Color(0, 204, 255));
        TODAYDATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TODAYDATE.setText("jLabel1");
        getContentPane().add(TODAYDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 10, 260, 34));

        CURRENTNOTEDATE.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        CURRENTNOTEDATE.setForeground(new java.awt.Color(255, 204, 204));
        CURRENTNOTEDATE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CURRENTNOTEDATE.setText("jLabel2");
        getContentPane().add(CURRENTNOTEDATE, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 280, 32));

        jCheckBox1.setText("jCheckBox1");
        getContentPane().add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        style.setBackground(new java.awt.Color(0, 0, 0));
        style.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        style.setForeground(new java.awt.Color(255, 255, 255));
        style.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plain", "Bold", "Italic", " " }));
        style.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                styleActionPerformed(evt);
            }
        });
        getContentPane().add(style, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, 50));

        face.setBackground(new java.awt.Color(0, 0, 0));
        face.setFont(new java.awt.Font("Comic Sans MS", 1, 11)); // NOI18N
        face.setForeground(new java.awt.Color(255, 255, 255));
        face.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Arial", "Arial Black", "Comic Sans MS", "Symbol", "Tahoma", "Verdana", "Vladmir Script", "Calibri", "Cambria", "Castellar" }));
        face.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                faceActionPerformed(evt);
            }
        });
        getContentPane().add(face, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 65, 50));

        FONTSELECTOR.setBackground(new java.awt.Color(0, 0, 0));
        FONTSELECTOR.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        FONTSELECTOR.setForeground(new java.awt.Color(255, 255, 255));
        FONTSELECTOR.setText("Click to confirm");
        FONTSELECTOR.setToolTipText("Selected font");
        FONTSELECTOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FONTSELECTORActionPerformed(evt);
            }
        });
        getContentPane().add(FONTSELECTOR, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 50));

        SIZE.setBackground(new java.awt.Color(0, 0, 0));
        SIZE.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SIZE.setForeground(new java.awt.Color(255, 255, 255));
        SIZE.setText("12");
        SIZE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SIZEActionPerformed(evt);
            }
        });
        getContentPane().add(SIZE, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 38, 50));

        Logoutb.setText("Log Out");
        Logoutb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutbActionPerformed(evt);
            }
        });
        getContentPane().add(Logoutb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, -1, 32));

        Saveb.setText("Save");
        Saveb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavebActionPerformed(evt);
            }
        });
        getContentPane().add(Saveb, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 470, -1, 32));

        Searchb.setText("Search");
        Searchb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchbActionPerformed(evt);
            }
        });
        getContentPane().add(Searchb, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 470, -1, 32));

        Addb.setText("Add Note");
        Addb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddbActionPerformed(evt);
            }
        });
        getContentPane().add(Addb, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 470, -1, 32));

        jp.setEditable(false);
        jScrollPane1.setViewportView(jp);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 610, 380));

        InsertImage.setText("Select Image");
        InsertImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertImageActionPerformed(evt);
            }
        });
        getContentPane().add(InsertImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 470, 165, 42));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Desktop\\image\\edited5.png")); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(674, 174));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 1020, 100));

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Downloads\\19214234-The-word-Favorite-in-a-blue-thumb-s-up-symbol-to-represent-liking-a-comment-photo-or-product-on-a-so-Stock-Photo.png")); // NOI18N
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 50, 50));

        jButton2.setBackground(new java.awt.Color(255, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Users\\himanshu\\Downloads\\download.png")); // NOI18N
        jButton2.setToolTipText("Remove from favourite");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 50, 50));

        FILE.setText("File");

        sav.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        sav.setText("Save");
        sav.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savActionPerformed(evt);
            }
        });
        FILE.add(sav);

        LOGOUT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        LOGOUT.setText("Log Out");
        LOGOUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOUTActionPerformed(evt);
            }
        });
        FILE.add(LOGOUT);

        DELETENOTE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        DELETENOTE.setText("Delete current note");
        DELETENOTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETENOTEActionPerformed(evt);
            }
        });
        FILE.add(DELETENOTE);

        jMenuBar1.add(FILE);

        SEARCH.setText("Search");

        SEARCHORADD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        SEARCHORADD.setText("Search or Add");
        SEARCHORADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SEARCHORADDActionPerformed(evt);
            }
        });
        SEARCH.add(SEARCHORADD);

        jMenuBar1.add(SEARCH);

        EDIT.setText("Edit");

        CUT.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        CUT.setText("Cut");
        CUT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CUTActionPerformed(evt);
            }
        });
        EDIT.add(CUT);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Redo");
        EDIT.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Undo");
        EDIT.add(jMenuItem1);

        COPY.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        COPY.setText("Copy");
        COPY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                COPYActionPerformed(evt);
            }
        });
        EDIT.add(COPY);

        PASTE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        PASTE.setText("Paste");
        PASTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PASTEActionPerformed(evt);
            }
        });
        EDIT.add(PASTE);

        BACKGROUNDCOLOR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        BACKGROUNDCOLOR.setText("Change background");
        BACKGROUNDCOLOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BACKGROUNDCOLORActionPerformed(evt);
            }
        });
        EDIT.add(BACKGROUNDCOLOR);

        FONTCOLOR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK));
        FONTCOLOR.setText("Change Font Color");
        FONTCOLOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FONTCOLORActionPerformed(evt);
            }
        });
        EDIT.add(FONTCOLOR);

        jMenuBar1.add(EDIT);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void savActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savActionPerformed
        // TODO add your handling code here:
         String s=NOTE.getText();
         Encryption en=new Encryption();
         s=en.encrypt(s);
        System.out.println(s);
         Save sa=new Save();
         sa.filesave(path,s);
    }//GEN-LAST:event_savActionPerformed

    private void LOGOUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOUTActionPerformed
        // TODO add your handling code here:
        Home log_out=new Home();
        log_out.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_LOGOUTActionPerformed

    private void SEARCHORADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SEARCHORADDActionPerformed
        // TODO add your handling code here:
        Cal c=new Cal(name,0);
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_SEARCHORADDActionPerformed

    private void DELETENOTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETENOTEActionPerformed
        // TODO add your handling code here:
        System.out.println("not deleted");
      //  System.out.println(""+path);
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
        
        String sq="DELETE from "+name+" where date='"+temp+"'";
        try {
            st.execute(sq);
        } catch (SQLException ex) {
            Logger.getLogger(text.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cal c=new Cal(name,0);
        c.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_DELETENOTEActionPerformed

    private void COPYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_COPYActionPerformed
        // TODO add your handling code here:
        NOTE.copy();
    }//GEN-LAST:event_COPYActionPerformed

    private void CUTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CUTActionPerformed
        // TODO add your handling code here:
        NOTE.cut();
    }//GEN-LAST:event_CUTActionPerformed

    private void PASTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PASTEActionPerformed
        // TODO add your handling code here:
        NOTE.paste();
    }//GEN-LAST:event_PASTEActionPerformed

    private void BACKGROUNDCOLORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BACKGROUNDCOLORActionPerformed
        // TODO add your handling code here:
        Color incol=Color.white;
        Color col=JColorChooser.showDialog(this,"Select Color" , incol);
        NOTE.setBackground(col);
    }//GEN-LAST:event_BACKGROUNDCOLORActionPerformed

    private void FONTCOLORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FONTCOLORActionPerformed
        // TODO add your handling code here:
        Color incol=Color.white;
        Color col=JColorChooser.showDialog(this,"Select Color" , incol);
        NOTE.setForeground(col);
    }//GEN-LAST:event_FONTCOLORActionPerformed

    private void faceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_faceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_faceActionPerformed

    private void styleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_styleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_styleActionPerformed

    private void FONTSELECTORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FONTSELECTORActionPerformed
        // TODO add your handling code here:
        String s1,s2;
        Font font=new Font("Arial",Font.PLAIN,12);
        int i1=face.getSelectedIndex();
        s2=face.getItemAt(i1);
        int i2=style.getSelectedIndex();
        s1=style.getItemAt(i2);
        int i3=Integer.parseInt(SIZE.getText());
        if(s1.equals("Plain"))
         font= new Font(s2,Font.PLAIN,i3);
        else if(s1.equals("Bold"))
          font= new Font(s2,Font.BOLD,i3);
        else if(s1.equals("Italic"))
          font= new Font(s2,Font.ITALIC,i3); 
       
        NOTE.setFont(font);
    }//GEN-LAST:event_FONTSELECTORActionPerformed

    private void LogoutbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutbActionPerformed
        // TODO add your handling code here:
        this.LOGOUTActionPerformed(evt);
    }//GEN-LAST:event_LogoutbActionPerformed

    private void SavebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavebActionPerformed
        // TODO add your handling code here:
        this.savActionPerformed(evt);
        
    }//GEN-LAST:event_SavebActionPerformed

    private void SearchbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchbActionPerformed
        // TODO add your handling code here:
        this.SEARCHORADDActionPerformed(evt);
    }//GEN-LAST:event_SearchbActionPerformed

    private void AddbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddbActionPerformed
        // TODO add your handling code here:
        this.SEARCHORADDActionPerformed(evt);
    }//GEN-LAST:event_AddbActionPerformed

    private void SIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SIZEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SIZEActionPerformed

    private void InsertImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertImageActionPerformed
        // TODO add your handling code here:
        JFileChooser jf=new JFileChooser();
        int option=jf.showOpenDialog(this);
        if(option==JFileChooser.APPROVE_OPTION)
        {
            File file=jf.getSelectedFile();
            if(isImage(file))
            {
                jp.setText(" ");
                jp.insertIcon(new ImageIcon(file.getAbsolutePath()));
                System.out.println(file.getAbsolutePath());
                impath=file.getAbsolutePath();
                String ca="C:\\Users\\himanshu\\Documents\\DIARY\\"+name;
                String iname=file.getName();
                 ProcessBuilder pb=new ProcessBuilder("cmd.exe","/C","copy "+impath+" "+ca+"");
              pb.directory(new File("C:\\Users\\himanshu\\Documents\\DIARY\\"+name));
                try{
                   Process p=pb.start();
                   while(p.isAlive());
                }
                catch(Exception e)
                {
                    
                }
                
               String sql="UPDATE "+name+" SET imagepath='"+iname+"' where date='"+temp+"'";
               try{
               st.execute(sql);
               }
               catch(Exception e){
               System.out.println(e);
            }}
            else{
                JOptionPane.showMessageDialog(null,"File is not image");
            }
        }
    }//GEN-LAST:event_InsertImageActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String sql="UPDATE "+name+" SET favourite=1 where date='"+temp+"'";
               try{
               st.execute(sql);
               jButton1.setBackground(Color.red);
               jButton2.setBackground(Color.white);
               jButton2.setToolTipText("Click to remove from favourite notes");
               jButton1.setToolTipText("Favourite one");
               }
               catch(Exception e){
               System.out.println(e);
            }
              
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String sql="UPDATE "+name+" SET favourite=0 where date='"+temp+"'";
               try{
               st.execute(sql);
               jButton2.setBackground(Color.red);
               jButton1.setBackground(Color.white);
               jButton2.setToolTipText("Not a favourite note");
               jButton1.setToolTipText("Click to add on favourite notes");
               }
               catch(Exception e){
               System.out.println(e);
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(text.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
           }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Addb;
    private javax.swing.JMenuItem BACKGROUNDCOLOR;
    private javax.swing.JMenuItem COPY;
    private javax.swing.JLabel CURRENTNOTEDATE;
    private javax.swing.JMenuItem CUT;
    private javax.swing.JMenuItem DELETENOTE;
    private javax.swing.JMenu EDIT;
    private javax.swing.JMenu FILE;
    private javax.swing.JMenuItem FONTCOLOR;
    private javax.swing.JButton FONTSELECTOR;
    private javax.swing.JToggleButton InsertImage;
    private javax.swing.JMenuItem LOGOUT;
    private javax.swing.JButton Logoutb;
    private javax.swing.JTextArea NOTE;
    private javax.swing.JMenuItem PASTE;
    private javax.swing.JMenu SEARCH;
    private javax.swing.JMenuItem SEARCHORADD;
    private javax.swing.JTextField SIZE;
    private javax.swing.JButton Saveb;
    private javax.swing.JButton Searchb;
    private javax.swing.JLabel TODAYDATE;
    private javax.swing.JComboBox<String> face;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jp;
    private javax.swing.JMenuItem sav;
    private javax.swing.JComboBox<String> style;
    // End of variables declaration//GEN-END:variables
}
