
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Save {
    String path,text;
    void filesave(String path,String text)
    {
    {
        this.path=path;
        this.text=text;
    }
    BufferedWriter bw=null;
    FileWriter fw=null;
   
        try {
            fw=new FileWriter(path);
            bw=new BufferedWriter(fw);
            bw.write(text);
            bw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Save.class.getName()).log(Level.SEVERE, null, ex);
}
    
 }
}

