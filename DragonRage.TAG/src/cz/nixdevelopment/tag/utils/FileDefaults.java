package cz.nixdevelopment.tag.utils;

import java.io.File;
import java.io.IOException;

public class FileDefaults {

    public static void Tags() {
        
        File msg = new File("plugins/TAG/tags.yml");
        if (!msg.exists()) {
          
            try {
              
                msg.createNewFile();
              
            } catch (IOException ex) {
              
                System.out.println("ERROR: Failed to create tags.yml file!");
                ex.printStackTrace();
              
            }
        }
        
    }
    
}
