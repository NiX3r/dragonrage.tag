package cz.nixdevelopment.tag.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Debugger {

    public static void Debug(String text) {
        
        File debug = new File("debug.txt");
        
        if(debug.exists()) {
            try {
                FileWriter fw = new FileWriter("debug.txt", true);
                fw.write("[" + LocalDateTime.now().toString() + "] » " + text + "\n");
                fw.flush();
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else {
            try {
                debug.createNewFile();
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
    }
    
}
