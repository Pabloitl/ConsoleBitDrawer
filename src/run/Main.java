package run;

import drawer.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] files = {
            "assets/up.txt",
            "assets/down.txt",
            "assets/left.txt",
            "assets/right.txt",
        };
        
        GUIDrawer bitImage = new GUIDrawer(files);
        
        bitImage.print();
    }
}
