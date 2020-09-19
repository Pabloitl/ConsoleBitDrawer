package run;

import drawer.Drawer;

public class Main {
    public static void main(String[] args) throws Exception {
        Drawer bitImage = new Drawer("assets/right.txt");
        
        bitImage.print();
    }
}
