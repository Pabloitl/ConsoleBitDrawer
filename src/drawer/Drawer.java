package drawer;

import java.io.FileNotFoundException;
import read.DrawReader;
import read.HexTransformer;

public class Drawer {
    private DrawReader repr;

    public Drawer () {
        
    }
    
    public Drawer (String file) throws FileNotFoundException {
        setFile(file);
    }
    
    public void setFile(String file) throws FileNotFoundException {
        repr = new DrawReader(file);
    }
    
    public void print() throws Exception {
        String line;
        
        try {
            while ((line = repr.readLine()) != null) {
                System.out.println(HexTransformer.toBox(line));
            }
        } finally {
            repr.close();
        }
    }
}
